package com.example.demo.dto;

import java.util.UUID;

public class NoteRequest {
    private String noteValue;

    public NoteRequest() {
    }

    public NoteRequest(String noteValue) {
        this.noteValue = noteValue;
    }

    public String getNoteValue() {
        return noteValue;
    }

    public void setNoteValue(String noteValue) {
        this.noteValue = noteValue;
    }
} 