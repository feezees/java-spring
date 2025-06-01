package com.example.demo.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.example.demo.entity.User;
import com.example.demo.repository.NoteRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.ToppingRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.data.UserDataGenerator;
import com.example.demo.data.NoteDataGenerator;
import com.example.demo.data.PostDataGenerator;
import com.example.demo.data.ToppingDataGenerator;

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

        @Autowired
        private ToppingRepository toppingRepository;

        @PersistenceContext
        private EntityManager entityManager;

        @Override
        @Transactional
        public void run(String... args) {
                try {
                        List<User> users = UserDataGenerator.generateUsers(userRepository);

                        NoteDataGenerator.generateNotes(noteRepository, users);

                        PostDataGenerator.generatePosts(postRepository, users);

                        ToppingDataGenerator.generateToppings(toppingRepository);

                        System.out.println("Sample data initialized successfully!");
                } catch (Exception e) {
                        System.err.println("Error initializing sample data: " + e.getMessage());
                        e.printStackTrace();
                }
        }
}