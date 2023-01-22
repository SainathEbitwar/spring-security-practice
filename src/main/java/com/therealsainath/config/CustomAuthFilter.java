package com.therealsainath.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class CustomAuthFilter extends OncePerRequestFilter {



    private final String key;

    public CustomAuthFilter(String key) {
        this.key = key;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {


        if (request.getHeader("x-api-key") == null) {
            filterChain.doFilter(request, response);
        }
        else {
            String apiKey = request.getHeader("x-api-key");
            var customAuthManager = new CustomAuthManager(key);
            var customAuthentication = new CustomAuthentication(apiKey);
            Authentication authentication = customAuthManager.authenticate(customAuthentication);
            if(authentication.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            filterChain.doFilter(request, response);
        }





    }
}
