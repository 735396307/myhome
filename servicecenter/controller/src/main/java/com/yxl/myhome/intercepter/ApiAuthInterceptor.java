package com.yxl.myhome.intercepter;

import com.yxl.myhome.filter.MyHttpServletRequestWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StreamUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author BG343094
 * @since 2019/8/6 16:19
 **/
@Slf4j
public class ApiAuthInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        log.info(StreamUtils.copyToString(request.getInputStream(), Charset.forName("utf-8")));
        return true;
    }
}
