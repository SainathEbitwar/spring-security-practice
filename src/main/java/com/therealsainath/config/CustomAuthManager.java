package com.therealsainath.config;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;


public class CustomAuthManager implements AuthenticationManager {

    private final String key;

    public CustomAuthManager(String key) {
        this.key = key;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        var customAuthProvider = new CustomAuthProvider(key);

        if (customAuthProvider.supports(authentication.getClass())) {
            return customAuthProvider.authenticate(authentication);
        }
        else
            throw new BadCredentialsException("Bad Creds :(");
    }
}
