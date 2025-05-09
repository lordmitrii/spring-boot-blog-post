package com.example.blog.repository;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import com.example.blog.model.Blog;

@Profile("dev")
@Repository
public class InMemoryBlogRepository implements BlogRepository {
    private List<Blog> blogs = new ArrayList<>();
    private Long nextId = 1L; 

    public InMemoryBlogRepository() {
        Blog blog1 = Blog.builder()
                .title("First Blog")
                .content("This is the content of the first blog.")
                .author("Author 1")
                .datePublished(LocalDate.of(2023, 10, 01))
                .build();
        Blog blog2 = Blog.builder()
                .title("Second Blog")
                .content("This is the content of the second blog.")
                .author("Author 2")
                .datePublished(LocalDate.of(2023, 10, 02))
                .build();

        this.save(blog1);
        this.save(blog2);
    }

    public List<Blog> findAll() {
        return blogs;
    }

    public List<Blog> findTop5ByOrderByDatePublishedDesc() {
        return blogs.stream()
                .sorted((b1, b2) -> b2.getDatePublished().compareTo(b1.getDatePublished()))
                .limit(5)
                .toList();
    }

    public void save(Blog blog) {
        if (blog.getId() == null) { 
            blog.setId(nextId++);
        }
        blogs.add(blog);
    }

    public void delete(Long id) {
        for (int i = 0; i < blogs.size(); i++) {
            if (blogs.get(i).getId().equals(id)) {
                blogs.remove(i);
                return;
            }
        }
    }

    public Blog findById(Long id) {
        for (Blog blog : blogs) {
            if (blog.getId().equals(id)) {
                return blog;
            }
        }
        return null;
    }

    public void update(Blog blog) {
        for (int i = 0; i < blogs.size(); i++) {
            if (blogs.get(i).getId().equals(blog.getId())) {
                blogs.set(i, blog);
                return;
            }
        }
    }

}
