package com.yxl.myhome.security;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author BG343094
 * @since 2019/8/6 20:01
 **/

@Component
public class WebAccessDeniedHandler implements AccessDeniedHandler {

    @Resource
    private AuthenticationResultHandler authenticationResultHandler;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
        authenticationResultHandler.onAuthenticationFailure(request, response, new AuthException("err", new RuntimeException()));
    }
}

class AuthException extends AuthenticationException {

    public AuthException(String msg, Throwable t) {
        super(msg, t);
    }
}