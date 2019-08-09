package com.yxl.myhome.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;

/**
 * @author BG343094
 * @since 2019/8/6 20:21
 **/
@Getter
@Setter
public class CustomSecurityContext implements SecurityContext {
    private Authentication authentication;

    public CustomSecurityContext(Authentication authentication) {
        setAuthentication(authentication);
    }
}
