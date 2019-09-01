package com.yxl.myhome.test;

import com.alibaba.fastjson.JSON;
import com.yxl.myhome.po.UserPO;
import com.yxl.myhome.user.UserDao;
import com.yxl.myhome.user.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.*;

@RestController
@RequestMapping("/test")
public class Test {
    @Resource
    private UserDao userDao;

    @Resource
    private UserService userService;

    @GetMapping("/test")
    public String fun() {
        UserPO userPO1 = new UserPO();
        userPO1.setId(1L);
        userPO1.setUserName("22222");
        userPO1.setPassword(System.currentTimeMillis() + "_1");

        userService.update(userPO1);

        userPO1.setUserName("22223332");
        userService.update(userPO1);
        return JSON.toJSONString(userDao.getList());
    }

    @PostMapping("/test1")
    public String fun1(HttpServletResponse response) {
        return "{\"timestamp\":1566554191478,\"status\":404,\"error\":\"Not Found\",\"message\":\"No message available\",\"path\":\"/orderPort/push\"}";
    }

    public static void main(String[] args) throws Exception {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10);
        cyclicBarrier.await(10, TimeUnit.SECONDS);
        System.out.println(1);
    }
}
