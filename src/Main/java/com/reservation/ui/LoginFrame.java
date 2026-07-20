package com.reservation.ui;

import com.reservation.dao.UserDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnRegister;
    private UserDAO userDAO;

    public LoginFrame() {
        userDAO = new UserDAO();
        setTitle("Online Reservation System - Login");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame
        
        initUI();
    }
    
    private void initUI() {
        ImagePanel panel = new ImagePanel(new GridBagLayout(), "/images/bg.png");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setFont(lblUsername.getFont().deriveFont(Font.BOLD, 14f));
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(lblPassword.getFont().deriveFont(Font.BOLD, 14f));
        
        txtUsername = new JTextField(15);
        txtPassword = new JPasswordField(15);
        
        btnLogin = new JButton("Login");
        btnRegister = new JButton("Register");
        
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(lblUsername, gbc);
        
        gbc.gridx = 1; gbc.gridy = 0;
        panel.add(txtUsername, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(lblPassword, gbc);
        
        gbc.gridx = 1; gbc.gridy = 1;
        panel.add(txtPassword, gbc);
        
        JPanel btnPanel = new JPanel();
        btnPanel.setOpaque(false);
        btnPanel.add(btnLogin);
        btnPanel.add(btnRegister);
        
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(btnPanel, gbc);
        
        setContentPane(panel);
        
        // Action Listener for Login Button
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText();
                String password = new String(txtPassword.getPassword());
                
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Please enter username and password.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // Validate credentials from the database
                if (userDAO.validateLogin(username, password)) {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Login Successful");
                    // Open Reservation Frame
                    dispose();
                    new ReservationFrame().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Invalid Username or Password", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        // Action Listener for Register Button
        btnRegister.addActionListener(e -> {
            dispose();
            new RegistrationFrame().setVisible(true);
        });
    }
}
