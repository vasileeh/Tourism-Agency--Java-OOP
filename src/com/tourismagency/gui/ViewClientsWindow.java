package com.tourismagency.gui;

import com.tourismagency.utils.DatabaseUtils;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ViewClientsWindow extends JFrame {
    public ViewClientsWindow() {
        setTitle("Vizualizeaza Clienti");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        getContentPane().setBackground(Color.decode("#E0F7FA"));

        JLabel titleLabel = new JLabel("Clienti ", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setForeground(Color.decode("#004D40"));
        add(titleLabel, BorderLayout.NORTH);

        JTextArea clientsTextArea = new JTextArea();
        clientsTextArea.setEditable(false);
        clientsTextArea.setBackground(Color.WHITE);
        clientsTextArea.setForeground(Color.BLACK);
        clientsTextArea.setFont(new Font("SansSerif", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(clientsTextArea);
        add(scrollPane, BorderLayout.CENTER);

        JButton closeButton = new JButton("Închide");
        closeButton.setBackground(Color.decode("#FF7043"));
        closeButton.setForeground(Color.WHITE);
        closeButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        closeButton.setPreferredSize(new Dimension(100, 40));
        closeButton.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.decode("#E0F7FA"));
        buttonPanel.add(closeButton);
        add(buttonPanel, BorderLayout.SOUTH);

        loadClientsData(clientsTextArea);
    }

    private void loadClientsData(JTextArea textArea) {
        try (Connection connection = DatabaseUtils.connect();
             Statement statement = connection.createStatement()) {

            String sql = "SELECT destination, reserved_by_name, reserved_by_email " +
                    "FROM tourist_packages " +
                    "WHERE available = false";
            ResultSet resultSet = statement.executeQuery(sql);

            StringBuilder sb = new StringBuilder();
            while (resultSet.next()) {
                String name = resultSet.getString("reserved_by_name");
                String email = resultSet.getString("reserved_by_email");
                String destination = resultSet.getString("destination");

                sb.append("Client: ").append(name)
                        .append(", Email: ").append(email)
                        .append(", Destinație: ").append(destination)
                        .append("\n");
            }

            if (sb.length() == 0) {
                textArea.setText("Nu există rezervări momentan.");
            } else {
                textArea.setText(sb.toString());
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Eroare la baza de date: " + e.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
            }
    }
}