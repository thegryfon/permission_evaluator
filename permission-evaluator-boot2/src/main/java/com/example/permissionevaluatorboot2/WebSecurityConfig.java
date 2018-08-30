package com.example.permissionevaluatorboot2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

import java.io.Serializable;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomPermissionEvaluator customPermissionEvaluator;

    @Bean
    public UserDetailsService userDetailsService() {
        // Spring Boot 2 default PasswordEncoder is built as a DelegatingPasswordEncoder. Using
        // {noop} will force DelegatingPasswordEncoder to use NoOpPasswordEncoder
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user1").password("{noop}pass").roles("USER")
                .build());
        manager.createUser(User.withUsername("user2").password("{noop}pass").roles("USER").build());
        return manager;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        handler.setPermissionEvaluator(customPermissionEvaluator);
        web.expressionHandler(handler);
    }

//    @Bean(name = "webSecurity")
    public com.example.permissionevaluatorboot2.WebSecurity webSecurity() {
        return new com.example.permissionevaluatorboot2.WebSecurity();
    }

    @Bean
    public PermissionEvaluator evaluator() {
        return new PermissionEvaluator() {
            @Override
            public boolean hasPermission(Authentication authentication, Object o, Object o1) {
                return false;
            }

            @Override
            public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
                return false;
            }
        };
    }
}