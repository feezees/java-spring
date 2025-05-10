package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "note_value", nullable = false)
    private String noteValue;

    public Note() {
    }

    public Note(String userId, String noteValue) {
        this.userId = userId;
        this.noteValue = noteValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getValue() {
        return noteValue;
    }

    public void setValue(String noteValue) {
        this.noteValue = noteValue;
    }
}
