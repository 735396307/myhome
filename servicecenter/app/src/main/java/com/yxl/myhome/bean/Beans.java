package com.yxl.myhome.bean;

import com.yxl.myhome.security.AuthenticationResultHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author BG343094
 * @since 2019/8/6 19:42
 **/
@Configuration
public class Beans {
    @Bean
    public AuthenticationResultHandler webAuthenticationResultHandler() {
        return new AuthenticationResultHandler();
    }
}
