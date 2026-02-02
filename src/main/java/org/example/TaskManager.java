package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    public void addTask(String title, String description) throws SQLException {
        String sql = "INSERT INTO Task (title, description) VALUES (?, ?)";
        try (Connection conn = DataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, title);
            ps.setString(2, description);
            ps.executeUpdate();
        }
    }

    public List<Task> getAllTasks() throws SQLException {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM Task";
        try (Connection conn = DataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tasks.add(new Task(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getBoolean("is_completed"),
                        rs.getTimestamp("created_at").toLocalDateTime()));
            }
        }
        return tasks;
    }

    public void updateTask(int id, String title, String description) throws SQLException {
        String sql = "UPDATE Task SET title=?, description=? WHERE id=?";
        try (Connection conn = DataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setInt(3, id);
            ps.executeUpdate();
        }
    }

    public void deleteTask(int id) throws SQLException {
        String sql = "DELETE FROM Task WHERE id = ?";
        try (Connection conn = DataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public void markCompleted(int id) throws SQLException {
        String sql = "UPDATE Task SET is_completed = TRUE WHERE id = ?";
        try (Connection conn = DataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
