package com.example.demo.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.annotation.RequireCookie;
import com.example.demo.dto.NoteRequest;
import com.example.demo.entity.Note;
import com.example.demo.entity.User;
import com.example.demo.service.NoteService;
import com.example.demo.service.CookieService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/notes")
@CrossOrigin(origins = { "http://localhost:8080", "http://localhost:3000", "http://localhost:5500",
        "http://127.0.0.1:5500" })
        @RequireCookie
        public class NoteController {

    private final NoteService noteService;
    private final CookieService cookieService;
    

    @Autowired
    public NoteController(NoteService noteService, CookieService cookieService) {
        this.noteService = noteService;
        this.cookieService = cookieService;
    }


    @GetMapping
    public List<Note> getAllNotes() {
        return noteService.getAllNotes();
    }

    @GetMapping("/{id}")
    // public ResponseEntity<Note> todo грязь
    public ResponseEntity<Note> getNoteById(@PathVariable Long id, HttpServletRequest req, HttpServletResponse res) {
        Optional<Note> optionalNote = noteService.getNoteById(id);

        if (optionalNote.isPresent()) {
            return ResponseEntity.ok(optionalNote.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user/{userId}")
    // ResponseEntity<List<Note>>
    public ResponseEntity<Object> getNotesByUserId(@PathVariable UUID userId, HttpServletRequest req,
            HttpServletResponse res) {
        List<Note> resBody = noteService.getNotesByUserId(userId);

        if (resBody == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(resBody);
    }

    @PostMapping
    @RequireCookie
    public ResponseEntity<Note> createNote(@RequestBody NoteRequest request, HttpServletRequest httpRequest) {
        User cookieUser = cookieService.getUserByCookie(httpRequest);
        if (cookieUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Note note = noteService.createNote(cookieUser.getId(), request.getNoteValue());
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