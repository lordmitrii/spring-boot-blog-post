package com.example.blog.service;

import com.example.blog.dto.BlogDto;
import com.example.blog.exception.BlogNotFoundException;
import com.example.blog.model.Blog;
import com.example.blog.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;

    public BlogServiceImpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public List<BlogDto> findAllBlogs() {
        return blogRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BlogDto> findLatestFiveBlogs() {
        return blogRepository.findTop5ByOrderByDatePublishedDesc()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public BlogDto findBlogById(Long id) {
        Blog blog = blogRepository.findById(id);
        if (blog == null) {
            throw new BlogNotFoundException("Blog not found with id " + id);
        }
        return convertToDto(blog);
    }

    @Override
    public void createBlog(BlogDto dto) {
        Blog blog = new Blog();
        blog.setTitle(dto.getTitle());
        blog.setContent(dto.getContent());
        blog.setAuthor(dto.getAuthor());
        blog.setDatePublished(dto.getDatePublished() != null ? dto.getDatePublished() : LocalDate.now());
        blogRepository.save(blog);
    }

    @Override
    public void updateBlog(BlogDto dto) {
        Blog existing = blogRepository.findById(dto.getId());
        if (existing == null) {
            throw new BlogNotFoundException("Blog not found with id " + dto.getId());
        }
        existing.setTitle(dto.getTitle());
        existing.setContent(dto.getContent());
        existing.setAuthor(dto.getAuthor());
        existing.setDatePublished(dto.getDatePublished());
        blogRepository.update(existing);
    }

    @Override
    public void deleteBlog(Long id) {
        blogRepository.delete(id);
    }

    private BlogDto convertToDto(Blog blog) {
        return BlogDto.builder()
                .id(blog.getId())
                .title(blog.getTitle())
                .content(blog.getContent())
                .author(blog.getAuthor())
                .datePublished(blog.getDatePublished())
                .build();
    }
}
