package com.example.blog.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.blog.model.Blog;
import com.example.blog.repository.BlogRepository;
import com.example.blog.service.BlogService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;

    @GetMapping("/blogs")
    public String listBlogs(Model model) {
        model.addAttribute("blogs", blogService.getAllBlogs());
        return "pages/blogs"; 
    }

    @GetMapping("/blogs/add")
    public String addBlog(Model model) {
        return "pages/addBlog"; 
    }

    @PostMapping("/blogs/add/")
    public String addBlog(@ModelAttribute Blog blog) {
        blogService.addBlog(blog);
        return "redirect:/blogs"; 
    }

    @DeleteMapping("/blogs/delete/{id}")
    public String deleteBlog(@PathVariable Long id) {
        blogService.deleteBlog(id);
        return "redirect:/blogs"; 
    }

    @GetMapping("/blogs/{id}")
    public String viewBlog(@PathVariable Long id, Model model) {
        Blog blog = blogService.getBlogById(id);
        model.addAttribute("blog", blog);
        return "pages/viewBlog"; 
    }

    @GetMapping("/blogs/edit/{id}")
    public String editBlog(@PathVariable Long id, Model model) {
        Blog blog = blogService.getBlogById(id);
        model.addAttribute("blog", blog);
        return "pages/addBlog"; 
    }

    @PostMapping("/blogs/edit/{id}")
    public String editBlog(@PathVariable Long id, @ModelAttribute Blog blog) {
        System.out.println("Editing blog with ID: " + id);
        blogService.addBlog(blog);
        blog.setId(id);
        System.out.println("Blog updated: " + blog.getId());
        return "redirect:/blogs"; 
    }
}