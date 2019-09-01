package com.yxl.myhome.transaction;

import java.util.List;

/**
 * @author BG343094
 * @since 2019/8/29 14:05
 **/
public interface MultiTransactionService {
    /**
     * 产生任务，通过taskGroup来标识同一组事务
     * @param runnable 任务
     * @param taskGroup 组标识，不同组之间的任务要区分开
     */
    void sendTask(Runnable runnable, String taskGroup);

    /**
     * 产生任务，该组会产生一个自动生成的组号
     * @param runnableList list
     */
    void sendTask(List<Runnable> runnableList);
}
