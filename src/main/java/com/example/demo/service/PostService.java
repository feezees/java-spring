package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.PostRepository;
import com.example.demo.entity.Post;

import java.util.List;

@Service
public class PostService {
    
    @Autowired
    private PostRepository postRepository;

    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }
}
