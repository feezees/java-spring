package com.example.demo.controller;

import com.example.demo.DemoApplication;
import com.example.demo.entity.Note;
import com.example.demo.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    // @PostMapping
    // public int logObject(@RequestBody Object object) {
    //     DemoApplication.logCounterResponse(object);
    //     return 52; 
    // }
    
    @Autowired
    private NoteService noteService;

    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id) {
        return noteService.getNoteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // @GetMapping("/user/{userId}")
    // public ResponseEntity<List<Note>> getNotesByUserId(@PathVariable String userId) {
    //     List<Note> notes = noteService.getNotesByUserId(userId);
    //     return ResponseEntity.ok(notes);
    // }

    // @PostMapping
    // public ResponseEntity<Note> createNote(@RequestParam String userId, @RequestParam String value) {
    //     Note note = noteService.createNote(userId, value);
    //     return ResponseEntity.ok(note);
    // }

    // @PutMapping("/{id}")
    // public ResponseEntity<Note> updateNote(@PathVariable Long id, @RequestParam String value) {
    //     try {
    //         Note updatedNote = noteService.updateNote(id, value);
    //         return ResponseEntity.ok(updatedNote);
    //     } catch (RuntimeException e) {
    //         return ResponseEntity.notFound().build();
    //     }
    // }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
    //     noteService.deleteNote(id);
    //     return ResponseEntity.ok().build();
    // }
} 