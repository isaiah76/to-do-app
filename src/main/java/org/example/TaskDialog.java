package org.example;

import javax.swing.*;
import java.awt.*;

public class TaskDialog extends JDialog {
    private final JTextField titleField = new JTextField();
    private final JTextArea descriptionArea = new JTextArea();
    private boolean confirmed = false;

    TaskDialog(JFrame parent, String title) {
        super(parent, title, true);
        setLayout(new BorderLayout());

        add(createFormPanel(), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(parent);
    }

    JPanel createFormPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 1, 5, 5));
        panel.add(new JLabel("Title:"));
        panel.add(titleField);
        panel.add(new JLabel("Description:"));
        panel.add(descriptionArea);
        return panel;
    }

    JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        JButton saveBtn = new JButton("Save");
        JButton cancelBtn = new JButton("Cancel");

        saveBtn.addActionListener(e -> {
            confirmed = true;
            dispose();
        });

        cancelBtn.addActionListener(e -> {
            dispose();
        });

        panel.add(saveBtn);
        panel.add(cancelBtn);
        return panel;
    }

    public void setTitleText(String title) {
        titleField.setText(title);
    }

    public void setDescriptionText(String description) {
        descriptionArea.setText(description);
    }

    public String getTitleText() {
        return titleField.getText();
    }

    public String getDescriptionText() {
        return descriptionArea.getText();
    }

    public boolean isConfirmed() {
        return confirmed;
    }
}
