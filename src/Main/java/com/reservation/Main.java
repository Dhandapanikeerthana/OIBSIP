package com.reservation;

import com.reservation.ui.LoginFrame;
import com.reservation.util.DatabaseConnection;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Initialize the database tables and insert sample data
        DatabaseConnection.initializeDatabase();
        
        // Launch the application GUI
        SwingUtilities.invokeLater(() -> {
            try {
                // Set system default look and feel for better UI
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new LoginFrame().setVisible(true);
        });
    }
}
