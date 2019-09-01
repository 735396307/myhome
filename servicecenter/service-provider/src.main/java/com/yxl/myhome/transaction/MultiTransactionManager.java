package com.yxl.myhome.transaction;

import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.*;

/**
 * @author BG343094
 * @since 2019/8/29 14:16
 **/
@Service
public class MultiTransactionManager {
    @Resource
    private PlatformTransactionManager transactionManager;

    @Resource
    private MultiTransactionService multiTransactionService;

    private Map<String, List<Runnable>> taskGroup = new ConcurrentHashMap<>();

    private Map<String, ExecutorService> executorsGroup = new ConcurrentHashMap<>();

    private Map<String, Boolean> taskGroupStatus= new ConcurrentHashMap<>();

    private ExecutorService defaultExecutor = new ThreadPoolExecutor(20, 20, 60, TimeUnit.SECONDS, new SynchronousQueue<>(),
            new MultiTransactionThreadFactory("Multi Transaction"), new ThreadPoolExecutor.CallerRunsPolicy());
    /**
     * 发出任务，执行任务需要调用execute方法，可以多次调用
     *
     * @param task  任务
     * @param group 组
     */
    public synchronized void sendTask(Runnable task, String group) {
        if (taskGroup.containsKey(group)) {
            List<Runnable> runnableList = taskGroup.get(group);
            runnableList.add(task);
        }
        else {
            List<Runnable> runnableList = new ArrayList<>();
            runnableList.add(task);
            taskGroup.put(group, runnableList);
        }
    }

    /**
     * 发出任务，执行任务需要调用exceute方法，可以多次调用
     *
     * @param taskList 任务
     * @param group    组
     */
    public synchronized void sendTask(List<Runnable> taskList, String group) {
        if (taskGroup.containsKey(group)) {
            List<Runnable> runnableList = taskGroup.get(group);
            runnableList.addAll(taskList);
        }
        else {
            List<Runnable> runnableList = new ArrayList<>(taskList);
            taskGroup.put(group, runnableList);
        }
    }

    /**
     * 一次发布一组任务，这里默认使用自动生成的组，并会返回
     * 后续持续添加其他任务到组，需要携带返回的组
     *
     * @param taskList list
     * @return group
     */
    public synchronized String sendTask(List<Runnable> taskList) {
        String group = UUID.randomUUID().toString();
        taskGroup.put(group, taskList);
        return group;
    }

    /**
     * 执行任务，使用默认的线程池
     *
     * @param group 组
     */
    public void execute(String group) {

        List<Runnable> runnableList = taskGroup.get(group);

        if(CollectionUtils.isEmpty(runnableList)) {
            return;
        }
        CyclicBarrier barrier = new CyclicBarrier(runnableList.size());
        System.out.println(runnableList.size());
        for (Runnable runnable : runnableList) {
            Runnable runnableWrapper = taskWrapper(runnable, barrier, group);
            defaultExecutor.submit(runnableWrapper);
        }
    }

    /**
     * 执行任务，使用提供的线程池
     * 注意线程池参数的设置，如果设置不合适会将程序卡死
     * @param group 组
     */
    public void execute(String group, ExecutorService executorService) {
        List<Runnable> runnableList = taskGroup.get(group);
        if(CollectionUtils.isEmpty(runnableList)) {
            return;
        }
        CyclicBarrier barrier = new CyclicBarrier(runnableList.size());
        for (Runnable runnable : runnableList) {
            Runnable runnableWrapper = taskWrapper(runnable, barrier, group);
            executorService.submit(runnableWrapper);
        }
    }

    private Runnable taskWrapper(Runnable runnable, CyclicBarrier barrier, String group) {

        return ()-> {
            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);

            TransactionStatus transaction = transactionManager.getTransaction(def);
            System.out.println(1);

            try {
                runnable.run();
            } catch (Exception e) {
                System.out.println("exception");
                e.printStackTrace();
                taskGroupStatus.put(group, false);
            }finally {
                try {
                    barrier.await(100, TimeUnit.SECONDS);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println(taskGroupStatus.get(group));
            if(Objects.equals(taskGroupStatus.get(group), Boolean.FALSE)) {
                System.out.println("roo");
                transactionManager.rollback(transaction);
            }
            else {
                System.out.println("commit");
                transactionManager.commit(transaction);
            }
        };
    }
}
