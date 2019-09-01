package com.yxl.myhome.user;

import com.alibaba.fastjson.JSON;
import com.yxl.myhome.po.UserPO;
import com.yxl.myhome.transaction.MultiTransactionManager;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author BG343094
 * @since 2019/8/29 21:25
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Resource
    private MultiTransactionManager multiTransactionManager;

    @Override
    public void create(UserPO userPO) {
        userDao.create(userPO);
    }


    @Override
    public void update(UserPO userPO) {
        userDao.update(userPO);
    }

    @Override
    public void update1(UserPO userPO) {
        ((UserService)AopContext.currentProxy()).update(userPO);

    }

    private Runnable getSleepTask(String ss) {
        return ()->{
            try {
                System.out.println(ss);
                Thread.sleep(10000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
    }

    private Runnable getTask(UserPO userPO) {
        return () -> {
            userDao.update(userPO);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (userPO.getUserName().equals("yxl")) {
                throw new RuntimeException();
            }
            System.out.println(userPO.getUserName());
        };
    }

}
