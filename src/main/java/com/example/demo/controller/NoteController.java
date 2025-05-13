package com.example.demo.controller;

import com.example.demo.DemoApplication;
import com.example.demo.dto.NoteRequest;
import com.example.demo.entity.Cookies;
import com.example.demo.entity.Note;
import com.example.demo.service.NoteService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/notes")
@CrossOrigin(origins = { "http://localhost:8080", "http://localhost:3000", "http://localhost:5500",
        "http://127.0.0.1:5500" })
public class NoteController {

    private Cookies cookies = new Cookies();

    // @PostMapping
    // public int logObject(@RequestBody Object object) {
    // DemoApplication.logCounterResponse(object);
    // return 52;
    // }

    @Autowired
    private NoteService noteService;

    @GetMapping
    public List<Note> getAllNotes() {
        return noteService.getAllNotes();
    }

    @GetMapping("/{id}")
    // public ResponseEntity<Note> todo грязь
    public ResponseEntity<Object> getNoteById(@PathVariable Long id, HttpServletRequest req, HttpServletResponse res) {
        String cookieUser = cookies.getCookiesUser(req);

        if (cookieUser == "undefined") {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("user cookie not found");
        }

        Optional<Note> n = noteService.getNoteById(id);

        if (n == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(n);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Note>> getNotesByUserId(@PathVariable UUID userId) {
        List<Note> notes = noteService.getNotesByUserId(userId);
        return ResponseEntity.ok(notes);
    }

    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody NoteRequest request) {
        Note note = noteService.createNote(request.getUserId(), request.getNoteValue());
        System.out.println(note.getUserId());
        System.out.println(note.getValue());
        return ResponseEntity.ok(note);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable Long id, @RequestBody NoteRequest request) {
        try {
            Note updatedNote = noteService.updateNote(id, request.getNoteValue());
            return ResponseEntity.ok(updatedNote);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        noteService.deleteNote(id);
        return ResponseEntity.ok().build();
    }
}