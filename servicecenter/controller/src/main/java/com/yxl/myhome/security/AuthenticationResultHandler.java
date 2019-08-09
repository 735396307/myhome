package com.yxl.myhome.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author BG343094
 * @since 2019/8/6 19:40
 **/
public class AuthenticationResultHandler implements AuthenticationSuccessHandler, AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
            AuthenticationException e) throws IOException, ServletException {
        System.out.println("fail");

    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
            Authentication authentication) throws IOException, ServletException {
        System.out.println("success");
    }
}