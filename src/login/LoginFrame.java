package login;
import dashboard.DashboardFrame;
import javax.swing.*;
import database.LoginDAO;

public class LoginFrame extends JFrame {

    JLabel usernameLabel;
    JLabel passwordLabel;

    JTextField usernameField;
    JPasswordField passwordField;

    JButton loginButton;
    JButton registerButton;

    public LoginFrame() {

        // Window Title
        setTitle("Online Reservation System");

        // Window Size
        setSize(450, 300);

        // Close application when window is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Center the window
        setLocationRelativeTo(null);

        // We are placing components manually
        setLayout(null);

        // Username Label
        usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(50, 50, 100, 30);
        add(usernameLabel);

        // Username TextField
        usernameField = new JTextField();
        usernameField.setBounds(150, 50, 220, 30);
        add(usernameField);

        // Password Label
        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(50, 100, 100, 30);
        add(passwordLabel);

        // Password Field
        passwordField = new JPasswordField();
        passwordField.setBounds(150, 100, 220, 30);
        add(passwordField);

        // Login Button
        loginButton = new JButton("Login");
        loginButton.setBounds(90, 180, 100, 35);
        add(loginButton);

        // Register Button
        registerButton = new JButton("Register");
        registerButton.setBounds(220, 180, 100, 35);
        add(registerButton);

        // Login Button Click Event
        loginButton.addActionListener(e -> {

            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            LoginDAO dao = new LoginDAO();

            boolean result = dao.checkLogin(username, password);

            if(result)
            {
                JOptionPane.showMessageDialog(this,"Login Successful");

                new DashboardFrame();

                dispose();
            }
            else
            {
                JOptionPane.showMessageDialog(this,"Invalid Username or Password");
            }

        });

        registerButton.addActionListener(e -> {

            new RegisterFrame();   // Open Register window
            dispose();             // Close Login window

        });

        // Make window visible
        setVisible(true);
    }
}