package com.tourismagency.gui;

import com.tourismagency.utils.DatabaseUtils;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ApplyDiscountWindow extends JFrame {
    private JTextField idField;
    private JTextField discountField;

    public ApplyDiscountWindow() {
        setTitle("Aplică Reducere");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(3, 2, 10, 10));
        getContentPane().setBackground(Color.decode("#004D40"));

        JLabel idLabel = new JLabel("Introdu ID-ul pachetului:");
        idLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        idLabel.setHorizontalAlignment(SwingConstants.CENTER);
        idLabel.setForeground(Color.WHITE);

        JLabel discountLabel = new JLabel("Introdu procentul reducerii:");
        discountLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        discountLabel.setHorizontalAlignment(SwingConstants.CENTER);
        discountLabel.setForeground(Color.WHITE);

        idField = createStyledTextField();
        discountField = createStyledTextField();

        JButton applyButton = createStyledButton("Aplică");
        applyButton.addActionListener(e -> applyDiscount(idField.getText(), discountField.getText()));

        add(idLabel);
        add(idField);
        add(discountLabel);
        add(discountField);
        add(applyButton);
    }

    private JTextField createStyledTextField() {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textField.setBorder(BorderFactory.createLineBorder(Color.decode("#00796B"), 1));
        textField.setBackground(Color.WHITE);
        return textField;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(Color.decode("#FF7043"));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
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

    private void applyDiscount(String packageId, String discount) {
        try (Connection connection = DatabaseUtils.connect()) {
            String sql = "UPDATE tourist_packages SET price = price - (price * ? / 100) WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, Double.parseDouble(discount));
            statement.setInt(2, Integer.parseInt(packageId));
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Reducerea a fost aplicată cu succes!");
            } else {
                JOptionPane.showMessageDialog(this, "Pachetul nu a fost găsit.", "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Eroare la baza de date: " + e.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID sau procent invalid.", "Eroare", JOptionPane.ERROR_MESSAGE);
        }
    }
}
