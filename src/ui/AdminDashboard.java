package ui;

import javax.swing.*;
import java.awt.*;

public class AdminDashboard extends JFrame {
    private JTabbedPane tabbedPane;

    public AdminDashboard() {
        setTitle("Hospital Management System - Admin Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setResizable(true);

        // Create tabbed pane
        tabbedPane = new JTabbedPane();

        // Add tabs
        tabbedPane.addTab("Patient Management", new PatientPanel());
        tabbedPane.addTab("Doctor Management", new DoctorPanel());
        tabbedPane.addTab("Appointments", new AppointmentPanel());
        tabbedPane.addTab("Pharmacy", new PharmacyPanel());
        tabbedPane.addTab("Billing", new BillingPanel());

        // Add logout button
        JPanel topPanel = new JPanel();
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> logout());
        topPanel.add(logoutButton);

        // Add components
        add(topPanel, BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.CENTER);
    }

    private void logout() {
        int result = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?",
                "Confirm Logout", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            new LoginFrame().setVisible(true);
            dispose();
        }
    }
}
