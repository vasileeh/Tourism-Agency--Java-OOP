package com.tourismagency.gui;

import javax.swing.*;
import java.awt.*;

public class AdminDashboard extends JFrame {
    public AdminDashboard() {
        setTitle("Admin Dashboard");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 1, 10, 10));
        getContentPane().setBackground(Color.decode("#004D40"));

        JButton addPackageButton = createStyledButton("Adaugă Pachet");
        JButton removePackageButton = createStyledButton("Elimină Pachet");
        JButton applyDiscountButton = createStyledButton("Aplică Reducere");
        JButton viewClientsButton = createStyledButton("Vizualizează Clienți");
        JButton backToMenuButton = createStyledButton("Înapoi la Meniu");

        addPackageButton.addActionListener(e -> openAddPackageWindow());
        removePackageButton.addActionListener(e -> openRemovePackageWindow());
        viewClientsButton.addActionListener(e -> openViewClientsWindow());
        applyDiscountButton.addActionListener(e -> openApplyDiscountWindow());

        backToMenuButton.addActionListener(e -> backToMenu());

        add(addPackageButton);
        add(removePackageButton);
        add(applyDiscountButton);
        add(viewClientsButton);
        add(backToMenuButton);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(Color.decode("#FF7043"));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.decode("#E0F7FA"), 2, true));
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

    private void openAddPackageWindow() {
        AddTouristPackageWindow addTouristPackageWindow = new AddTouristPackageWindow();
        addTouristPackageWindow.setVisible(true);
    }

    private void openRemovePackageWindow() {
        RemovePackageWindow removePackageWindow = new RemovePackageWindow();
        removePackageWindow.setVisible(true);
    }

    private void openApplyDiscountWindow() {
        ApplyDiscountWindow applyDiscountWindow = new ApplyDiscountWindow();
        applyDiscountWindow.setVisible(true);
    }

    private void backToMenu() {
        dispose();
        MainWindow mainWindow = new MainWindow();
        mainWindow.setVisible(true);
    }

    private void openViewClientsWindow() {
        ViewClientsWindow viewClientsWindow = new ViewClientsWindow();
        viewClientsWindow.setVisible(true);
    }
}
