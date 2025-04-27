package com.example.blog.model;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class Blog {
    private Long id;
    private String title;
    private String content;
    private String author;
    private String datePublished;
}
    
