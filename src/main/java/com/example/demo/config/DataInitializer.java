package com.example.demo.config;

import com.example.demo.entity.Note;
import com.example.demo.repository.NoteRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.Arrays;

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
                "CREATE TABLE IF NOT EXISTS notes (id BIGINT AUTO_INCREMENT PRIMARY KEY, user_id VARCHAR(255) NOT NULL, note_value VARCHAR(255) NOT NULL)"
            ).executeUpdate();

            // Create some sample notes
            Note note1 = new Note("user1", "Первая заметка пользователя 1");
            Note note2 = new Note("user1", "Вторая заметка пользователя 1");
            Note note3 = new Note("user2", "Первая заметка пользователя 2");
            Note note4 = new Note("user2", "Важная заметка пользователя 2");
            Note note5 = new Note("user3", "Единственная заметка пользователя 3");

            // Save notes to database
            noteRepository.saveAll(Arrays.asList(note1, note2, note3, note4, note5));

            System.out.println("Sample data initialized successfully!");
        } catch (Exception e) {
            System.err.println("Error initializing sample data: " + e.getMessage());
            e.printStackTrace();
        }
    }
}