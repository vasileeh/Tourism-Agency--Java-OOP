package com.tourismagency.gui;

import com.tourismagency.exceptions.InvalidLoginException;
import com.tourismagency.models.Admin;

import javax.swing.*;
import java.awt.*;

public class AdminLoginWindow extends JDialog {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public AdminLoginWindow(JFrame parent) {
        super(parent, "Admin Login", true);
        setSize(400, 250);
        setLocationRelativeTo(parent);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(Color.decode("#004D40"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(15, 15, 15, 15);

        JLabel titleLabel = new JLabel("Admin Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.decode("#E0F7FA"));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(titleLabel, gbc);
        gbc.gridwidth = 1;

        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        JButton loginButton = new JButton("Login");

        usernameField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#FF7043"), 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        passwordField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#FF7043"), 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        styleButton(loginButton);

        loginButton.addActionListener(e -> {
            try {
                Admin.getInstance().login(usernameField.getText(), new String(passwordField.getPassword()));

                MainWindow mainWindow = (MainWindow) SwingUtilities.getWindowAncestor(this);
                mainWindow.dispose();

                AdminDashboard adminDashboard = new AdminDashboard();
                adminDashboard.setVisible(true);
            } catch (InvalidLoginException ex) {
                JOptionPane.showMessageDialog(AdminLoginWindow.this, ex.getMessage(), "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        add(usernameField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        add(passwordField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2; // Center the button
        gbc.anchor = GridBagConstraints.CENTER;
        add(loginButton, gbc);
    }

    private void styleButton(JButton button) {
        button.setBackground(Color.decode("#FF7043"));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                button.setBackground(Color.decode("#D84315"));
                button.setFont(button.getFont().deriveFont(16f));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                button.setBackground(Color.decode("#FF7043"));
                button.setFont(button.getFont().deriveFont(14f));
            }
        });
    }
}
