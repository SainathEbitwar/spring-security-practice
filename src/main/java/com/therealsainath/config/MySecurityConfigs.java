package com.therealsainath.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class MySecurityConfigs {



    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    /*@Bean
    public CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return args -> {

            User user1 = new User();
            user1.setPassword("password1");
            user1.setUserId(UUID.randomUUID());
            user1.setCreationDate(LocalDate.now());

            User user2 = new User();
            user2.setUserId(UUID.randomUUID());
            user2.setPassword("password2");
            user2.setCreationDate(LocalDate.now());

            userRepository.save(user1);
            userRepository.save(user2);

        };
    }*/
}
