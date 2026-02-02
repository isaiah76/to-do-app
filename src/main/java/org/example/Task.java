package org.example;

import java.time.LocalDateTime;

public class Task {
    private int id;
    private String title, description;
    private boolean isCompleted;
    private LocalDateTime createdAt;

    public Task(int id, String title, String description, boolean isCompleted, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isCompleted = isCompleted;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        String date = createdAt != null ? createdAt.format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) : "";
        return String.format("[%s] | %s | %s | Created: %s", isCompleted ? "✔️" : "❌", title, description, date);
    }
}
