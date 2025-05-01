package com.example.blog.repository;

import com.example.blog.model.Blog;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Primary
@Repository
public class H2BlogRepository implements BlogRepository {

    private final JpaBlogRepository jpaRepo;

    public H2BlogRepository(JpaBlogRepository jpaRepo) {
        this.jpaRepo = jpaRepo;
    }

    @Override
    public List<Blog> findAll() {
        return jpaRepo.findAll();
    }

    @Override
    public List<Blog> findTop5ByOrderByDatePublishedDesc() {
        return jpaRepo.findTop5ByOrderByDatePublishedDesc();
    }

    @Override
    public Blog findById(Long id) {
        return jpaRepo.findById(id).orElse(null);
    }

    @Override
    public void save(Blog blog) {
        jpaRepo.save(blog);
    }

    @Override
    public void update(Blog blog) {
        jpaRepo.save(blog);
    }

    @Override
    public void delete(Long id) {
        jpaRepo.deleteById(id);
    }
}
