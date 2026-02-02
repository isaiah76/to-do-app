package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Gui extends JFrame {
    private final DefaultListModel<Task> listModel = new DefaultListModel<>();
    private JList<Task> list1 = new JList<>(listModel);

    private JPanel mainPanel;
    private JButton createBtn;
    private JButton editBtn;
    private JButton deleteBtn;
    private JPanel bottomPanel;

    private final TaskManager tm;

    Gui(TaskManager tm) throws SQLException {
        this.tm = tm;

        setTitle("To-Do-App");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
        GraphicsDevice gd =
                GraphicsEnvironment.getLocalGraphicsEnvironment()
                        .getDefaultScreenDevice();

        gd.setFullScreenWindow(this);

        loadTasks();

        createBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });

        editBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });

        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
    }

    void loadTasks() throws SQLException {
        listModel.clear();
        java.util.List<Task> tasks = tm.getAllTasks();
        tasks.forEach(listModel::addElement);
    }

    void createTask() {

    }

    void editTask() {

    }

    void deleteTask() {
       
    }
}
