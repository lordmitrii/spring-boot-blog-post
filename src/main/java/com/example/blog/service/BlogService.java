package com.example.blog.service;

import com.example.blog.dto.BlogDto;
import java.util.List;

public interface BlogService {
    List<BlogDto> findAllBlogs();
    List<BlogDto> findLatestFiveBlogs();
    BlogDto findBlogById(Long id);
    void createBlog(BlogDto blogDto);
    void updateBlog(BlogDto blogDto);
    void deleteBlog(Long id);
}