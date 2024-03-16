package com.wamazon.app.Model;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.persistence.Id;

@Entity
public class LogEntryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime timestamp;
    private String message;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;

    public LogEntryModel() {
        this.timestamp = LocalDateTime.now();
    }

    public LogEntryModel(String message, UserModel user) {
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.user = user;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
