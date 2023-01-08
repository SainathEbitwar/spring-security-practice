package com.therealsainath.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class CustomAuthFilter extends OncePerRequestFilter {

    private final CustomAuthManager customAuthManager;

    public CustomAuthFilter(CustomAuthManager customAuthManager) {
        this.customAuthManager = customAuthManager;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String key = String.valueOf(request.getHeader("key"));

        CustomAuthentication customAuthentication = new CustomAuthentication(key, false);

        Authentication authentication = customAuthManager.authenticate(customAuthentication);

        if(authentication.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        else
            response.setStatus(401);

        filterChain.doFilter(request, response);

    }
}
