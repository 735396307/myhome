package com.yxl.myhome.transaction;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author BG343094
 * @since 2019/8/29 14:13
 **/
@Service
public class MultiTransactionServiceImpl implements MultiTransactionService {
    @Override
    public void sendTask(Runnable runnable, String taskGroup) {

    }

    @Override
    public void sendTask(List<Runnable> runnableList) {

    }
}
