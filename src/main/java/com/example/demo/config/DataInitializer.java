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
import com.example.demo.repository.UserRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Component
@Order(1)
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

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
            User user1 = new User("admin", "admin");
            User user2 = new User("moderator", "moderator");
            User user3 = new User("user", "user");

            // Save users individually to ensure IDs are generated and available
            user1 = userRepository.save(user1);
            user2 = userRepository.save(user2);
            user3 = userRepository.save(user3);

            Note note1 = new Note(user1.getId(), "Первая заметка пользователя 1");
            Note note2 = new Note(user1.getId(), "Вторая заметка пользователя 1");
            Note note3 = new Note(user2.getId(), "Первая заметка пользователя 2");
            Note note4 = new Note(user2.getId(), "Важная заметка пользователя 2");
            Note note5 = new Note(user3.getId(), "Единственная заметка пользователя 3");

            noteRepository.saveAll(Arrays.asList(note1, note2, note3, note4, note5));

            PostBody pb1 = new PostBody("text", "lorem1");
            PostBody pb2 = new PostBody("image",
                    "https://upload.wikimedia.org/wikipedia/commons/b/b6/Image_created_with_a_mobile_phone.png");
            PostBody pb3 = new PostBody("text", "lorem2");
            PostBody pb4 = new PostBody("image",
                    "https://tsx.x5static.net/i/800x800-fit/xdelivery/files/4c/a7/d55bc529fbedcc465f790fcc7a8d.jpg");

            PostBody pb5 = new PostBody("image",
                    "https://avatars.mds.yandex.net/get-lpc/1520633/dd1796d9-d6e9-410b-8011-d8dbe7be1446/orig");

            PostBody pb6 = new PostBody("image",
                    "https://i0.wp.com/www.vsyasol.com/wp-content/uploads/2021/04/image5.jpg?fit=1507%2C1303&ssl=1");

            // Use the IDs from the saved user objects
            Post post1 = new Post(user1.getId(), List.of(pb1, pb2, pb3));
            Post post2 = new Post(user2.getId(), List.of(pb3, pb2, pb1));
            Post post3 = new Post(user2.getId(), List.of(pb3, pb4, pb5));
            Post post4 = new Post(user2.getId(), List.of(pb1, pb6, pb5));

            postRepository.saveAll(Arrays.asList(post1, post2, post3, post4));

            // UUID.randomUUID();
            // noteRepository.saveAll(Arrays.asList(note1, note2, note3, note4, note5));

            System.out.println("Sample data initialized successfully!");
        } catch (Exception e) {
            System.err.println("Error initializing sample data: " + e.getMessage());
            e.printStackTrace();
        }
    }
}