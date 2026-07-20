package login;

import javax.swing.*;
import database.UserDAO;

public class RegisterFrame extends JFrame {

    JLabel nameLabel, usernameLabel, passwordLabel, confirmPasswordLabel;

    JTextField nameField, usernameField;
    JPasswordField passwordField, confirmPasswordField;

    JButton registerButton, backButton;

    public RegisterFrame() {

        // Window Title
        setTitle("User Registration");

        // Window Size
        setSize(500, 400);

        // Close Operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Center the Window
        setLocationRelativeTo(null);

        // Manual Layout
        setLayout(null);

        // Name
        nameLabel = new JLabel("Name");
        nameLabel.setBounds(50, 40, 120, 30);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(180, 40, 220, 30);
        add(nameField);

        // Username
        usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(50, 90, 120, 30);
        add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(180, 90, 220, 30);
        add(usernameField);

        // Password
        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(50, 140, 120, 30);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(180, 140, 220, 30);
        add(passwordField);

        // Confirm Password
        confirmPasswordLabel = new JLabel("Confirm Password");
        confirmPasswordLabel.setBounds(50, 190, 120, 30);
        add(confirmPasswordLabel);

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(180, 190, 220, 30);
        add(confirmPasswordField);

        // Register Button
        registerButton = new JButton("Register");
        registerButton.setBounds(100, 270, 120, 35);
        add(registerButton);

        // Back Button
        backButton = new JButton("Back");
        backButton.setBounds(260, 270, 120, 35);
        add(backButton);

        registerButton.addActionListener(e -> {

            String name = nameField.getText();
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());


            if(name.isEmpty() || username.isEmpty() || password.isEmpty()) {

                JOptionPane.showMessageDialog(this, "Fill all fields");

            }
            else if(!password.equals(confirmPassword)) {

                JOptionPane.showMessageDialog(this, "Passwords do not match");

            }
            else {

                UserDAO dao = new UserDAO();

                boolean status = dao.registerUser(name, username, password);

                if(status) {

                    JOptionPane.showMessageDialog(this, "Registration Successful");

                }
                else {

                    JOptionPane.showMessageDialog(this, "Registration Failed");

                }
            }

        });
        backButton.addActionListener(e -> {

            new LoginFrame();   // Open Login window
            dispose();          // Close Register window

        });
        setVisible(true);
    }
}