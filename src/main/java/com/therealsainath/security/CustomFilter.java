package com.therealsainath.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class CustomFilter implements Filter {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        if(httpServletRequest.getServletPath().equals("/user")) {
            CustomAuthentication authenticationResult = new CustomAuthentication(null, null, null);
            SecurityContextHolder.getContext().setAuthentication(authenticationResult);
            chain.doFilter(request, response);
        }
        else {

            String authorization = httpServletRequest.getHeader("Authorization");

            try {

                if (authorization != null && authorization.startsWith("Basic ")) {
                    String base64creds = authorization.substring(6);
                    String[] creds = new String((Base64.getDecoder().decode(base64creds.getBytes(StandardCharsets.UTF_8)))).split(":");
                    CustomAuthentication authenticationInput = new CustomAuthentication(creds[0], creds[1]);
                    Authentication authenticationResult = authenticationManager.authenticate(authenticationInput);
                    if (authenticationResult.isAuthenticated()) {
                        SecurityContextHolder.getContext().setAuthentication(authenticationResult);
                        chain.doFilter(request, response);
                    } else
                        throw new BadCredentialsException("Oops Bad Creds!");
                } else
                    throw new BadCredentialsException("Oops Bad Creds!");
            }
            catch (AuthenticationException exception) {
                HttpServletResponse httpServletResponse = (HttpServletResponse) response;
                httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            }
        }





    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
