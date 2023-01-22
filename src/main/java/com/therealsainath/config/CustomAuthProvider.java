package com.therealsainath.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;


public class CustomAuthProvider implements AuthenticationProvider {

    private final String key;

    public CustomAuthProvider(String key) {
        this.key = key;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        CustomAuthentication customAuthentication = (CustomAuthentication) authentication;
        if(key.equals(customAuthentication.getKey())) {
            customAuthentication.setAuthenticated(true);
            customAuthentication.setKey(null);
            return customAuthentication;
        }
        else
            throw new BadCredentialsException("Bad Creds :(");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(CustomAuthentication.class);
    }
}
