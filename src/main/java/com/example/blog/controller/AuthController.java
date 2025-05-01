package com.example.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {
    @GetMapping("/login")
    public String login() {
        return "pages/login";   
    }

    @GetMapping("/register")
    public String register() {
        return "pages/register";   
    }

    @GetMapping("/account")
    public String account() {
        return "pages/account";   
    }
}
