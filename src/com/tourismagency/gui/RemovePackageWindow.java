package com.tourismagency.gui;

import com.tourismagency.utils.DatabaseUtils;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RemovePackageWindow extends JFrame {
    private JTextField idField;

    public RemovePackageWindow() {
        setTitle("Elimină Pachet");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(3, 2, 10, 10));
        getContentPane().setBackground(Color.decode("#004D40"));

        JLabel label = new JLabel("Introdu ID-ul pachetului de eliminat:");
        label.setFont(new Font("SansSerif", Font.PLAIN, 14));
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(SwingConstants.CENTER);

        idField = createStyledTextField();

        JButton removeButton = createStyledButton("Elimină");
        removeButton.addActionListener(e -> removePackage(idField.getText()));

        add(label);
        add(idField);
        add(removeButton);
    }

    private JTextField createStyledTextField() {
        JTextField textField = new JTextField();
        textField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        textField.setBorder(BorderFactory.createLineBorder(Color.decode("#00796B"), 1));
        textField.setOpaque(true);
        textField.setBackground(Color.WHITE);
        return textField;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(Color.decode("#FF7043"));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.decode("#D84315"));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.decode("#FF7043"));
            }
        });

        return button;
    }

    private void removePackage(String packageId) {
        try (Connection connection = DatabaseUtils.connect()) {
            String sql = "DELETE FROM tourist_packages WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(packageId));
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Pachet eliminat cu succes!");
            } else {
                JOptionPane.showMessageDialog(this, "Pachetul nu a fost găsit.", "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Eroare la baza de date: " + e.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID invalid.", "Eroare", JOptionPane.ERROR_MESSAGE);
        }
    }
}
