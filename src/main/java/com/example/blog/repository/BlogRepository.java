package com.example.blog.repository;
import com.example.blog.model.Blog;
import java.util.List;


public interface BlogRepository {
    List<Blog> findAll(); // Fetch all blogs from the repository

    List<Blog> findTop5ByOrderByCreatedAtDesc(); // Fetch the latest 5 blogs

    Blog findById(Long id); // Fetch a blog by its ID

    void save(Blog blog); // Save a new blog or update an existing one

    void update(Blog blog); 

    void delete(Long id); // Delete a blog by its ID
    
}
