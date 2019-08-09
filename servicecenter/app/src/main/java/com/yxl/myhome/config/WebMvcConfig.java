package com.yxl.myhome.config;

import com.yxl.myhome.intercepter.ApiAuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author BG343094
 * @since 2019/8/6 16:10
 **/
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {



    @Bean
    public ApiAuthInterceptor getApiInterceptor() {
        return new ApiAuthInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getApiInterceptor()).addPathPatterns("/api/**");
    }

}
