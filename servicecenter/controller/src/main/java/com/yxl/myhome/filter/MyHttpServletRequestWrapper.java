package com.yxl.myhome.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StreamUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

@Slf4j
public class MyHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private byte[] requestBody = null;

    /**
     * 复制request body内容并且缓存
     * @param request req
     */
    MyHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        try {
            requestBody = StreamUtils.copyToByteArray(request.getInputStream());
        } catch (IOException e) {
            log.error("=============copy request body failed" ,e);
        }
    }

    /**
     * 重写getInputStream方法,返回缓存
     */
    @Override
    public ServletInputStream getInputStream() {
        if (requestBody == null) {
            requestBody = new byte[0];
        }

        final ByteArrayInputStream newInputStream = new ByteArrayInputStream(requestBody);

        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener listener) {

            }

            @Override
            public int read() {
                return newInputStream.read();
            }
        };
    }

    /**
     * 重写getReader方法,返回缓存
     */
    @Override
    public BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }
}
