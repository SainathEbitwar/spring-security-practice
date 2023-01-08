package com.therealsainath.config;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class CustomAuthManager implements AuthenticationManager {

    private final CustomAuthProvider customAuthProvider;

    public CustomAuthManager(CustomAuthProvider customAuthProvider) {
        this.customAuthProvider = customAuthProvider;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        if (customAuthProvider.supports(authentication.getClass())) {
            return customAuthProvider.authenticate(authentication);
        }
        else
            throw new BadCredentialsException("Bad Creds :(");
    }
}
