package com.tourismagency.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainWindow extends JFrame {
    public MainWindow() {
        setTitle("Agentie Turism");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        getContentPane().setBackground(Color.decode("#004D40"));

        JLabel titleLabel = new JLabel("Bine ai venit la agentia de turism!", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(false); // Panou transparent

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);

        JButton clientButton = new JButton("Client");
        JButton adminButton = new JButton("Admin");

        styleButton(clientButton);
        styleButton(adminButton);

        clientButton.addActionListener(e -> new TouristPackagesWindow().setVisible(true));
        adminButton.addActionListener(e -> new AdminLoginWindow(this).setVisible(true));

        gbc.gridx = 0;
        buttonPanel.add(clientButton, gbc);

        gbc.gridx = 1;
        buttonPanel.add(adminButton, gbc);

        add(buttonPanel, BorderLayout.CENTER);
    }

    private void styleButton(JButton button) {
        button.setBackground(Color.decode("#FF7043"));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("SansSerif", Font.BOLD, 16));
        button.setPreferredSize(new Dimension(180, 60));
        button.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(Color.decode("#BF360C"), 2));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(Color.decode("#D84315"));
                button.setFont(button.getFont().deriveFont(18f));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(Color.decode("#FF7043"));
                button.setFont(button.getFont().deriveFont(16f));
            }
        });
    }
}
