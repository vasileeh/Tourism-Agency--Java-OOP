package com.tourismagency.gui;

import com.tourismagency.models.TouristPackage;
import com.tourismagency.utils.DatabaseUtils;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddTouristPackageWindow extends JFrame {
    private JTextField destinationField;
    private JTextField durationField;
    private JTextField priceField;
    private JTextField descriptionField;

    public AddTouristPackageWindow() {
        setTitle("Adaugă Pachet Turistic");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10));
        getContentPane().setBackground(Color.decode("#E0F7FA"));

        JLabel destinationLabel = createStyledLabel("Destinație:");
        JLabel durationLabel = createStyledLabel("Durata (zile):");
        JLabel priceLabel = createStyledLabel("Preț:");
        JLabel descriptionLabel = createStyledLabel("Descriere:");

        destinationField = createStyledTextField();
        durationField = createStyledTextField();
        priceField = createStyledTextField();
        descriptionField = createStyledTextField();

        JButton addButton = createStyledButton("Adaugă Pachet");
        addButton.addActionListener(e -> addTouristPackage());

        add(destinationLabel);
        add(destinationField);
        add(durationLabel);
        add(durationField);
        add(priceLabel);
        add(priceField);
        add(descriptionLabel);
        add(descriptionField);
        add(addButton);
    }

    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        return label;
    }

    private JTextField createStyledTextField() {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textField.setBorder(BorderFactory.createLineBorder(Color.decode("#00796B"), 1));
        return textField;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(Color.decode("#4CAF50"));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));


        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.decode("#388E3C"));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.decode("#4CAF50"));
            }
        });

        return button;
    }

    private void addTouristPackage() {
        String destination = destinationField.getText();
        int duration;
        double price;

        try {
            duration = Integer.parseInt(durationField.getText());
            price = Double.parseDouble(priceField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Durata sau prețul sunt invalide. Te rugăm să introduci valori numerice.", "Eroare", JOptionPane.ERROR_MESSAGE);
            return;
        }

        TouristPackage touristPackage = new TouristPackage(destination, duration, price, descriptionField.getText(), new ArrayList<>());

        try (Connection connection = DatabaseUtils.connect()) {
            String sql = "INSERT INTO tourist_packages (destination, duration, price, description) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, touristPackage.getDestination());
            statement.setInt(2, touristPackage.getDuration());
            statement.setDouble(3, touristPackage.getPrice());
            statement.setString(4, touristPackage.getDescription());
            statement.executeUpdate();
            JOptionPane.showMessageDialog(this, "Pachetul a fost adăugat cu succes!");
            resetFields();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Eroare la baza de date: " + e.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void resetFields() {
        destinationField.setText("");
        durationField.setText("");
        priceField.setText("");
        descriptionField.setText("");
    }
}
