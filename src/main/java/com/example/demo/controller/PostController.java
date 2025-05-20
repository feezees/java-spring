package com.example.demo.controller;

import java.util.List;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Cookies;
import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.model.PostBody;
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
    public ResponseEntity<?> createPost(HttpServletRequest request, @RequestBody Post post, HttpServletResponse response) {

        // check cookie
        String cookieUser = cookieEntity.getCookiesUser(request);
        if (cookieUser == "undefined"){
            return ResponseEntity.ok().body("Cookie user is undefined");
        }

        System.out.println(cookieUser);

        User creator = userRepository.findByUsername(cookieUser);

        if (creator == null) {
            return ResponseEntity.ok().body("User not found for cookie: " + cookieUser);
        }

        List<PostBody> postBodyList = post.getPostBody();

        if (postBodyList == null || postBodyList.isEmpty()) {
            return ResponseEntity.ok().body("Post body is null or empty");
        }

        for (PostBody body : postBodyList) {
            if (body.getBodyType() == null || body.getBodyValue() == null) {
                return ResponseEntity.ok().body("Post body element missing bodyType or bodyValue");
            }
            if (!body.getBodyType().equals("text") && !body.getBodyType().equals("image")) {
                return ResponseEntity.ok().body("Invalid bodyType: " + body.getBodyType());
            }
            if (!(body.getBodyValue() instanceof String)) {
                return ResponseEntity.ok().body("bodyValue is not a String");
            }
        }

        post.setAuthor(creator.getId());

        try {
            postService.savePost(post);
            return ResponseEntity.ok().body(post);
        } catch (Exception e) {
            return ResponseEntity.ok().body("Error saving post: " + e.getMessage());
        }
    }
}
