package com.example.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.blog.model.Blog;
import com.example.blog.service.BlogService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final BlogService blogService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("blogs", blogService.getLatestBlogs());
        return "pages/home";
    }
}
