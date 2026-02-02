package org.example;

import javax.swing.*;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws Exception {
        System.setProperty("sun.java2d.uiScale", "2.0");
        System.setProperty("awt.useSystemAAFontSettings", "on");

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Nimbus look and feel not available! Using default");
        }

        SwingUtilities.invokeLater(() -> {
            TaskManager tm = new TaskManager();
            try {
                new Gui(tm);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        try (var conn = DataSource.getConnection()) {
            System.out.println("Connected to db: " + conn.getCatalog());
        }
    }
}
