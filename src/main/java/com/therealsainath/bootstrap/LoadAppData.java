package com.therealsainath.bootstrap;

import com.therealsainath.entity.User;
import com.therealsainath.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class LoadAppData implements CommandLineRunner {

    @Autowired
    private final UserRepository userRepository;

    public LoadAppData(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        User user1 = new User();
        user1.setUsername("Sai");
        user1.setPassword("12345");

        User user2 = new User();
        user2.setUsername("Unknown");
        user2.setPassword("Unknown");
        userRepository.save(user1);
        userRepository.save(user2);
    }
}
