package com.example.demo.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Note;
import com.example.demo.entity.Post;
// import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.model.PostBody;
import com.example.demo.repository.NoteRepository;
import com.example.demo.repository.PostRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Component
@Order(1)
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private PostRepository postRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void run(String... args) {
        try {
            // Create notes table if it doesn't exist
            // entityManager.createNativeQuery(
            //     "CREATE TABLE IF NOT EXISTS notes (id BIGINT AUTO_INCREMENT PRIMARY KEY, user_id VARCHAR(36) NOT NULL, note_value VARCHAR(255) NOT NULL)"
            // ).executeUpdate();

         
            User user1 = new User("username1", "admin");
            User user2 = new User("username2", "moderator");
            User user3 = new User("username3", "user");

            Note note1 = new Note(user1.getId(), "Первая заметка пользователя 1");
            // Note note2 = new Note(user1.getId(), "Вторая заметка пользователя 1");
            // Note note3 = new Note(user2.getId(), "Первая заметка пользователя 2");
            // Note note4 = new Note(user2.getId(), "Важная заметка пользователя 2");
            // Note note5 = new Note(user3.getId(), "Единственная заметка пользователя 3");

            noteRepository.saveAll(Arrays.asList(note1));

            PostBody pb1 = new PostBody("text", "lorem1");
            PostBody pb2 = new PostBody("image", "https://upload.wikimedia.org/wikipedia/commons/b/b6/Image_created_with_a_mobile_phone.png");
            PostBody pb3 = new PostBody("text", "lorem2");

            Post post1 = new Post( user1.getId() , List.of(pb1, pb2, pb3));
            Post post2 = new Post( user2.getId() , List.of(pb3, pb2, pb1));

            postRepository.saveAll(Arrays.asList(post1, post2));

            // UUID.randomUUID()


            // noteRepository.saveAll(Arrays.asList(note1, note2, note3, note4, note5));

            System.out.println("Sample data initialized successfully!");
        } catch (Exception e) {
            System.err.println("Error initializing sample data: " + e.getMessage());
            e.printStackTrace();
        }
    }
}