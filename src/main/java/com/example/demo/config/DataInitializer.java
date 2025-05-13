package com.example.demo.config;

import com.example.demo.entity.Note;
import com.example.demo.entity.User;
import com.example.demo.repository.NoteRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.Arrays;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private NoteRepository noteRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void run(String... args) {
        try {
            // Create notes table if it doesn't exist
            entityManager.createNativeQuery(
                "CREATE TABLE IF NOT EXISTS notes (id BIGINT AUTO_INCREMENT PRIMARY KEY, user_id VARCHAR(36) NOT NULL, note_value VARCHAR(255) NOT NULL)"
            ).executeUpdate();

            User user1 = new User("username1", "admin");
            User user2 = new User("username2", "moderator");
            User user3 = new User("username3", "user");

            Note note1 = new Note(user1.getId(), "Первая заметка пользователя 1");
            // Note note2 = new Note(user1.getId(), "Вторая заметка пользователя 1");
            // Note note3 = new Note(user2.getId(), "Первая заметка пользователя 2");
            // Note note4 = new Note(user2.getId(), "Важная заметка пользователя 2");
            // Note note5 = new Note(user3.getId(), "Единственная заметка пользователя 3");

            noteRepository.saveAll(Arrays.asList(note1));
            // noteRepository.saveAll(Arrays.asList(note1, note2, note3, note4, note5));

            System.out.println("Sample data initialized successfully!");
        } catch (Exception e) {
            System.err.println("Error initializing sample data: " + e.getMessage());
            e.printStackTrace();
        }
    }
}