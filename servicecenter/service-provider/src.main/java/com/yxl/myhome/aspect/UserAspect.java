package com.yxl.myhome.aspect;

import com.yxl.myhome.po.UserPO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * @author BG343094
 * @since 2019/8/30 19:48
 **/
@Aspect
@Component
@EnableAspectJAutoProxy(exposeProxy= true)
public class UserAspect {

    @Around(value = "execution(* com.yxl.myhome.user.UserServiceImpl.update(..)) && args(userPO)", argNames = "joinPoint, userPO")
    public Object aspectTest(ProceedingJoinPoint joinPoint, UserPO userPO) {
        try {
            joinPoint.proceed();
            System.out.println("after");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
//        throw new RuntimeException();
        return 1;
    }
}
