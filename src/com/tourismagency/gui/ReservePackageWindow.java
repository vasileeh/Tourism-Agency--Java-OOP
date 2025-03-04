package com.tourismagency.gui;

import com.tourismagency.utils.DatabaseUtils;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReservePackageWindow extends JFrame {
    private int packageId;
    private String clientName;
    private String clientEmail;

    public ReservePackageWindow(int packageId, String clientName, String clientEmail) {
        this.packageId = packageId;
        this.clientName = clientName;
        this.clientEmail = clientEmail;
        setTitle("Rezervare Pachet");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 1));

        getContentPane().setBackground(Color.decode("#E0F7FA"));

        add(new JLabel("ID Pachet: " + packageId));
        add(new JLabel("Nume Client: " + clientName));
        add(new JLabel("Email Client: " + clientEmail));

        JButton reserveButton = createStyledButton("Confirmă Rezervarea");
        reserveButton.addActionListener(e -> reservePackage());
        add(reserveButton);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(Color.WHITE);
        button.setForeground(Color.decode("#00796B"));
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.decode("#00796B"), 1));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Cursor tip mână

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.decode("#E0F7FA"));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.WHITE);
            }
        });

        return button;
    }

    private void reservePackage() {
        try (Connection connection = DatabaseUtils.connect()) {
            String sql = "UPDATE tourist_packages SET reserved_by_name = ?, reserved_by_email = ?, available = false WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, clientName);
            statement.setString(2, clientEmail);
            statement.setInt(3, packageId);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(this, "Pachet rezervat cu succes!");
            dispose();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Eroare la baza de date: " + e.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
        }
    }
}
