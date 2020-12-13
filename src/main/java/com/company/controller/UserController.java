package com.company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @GetMapping("/user")
    public String userInfo() {
        return "user";
    }

    @GetMapping("/admin")
    public String adminInfo() {
        return "admin";
    }

    @PostMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String start() {
        return "login";
    }
}
