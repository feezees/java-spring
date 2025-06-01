package com.example.demo.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.model.FailedResponseBody;
import com.example.demo.model.PostBody;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.PostService;
import com.example.demo.service.CookieService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = { "http://localhost:8080", "http://localhost:3000", "http://localhost:5500",
        "http://127.0.0.1:5500" })
public class PostController {

    private final PostService postService;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CookieService cookieService;

    @Autowired
    public PostController(PostService postService, UserRepository userRepository, PostRepository postRepository, CookieService cookieService) {
        this.postService = postService;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.cookieService = cookieService;
    }

    private FailedResponseBody failedResponseBody = new FailedResponseBody(HttpStatus.NOT_FOUND);

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{username}")
    // ResponseEntity<List<Post>>
    public ResponseEntity<Object> getUserPosts(@PathVariable String username) {

        try {
            User user = userRepository.findByUsername(username);
            UUID author = user.getId();

            List<Post> posts = postRepository.findByAuthor(author);

            // System.out.println(posts);
            return ResponseEntity.ok(posts);

        } catch (Exception e) {
            e.printStackTrace(); // Log the error
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(failedResponseBody);
        }
    }

    @PutMapping
    public ResponseEntity<?> createPost(HttpServletRequest request, @RequestBody Post post,
            HttpServletResponse response) {

        User creator = cookieService.getUserByCookie(request);
        if (creator == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found for cookie");
        }

        List<PostBody> postBodyList = post.getPostBody();

        if (postBodyList == null || postBodyList.isEmpty()) {
            return ResponseEntity.badRequest().body("Post body is null or empty");
        }

        for (PostBody body : postBodyList) {
            if (body.getBodyType() == null || body.getBodyValue() == null) {
                return ResponseEntity.badRequest().body("Post body element missing bodyType or bodyValue");
            }
            if (!body.getBodyType().equals("text") && !body.getBodyType().equals("image")) {
                return ResponseEntity.badRequest().body("Invalid bodyType: " + body.getBodyType());
            }
            if (!(body.getBodyValue() instanceof String)) {
                return ResponseEntity.badRequest().body("bodyValue is not a String");
            }
        }

        post.setAuthor(creator.getId());

        try {
            postService.savePost(post);
            return ResponseEntity.ok().body(post);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving post: " + e.getMessage());
        }
    }
}
