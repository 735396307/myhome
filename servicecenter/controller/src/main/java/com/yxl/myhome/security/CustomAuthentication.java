package com.yxl.myhome.security;

import com.alibaba.fastjson.JSON;
import com.yxl.myhome.po.UserPO;
import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.*;

/**
 * @author BG343094
 * @since 2019/8/6 20:22
 **/
@Data
public class CustomAuthentication implements Authentication {

    private static final long serialVersionUID = 7917422426566915254L;

    public CustomAuthentication(UserPO user) {
        this.user = user;
    }

    private UserPO user;


    /**
     * DO NOT directly invoke this in application. Use getMenuGrantedAuthority/getDataGrantedAuthority instead.
     * The Spring framework might use this.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new HierarchyGrantedAuthority("admin"));
    }
    @Override
    public Object getCredentials() {
        return user.getPassword();
    }
    @Override
    public Object getDetails() {
        return getUser();
    }
    @Override
    public Object getPrincipal() {
        return user.getUserName();
    }
    @Override
    public boolean isAuthenticated() {
        return false;
    }
    @Override
    public void setAuthenticated(boolean isAuthenticated) {
        throw new UnsupportedOperationException();
    }
    @Override
    public String getName() {
        return user.getUserName();
    }
    @Override
    public String toString() {
        return "User: " + JSON.toJSONString(user);
    }
}
