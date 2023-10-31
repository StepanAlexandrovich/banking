package com.example.banking.configuration;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.persistence.*;
import java.io.IOException;
import java.util.Set;

@Configuration
public class CustomUrlHandler implements AuthenticationSuccessHandler{
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if(roles.contains("ROLE_ADMIN")){
            response.sendRedirect("/admin/menu");
        }else
        if(roles.contains("ROLE_DISTRIBUTOR")){
            response.sendRedirect("/distributor/menu");
        }else
        if(roles.contains("ROLE_USER")){
            response.sendRedirect("/user/menu");
        }
    }

}
