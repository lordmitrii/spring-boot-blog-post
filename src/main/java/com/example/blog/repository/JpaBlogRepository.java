package com.example.blog.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.blog.model.Blog;

public interface JpaBlogRepository extends JpaRepository<Blog, Long>{
    List<Blog> findTop5ByOrderByDatePublishedDesc();
}