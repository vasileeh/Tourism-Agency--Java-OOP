package com.tourismagency.gui;

import com.tourismagency.utils.DatabaseUtils;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.sql.*;

public class TouristPackagesWindow extends JFrame {
    private JPanel packagesPanel;

    public TouristPackagesWindow() {
        setTitle("Available Tourist Packages");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());


        getContentPane().setBackground(Color.decode("#004D40"));

        packagesPanel = new JPanel();
        packagesPanel.setLayout(new GridLayout(0, 2, 15, 15));
        packagesPanel.setBackground(Color.decode("#004D40"));

        JScrollPane scrollPane = new JScrollPane(packagesPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        scrollPane.getViewport().setBackground(Color.decode("#004D40"));

        loadPackages();
        JButton filterButton = new JButton("Filter");
        styleButton(filterButton);

        filterButton.addActionListener(e -> filterPackages());


        add(scrollPane, BorderLayout.CENTER);
        add(filterButton, BorderLayout.SOUTH);
    }

    private void loadPackages() {
        try (Connection connection = DatabaseUtils.connect()) {
            String sql = "SELECT * FROM tourist_packages WHERE available = true";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String destination = resultSet.getString("destination");
                int duration = resultSet.getInt("duration");
                double price = resultSet.getDouble("price");
                String description = resultSet.getString("description");
                int packageId = resultSet.getInt("id");

                JPanel packagePanel = createPackagePanel(destination, duration, price, description, packageId);
                packagesPanel.add(packagePanel);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private JPanel createPackagePanel(String destination, int duration, double price, String description, int packageId) {
        JPanel panel = new JPanel();
        panel.setBorder(new LineBorder(Color.decode("#009688"), 1, true));
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(350, 150));

        JLabel destinationLabel = new JLabel(destination);
        destinationLabel.setFont(new Font("Arial", Font.BOLD, 16));
        destinationLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel durationLabel = new JLabel("Days: " + duration);
        durationLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel priceLabel = new JLabel("Price: $" + price);
        priceLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton detailsButton = new JButton("Details");
        styleButton(detailsButton); // Style the details button
        detailsButton.addActionListener(e -> openPackageDetails(destination, duration, price, description, packageId));

        panel.add(destinationLabel, BorderLayout.NORTH);
        panel.add(durationLabel, BorderLayout.CENTER);
        panel.add(priceLabel, BorderLayout.SOUTH);
        panel.add(detailsButton, BorderLayout.SOUTH);

        return panel;
    }

    private void openPackageDetails(String destination, int duration, double price, String description, int packageId) {
        PackageDetailsWindow detailsWindow = new PackageDetailsWindow(destination, duration, price, description, packageId);
        detailsWindow.setVisible(true);
    }

    private void filterPackages() {
        String input = JOptionPane.showInputDialog(this, "Enter max price or duration (in days):");
        if (input != null) {
            try {
                double filterValue = Double.parseDouble(input);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid input.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void styleButton(JButton button) {
        button.setBackground(Color.decode("#FF7043"));
        button.setForeground(Color.WHITE); // White text
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
}
