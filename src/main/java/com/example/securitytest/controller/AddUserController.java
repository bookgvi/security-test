package com.example.securitytest.controller;

import com.example.securitytest.domain.User;
import com.example.securitytest.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.concurrent.ThreadLocalRandom;

@Controller
public class AddUserController {
    private final UserService userService;

    public AddUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/add_user")
    public String addUserPage() {
        String up = String.valueOf(ThreadLocalRandom.current().nextInt(100));
        userService.addUser(User.builder().userName("a"+up).password(up).build());
        return "add_user.html";
    }

    @PostMapping("/registration")
    public String addUser(User user) {
        userService.addUser(user);
        return "redirect:test.html";
    }
}
