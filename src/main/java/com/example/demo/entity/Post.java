package com.example.demo.entity;

import java.util.List;
import java.util.UUID;

import com.example.demo.model.PostBody;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "author", nullable = false)
    private UUID author;

    @ElementCollection
    @CollectionTable(name = "post_bodies", joinColumns = @JoinColumn(name = "post_id"))
    private List<PostBody> postBody;

    public Post() {
        // Default constructor required by JPA
    }

    public Post(UUID author, List<PostBody> postBody) {
        this.author = author;
        this.postBody = postBody;
    }
}
