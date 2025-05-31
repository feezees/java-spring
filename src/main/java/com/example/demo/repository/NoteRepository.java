package com.example.demo.repository;

import com.example.demo.entity.Note;

import jakarta.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByUserId(UUID userId);

    Optional<Note> findById(Long id);

    void deleteById(Long id);
}
