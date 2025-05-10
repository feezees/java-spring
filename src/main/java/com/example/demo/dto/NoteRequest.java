package com.example.demo.dto;

public class NoteRequest {
    private String userId;
    private String noteValue;

    public NoteRequest() {
    }

    public NoteRequest(String userId, String noteValue) {
        this.userId = userId;
        this.noteValue = noteValue;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNoteValue() {
        return noteValue;
    }

    public void setNoteValue(String noteValue) {
        this.noteValue = noteValue;
    }
} 