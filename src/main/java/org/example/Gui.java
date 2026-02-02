package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
                markTask();
            }
        });

        list1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Task selected = (Task) list1.getSelectedValue();
                if (selected != null) {
                    markCB.setSelected(selected.isCompleted());
                    markCB.setEnabled(true);
                } else {
                    markCB.setSelected(false);
                    markCB.setEnabled(false);
                }
            }
        });
    }

    void loadTasks() throws SQLException {
        listModel.clear();
        java.util.List<Task> tasks = tm.getAllTasks();
        tasks.forEach(listModel::addElement);

        // sync checkbox
        Task selected = (Task) list1.getSelectedValue();
        if (selected != null) {
            markCB.setSelected(selected.isCompleted());
            markCB.setEnabled(true);
        } else {
            markCB.setSelected(false);
            markCB.setEnabled(false);
        }
    }

    void createTask() {
        TaskDialog dialog = new TaskDialog(this, "Create Task");
        dialog.setVisible(true);

        if (dialog.isConfirmed()) {
            String title = dialog.getTitleText(), description = dialog.getDescriptionText();
            if (!title.isEmpty()) {
                try {
                    tm.addTask(title, description);
                    loadTasks();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Error adding task: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Title cannot be empty", "Validation Error", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    void editTask() {
        Task selected = getSelectedTask();
        if  (selected == null) return;

        TaskDialog dialog = new TaskDialog(this, "Edit Task");
        dialog.setTitleText(selected.getTitle());
        dialog.setDescriptionText(selected.getDescription());
        dialog.setVisible(true);

        if (dialog.isConfirmed()){
            String newTitle = dialog.getTitleText(), newDescription =  dialog.getDescriptionText();

            if (newTitle.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Title cannot be empty", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                tm.updateTask(selected.getId(), newTitle, newDescription);
                loadTasks();
            } catch (SQLException e){

            }
        }
    }

    void deleteTask() {
        Task selected = getSelectedTask();
        if (selected == null) return;

        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this task?", "Confirm Delete", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                tm.deleteTask(selected.getId());
                loadTasks();
            } catch (SQLException e) {
            }
        }
    }

    void markTask() {
        Task selected = getSelectedTask();
        if (selected == null) return;

        try {
            if (markCB.isSelected()) {
                tm.markCompleted(selected.getId());
            } else {
                tm.unmarkCompleted(selected.getId());
            }
            loadTasks();
        } catch (SQLException e) {
        }
    }

    Task getSelectedTask() {
        Task selected = (Task) list1.getSelectedValue();
        if (selected == null)
            JOptionPane.showMessageDialog(this, "Please select a task", "No Selection", JOptionPane.WARNING_MESSAGE);
        return selected;
    }
}
