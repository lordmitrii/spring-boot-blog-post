package com.example.blog.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.blog.model.Blog;
import com.example.blog.repository.BlogRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;

    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    public void addBlog(Blog blog) {
        blogRepository.save(blog);
    }
    public void deleteBlog(Long id) {
        blogRepository.delete(id);
    }

}
