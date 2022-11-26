package com.therealsainath.security;

import com.therealsainath.service.UserDetailsManagerImpl;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsManagerImpl userDetailsManager;
    private final PasswordEncoder passwordEncoder;

    public CustomAuthenticationProvider(UserDetailsManagerImpl userDetailsManager) {
        this.userDetailsManager = userDetailsManager;
        this.passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        UserDetails userDetails = userDetailsManager.loadUserByUsername(authentication.getName());
        if(userDetails != null) {
            if(passwordEncoder.matches(String.valueOf(authentication.getCredentials()), userDetails.getPassword()))
                return new CustomAuthentication(authentication.getName(),
                        authentication.getPrincipal(), userDetails.getAuthorities());
            else
                throw new BadCredentialsException("Oops Bad Credentials!");
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> authenticationType) {
        return authenticationType.equals(UsernamePasswordAuthenticationToken.class);
    }
}
