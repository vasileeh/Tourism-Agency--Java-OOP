package com.tourismagency.gui;

import javax.swing.*;
import java.awt.*;

public class PackageDetailsWindow extends JFrame {
    private String destination;
    private int duration;
    private double price;
    private String description;
    private int packageId;

    public PackageDetailsWindow(String destination, int duration, double price, String description, int packageId) {
        this.destination = destination;
        this.duration = duration;
        this.price = price;
        this.description = description;
        this.packageId = packageId;

        setTitle("Package Details");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(6, 1));
        getContentPane().setBackground(Color.decode("#004D40"));

        JLabel destinationLabel = new JLabel("Destination: " + destination);
        JLabel durationLabel = new JLabel("Duration: " + duration + " days");
        JLabel priceLabel = new JLabel("Price: $" + price);
        JLabel descriptionLabel = new JLabel("<html>Description: " + description + "</html>");

        styleLabel(destinationLabel);
        styleLabel(durationLabel);
        styleLabel(priceLabel);
        styleLabel(descriptionLabel);

        JButton reserveButton = new JButton("Reserve Package");
        styleButton(reserveButton);

        reserveButton.addActionListener(e -> openReservePackageWindow());

        add(destinationLabel);
        add(durationLabel);
        add(priceLabel);
        add(descriptionLabel);
        add(reserveButton);
    }

    private void styleLabel(JLabel label) {
        label.setFont(new Font("SansSerif", Font.PLAIN, 14));
        label.setForeground(Color.decode("#E0F7FA"));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
    }

    private void styleButton(JButton button) {
        button.setBackground(Color.decode("#FF7043"));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
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

    private void openReservePackageWindow() {
        String clientName = JOptionPane.showInputDialog(this, "Enter your name:");
        String clientEmail = JOptionPane.showInputDialog(this, "Enter your email:");
        if (clientName != null && clientEmail != null) {
            ReservePackageWindow reserveWindow = new ReservePackageWindow(packageId, clientName, clientEmail);
            reserveWindow.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Name and email are required to reserve a package.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
