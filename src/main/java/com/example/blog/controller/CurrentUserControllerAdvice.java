// src/main/java/com/example/blog/controller/CurrentUserControllerAdvice.java
package com.example.blog.controller;

import com.example.blog.model.User;
import com.example.blog.repository.UserRepository;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

@ControllerAdvice
public class CurrentUserControllerAdvice {

    private final UserRepository userRepository;

    public CurrentUserControllerAdvice(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @ModelAttribute("currentUser")
    public User getCurrentUser(Principal principal) {
        if (principal == null) {
            return null;   
        }
        return userRepository.findByUsername(principal.getName());
    }
}
