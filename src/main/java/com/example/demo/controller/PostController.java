package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Cookies;
import com.example.demo.entity.Post;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.PostService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = { "http://localhost:8080", "http://localhost:3000", "http://localhost:5500",
        "http://127.0.0.1:5500" })
public class PostController {
    public Cookies cookieEntity = new Cookies();

    @Autowired
    private PostService postService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @PutMapping
    public Boolean createPost(HttpServletRequest request, @RequestBody Object requestBody, HttpServletResponse response) {

        // check cookie
        String cookieUser = cookieEntity.getCookiesUser(request);
        if (cookieUser == "undefined"){
            return false;
        }

        Boolean userIsExist = userRepository.existsByUsername(cookieUser);

        if (!userIsExist){
            return false;
        }


        // create body
        // TODO: Replace 'Object' with a specific DTO class for the request body
        System.out.println(requestBody);


        // push into h2

        return true;
    }
}
