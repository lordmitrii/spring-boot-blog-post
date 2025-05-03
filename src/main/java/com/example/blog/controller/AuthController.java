package com.example.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.blog.dto.UserDto;
import com.example.blog.exception.UserAlreadyExistsException;
import com.example.blog.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "pages/login";   
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new UserDto());
        return "pages/register";   
    }

    @PostMapping("/register")
    @SuppressWarnings("null")
    public String register(
            @Valid @ModelAttribute("user") UserDto userDto,
            BindingResult result,
            Model model) {
        if (!userDto.getPassword().equals(userDto.getConfirmPassword())) {
            result.rejectValue("confirmPassword", null, "Passwords must match");
        }
        if (!result.hasErrors()) {
            try {
                userService.register(userDto);
            } catch (UserAlreadyExistsException e) {
                result.rejectValue("username", null, "Username already exists");
            }
        }
        if (result.hasErrors()) {
            return "pages/register";
        }
        return "redirect:/login";
    }
}
