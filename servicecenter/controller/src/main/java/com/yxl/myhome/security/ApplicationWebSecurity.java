package com.yxl.myhome.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter;

import javax.annotation.Resource;

/**
 * @author BG343094
 * @since 2019/8/6 19:59
 **/
@EnableWebSecurity
public class ApplicationWebSecurity extends WebSecurityConfigurerAdapter {
    @Resource
    private WebAccessDeniedHandler webAccessDeniedHandler;
    @Resource
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    @Resource
    private AuthenticationFilter authenticationFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().httpBasic().disable().formLogin().disable().logout().disable();
        http.exceptionHandling()
                .accessDeniedHandler(webAccessDeniedHandler)
                .authenticationEntryPoint(customAuthenticationEntryPoint);
        http.addFilterAfter(authenticationFilter, SecurityContextHolderAwareRequestFilter.class);
        http.authorizeRequests().antMatchers("/pub/**", "/api/**").permitAll();
        http.authorizeRequests().anyRequest().authenticated();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().mvcMatchers("/**/*.ico", "/**/error");
    }
}