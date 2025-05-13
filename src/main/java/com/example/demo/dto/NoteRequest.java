package com.example.demo.dto;

import java.util.UUID;

public class NoteRequest {
    private UUID userId;
    private String noteValue;

    public NoteRequest() {
    }

    public NoteRequest(UUID userId, String noteValue) {
        this.userId = userId;
        this.noteValue = noteValue;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getNoteValue() {
        return noteValue;
    }

    public void setNoteValue(String noteValue) {
        this.noteValue = noteValue;
    }
} 