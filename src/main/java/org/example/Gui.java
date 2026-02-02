package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Gui extends JFrame {
    private final DefaultListModel<Task> listModel = new DefaultListModel<>();
    private JList list1;
    private JPanel mainPanel;
    private JButton createBtn;
    private JButton editBtn;
    private JButton deleteBtn;
    private JPanel bottomPanel;
    private JCheckBox markCB;
    private JPanel spacerPanel;

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

        list1.setModel(listModel);
        loadTasks();

        createBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                createTask();
            }
        });

        editBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                editTask();
            }
        });

        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                deleteTask();
            }
        });

        markCB.addActionListener(new ActionListener() {
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
        TaskDialog dialog = new TaskDialog(this, "Create Task");
        dialog.setVisible(true);

        if (dialog.isConfirmed()){
            String title = dialog.getTitleText(), description = dialog.getDescriptionText();
            if (!title.isEmpty()){
               try {
                   tm.addTask(title, description);
                   loadTasks();
               } catch (Exception e){
                   JOptionPane.showMessageDialog(this, "Error adding task: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
               }
            } else {
                JOptionPane.showMessageDialog(this, "Title cannot be empty", "Validation Error", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    void editTask() {

    }

    void deleteTask() {

    }
}
