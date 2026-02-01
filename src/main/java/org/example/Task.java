package org.example;

import java.sql.Timestamp;

public class Task {
    public int id;
    public String title, description;
    public boolean isCompleted;
    public Timestamp createdAt;

    public Task(int id, String title, String description, boolean isCompleted, Timestamp createdAt) {
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

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return String.format("%d %s %s %s %s",
                id, title, description, isCompleted, createdAt);
    }
}
