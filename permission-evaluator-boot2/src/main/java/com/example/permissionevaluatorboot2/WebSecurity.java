package com.example.permissionevaluatorboot2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class WebSecurity {
    public boolean check(Authentication authentication, HttpServletRequest request) {
        if (authentication == null) {
            return false;
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        if (userDetails.getUsername() == "user1") {


            return true;
        }
        else {
            return false;
        }
    }

}
