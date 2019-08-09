package com.yxl.myhome.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author BG343094
 * @since 2019/8/6 19:18
 **/
@WebFilter
@Component
public class RequestBodyReplaceFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws
            IOException,
            ServletException {
        ServletRequest newRequest = null;
        if (servletRequest instanceof HttpServletRequest) {
            newRequest = new MyHttpServletRequestWrapper((HttpServletRequest) servletRequest);
        }
        if (newRequest != null) {
            filterChain.doFilter(newRequest, servletResponse);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
