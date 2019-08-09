package com.yxl.myhome.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * @author Base
 * @date 2019-01-15 15:34
 **/
@Component
public class UserAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) {
        return authentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(CustomAuthentication.class);
    }
}
