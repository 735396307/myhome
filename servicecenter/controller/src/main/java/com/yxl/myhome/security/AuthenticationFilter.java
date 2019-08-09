package com.yxl.myhome.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author BG343094
 * @since 2019/8/6 19:45
 **/
@Slf4j
@WebFilter
@Component
public class AuthenticationFilter extends SessionManagementFilter {
    private static final Set<String> NOT_FILTER_PATH = new HashSet<>(Arrays.asList("^/pub/.*", "^/api/.*"));

    @Resource
    private AuthenticationFailureHandler authenticationFailureHandler;

    public AuthenticationFilter() {
        super(new HttpSessionSecurityContextRepository());
        this.setAuthenticationFailureHandler(authenticationFailureHandler);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        if (filterMatched(request.getServletPath())) {
            chain.doFilter(req, res);
            return;
        }
        filterAndSetContext(request);
        chain.doFilter(req, res);
    }
    @Override
    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    private void filterAndSetContext(HttpServletRequest request) {
        CustomSecurityContext context = (CustomSecurityContext) request.getSession()
                .getAttribute("CONTEXT");
        SecurityContextHolder.setContext(context);
    }

    private boolean filterMatched(String path) {
        for(String temp : NOT_FILTER_PATH) {
            if(path.matches(temp)) {
                return true;
            }
        }
        return false;
    }
}
