package com.reservation.ui;

import com.reservation.dao.UserDAO;

import javax.swing.*;
import java.awt.*;

public class RegistrationFrame extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JPasswordField txtConfirmPassword;
    private JButton btnRegister;
    private JButton btnBack;
    private UserDAO userDAO;

    public RegistrationFrame() {
        userDAO = new UserDAO();
        setTitle("Online Reservation System - Register");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        initUI();
    }
    
    private void initUI() {
        ImagePanel panel = new ImagePanel(new GridBagLayout(), "/images/bg.png");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        JLabel lblUsername = new JLabel("New Username:");
        lblUsername.setFont(lblUsername.getFont().deriveFont(Font.BOLD, 14f));
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(lblPassword.getFont().deriveFont(Font.BOLD, 14f));
        JLabel lblConfirmPassword = new JLabel("Confirm Password:");
        lblConfirmPassword.setFont(lblConfirmPassword.getFont().deriveFont(Font.BOLD, 14f));
        
        txtUsername = new JTextField(15);
        txtPassword = new JPasswordField(15);
        txtConfirmPassword = new JPasswordField(15);
        
        btnRegister = new JButton("Register");
        btnBack = new JButton("Back to Login");
        
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(lblUsername, gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        panel.add(txtUsername, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(lblPassword, gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        panel.add(txtPassword, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(lblConfirmPassword, gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        panel.add(txtConfirmPassword, gbc);
        
        JPanel btnPanel = new JPanel();
        btnPanel.setOpaque(false);
        btnPanel.add(btnRegister);
        btnPanel.add(btnBack);
        
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(btnPanel, gbc);
        
        setContentPane(panel);
        
        btnRegister.addActionListener(e -> registerUser());
        btnBack.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });
    }
    
    private void registerUser() {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());
        String confirmPassword = new String(txtConfirmPassword.getPassword());
        
        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (userDAO.registerUser(username, password)) {
            JOptionPane.showMessageDialog(this, "Registration Successful! You can now log in.");
            dispose();
            new LoginFrame().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Registration Failed. Username might already exist.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
