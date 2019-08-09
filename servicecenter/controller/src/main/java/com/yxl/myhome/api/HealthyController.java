package com.yxl.myhome.api;

import com.alibaba.fastjson.JSON;
import com.yxl.myhome.po.UserPO;
import com.yxl.myhome.security.CustomAuthentication;
import com.yxl.myhome.security.CustomSecurityContext;
import com.yxl.myhome.user.UserDao;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author BG343094
 * @since 2019/8/6 19:25
 **/
@RequestMapping("/pub")
@RestController
public class HealthyController {
    @Resource
    private UserDao userDao;

    @GetMapping("/health")
    public String health(HttpServletRequest request) {
        CustomAuthentication userAuth = new CustomAuthentication(new UserPO());
        CustomSecurityContext context = new CustomSecurityContext(userAuth);
        context.setAuthentication(userAuth);

        SecurityContextHolder.setContext(context);
        request.getSession().setAttribute("CONTEXT",context);
        return "true";
    }

    @GetMapping("/getUser")
    public String getUser(HttpServletRequest request) {
        System.out.println(request.getSession().getAttribute("qwe").toString());
        return "true";
    }
}
