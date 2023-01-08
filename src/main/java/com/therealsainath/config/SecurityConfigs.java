package com.therealsainath.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfigs {

    private final CustomAuthFilter customAuthFilter;

    public SecurityConfigs(CustomAuthFilter customAuthFilter) {
        this.customAuthFilter = customAuthFilter;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       return http
               .addFilterAt(customAuthFilter, UsernamePasswordAuthenticationFilter.class)
               .authorizeHttpRequests().anyRequest().authenticated()
               .and()
               .build();
    }
}
