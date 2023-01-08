package com.therealsainath.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class CustomAuthProvider implements AuthenticationProvider {

    @Value("${secure.key}")
    private String key;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        CustomAuthentication customAuthentication = (CustomAuthentication) authentication;
        if(key.equals(customAuthentication.getKey())) {
            return new CustomAuthentication(null, true);
        }
        else
            throw new BadCredentialsException("Bad Creds :(");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(CustomAuthentication.class);
    }
}
