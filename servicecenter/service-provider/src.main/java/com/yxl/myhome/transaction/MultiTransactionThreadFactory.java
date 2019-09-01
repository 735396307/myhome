package com.yxl.myhome.transaction;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author BG343094
 * @since 2019/8/29 15:08
 **/
public class MultiTransactionThreadFactory implements ThreadFactory {
    private AtomicInteger atomicInteger = new AtomicInteger(0);
    private String namePrefix;

    MultiTransactionThreadFactory(String namePrefix) {
        this.namePrefix = namePrefix;
    }

    @Override
    public Thread newThread(Runnable r) {
        SecurityManager s = System.getSecurityManager();
        ThreadGroup group = (s != null) ? s.getThreadGroup() :
                Thread.currentThread().getThreadGroup();
        Thread t = new Thread(group, r, namePrefix + atomicInteger.getAndIncrement(), 0);
        if (t.isDaemon()) {
            t.setDaemon(false);
        }
        if (t.getPriority() != Thread.NORM_PRIORITY) {
            t.setPriority(Thread.NORM_PRIORITY);
        }
        return t;
    }
}
