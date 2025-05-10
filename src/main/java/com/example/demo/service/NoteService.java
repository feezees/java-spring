package com.example.demo.service;

import com.example.demo.entity.Note;
import com.example.demo.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    public List<Note> getAllNotes(){
        return noteRepository.findAll();
    }

    public Optional<Note> getNoteById(Long id) {
        return noteRepository.findById(id);
    }

    public List<Note> getNotesByUserId(String userId) {
        return noteRepository.findByUserId(userId);
    }

    public Note createNote(String userId, String noteValue) {
        Note note = new Note(userId, noteValue);
        return noteRepository.save(note);
    }

    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }

    public Note updateNote(Long id, String value) {
        Optional<Note> existingNote = noteRepository.findById(id);
        if (existingNote.isPresent()) {
            Note note = existingNote.get();
            note.setValue(value);
            return noteRepository.save(note);
        }
        throw new RuntimeException("Note not found with id: " + id);
    }
} 