package com.therealsainath.controller;

import com.therealsainath.entity.User;
import com.therealsainath.service.UserDetailsManagerImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final UserDetailsManagerImpl userDetailsManager;

    public TestController(UserDetailsManagerImpl userDetailsManager) {
        this.userDetailsManager = userDetailsManager;
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @PostMapping("/user")
    public void addUser(@RequestBody User user) {
        userDetailsManager.encodePasswordAndSaveUse(user);
    }

}
