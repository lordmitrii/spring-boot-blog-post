package com.example.blog.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.blog.dto.BlogDto;
import com.example.blog.service.BlogService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/blogs")
public class BlogController {
    private final BlogService blogService;

    @GetMapping
    public String listBlogs(Model model) {
        List<BlogDto> blogs = blogService.findAllBlogs();
        model.addAttribute("blogs", blogs);
        return "pages/blogs"; 
    }

    @GetMapping("/latest")
    public String latestBlogs(Model model) {
        // TODO: Implement pagination for the latest blogs
        List<BlogDto> blogs = blogService.findLatestFiveBlogs();
        model.addAttribute("blogs", blogs);
        return "pages/latestBlogs";
    }

    @GetMapping("/{id}")
    public String viewBlog(@PathVariable Long id, Model model) {
        BlogDto blog = blogService.findBlogById(id);
        model.addAttribute("blog", blog);
        return "pages/viewBlog";
    }

    @GetMapping("/add")
    public String showCreateForm(Model model) {
        model.addAttribute("blog", new BlogDto());
        return "pages/addBlog";
    }

    @PostMapping("/add/")
    public String createBlog(@ModelAttribute("blog") BlogDto blogDto) {
        blogService.createBlog(blogDto);
        return "redirect:/blogs";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        BlogDto blogDto = blogService.findBlogById(id);
        model.addAttribute("blog", blogDto);
        return "pages/AddBlog"; 
    }

    @PostMapping("/edit/{id}")
    public String updateBlog(@PathVariable Long id, @ModelAttribute("blogDto") BlogDto blogDto) {
        blogDto.setId(id);
        blogService.updateBlog(blogDto);
        return "redirect:/blogs";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBlog(@PathVariable Long id) {
        blogService.deleteBlog(id);
        return "redirect:/blogs"; 
    }
}