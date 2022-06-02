package com.sparta.security0531.controller;

import com.sparta.security0531.dto.BlogRequestDto;
import com.sparta.security0531.model.Blog;
import com.sparta.security0531.repository.BlogRepository;
import com.sparta.security0531.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BlogController {

    private final BlogRepository blogRepository;
    private final BlogService blogService;

    @PostMapping("/api/blogs")
    public Blog postBlog(@AuthenticationPrincipal @RequestBody BlogRequestDto requestDto){
        Blog blog = new Blog(requestDto);
        return blogRepository.save(blog);
    }

    @GetMapping("/api/blogs")
    public List<Blog> getBlog(){
        return blogRepository.findAllByOrderByModifiedAtDesc();
    }

    @DeleteMapping("/api/blogs/{id}")
    public Long deleteBlog(@AuthenticationPrincipal @PathVariable Long id){
        blogRepository.deleteById(id);
        return id;
    }

    @PutMapping("/api/blogs/{id}")
    public Long updateBlog(@AuthenticationPrincipal @PathVariable Long id, @RequestBody BlogRequestDto requestDto){
        blogService.update(id, requestDto);
        return id;
    }
}
