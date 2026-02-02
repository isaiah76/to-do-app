package org.example;

import javax.swing.*;

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
            Gui gui = new Gui(tm);
            gui.setVisible(true);
        });

        try (var conn = DataSource.getConnection()) {
            System.out.println("Connected to db: " + conn.getCatalog());
        }
    }
}
