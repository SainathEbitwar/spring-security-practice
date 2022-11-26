package com.therealsainath.config;

import com.therealsainath.security.CustomAuthenticationProvider;
import com.therealsainath.security.CustomFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class ProjectSecurityConfigs extends WebSecurityConfigurerAdapter {


    @Autowired
    CustomAuthenticationProvider customAuthenticationProvider;
    @Autowired
    CustomFilter customFilter;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();

        http.csrf().disable();

        http.addFilterAt(customFilter, BasicAuthenticationFilter.class);

        http.authorizeRequests()
                .anyRequest().permitAll();

//        http.authorizeRequests()
//                .mvcMatchers("/user").permitAll()
//                .anyRequest().authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthenticationProvider);
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


}
