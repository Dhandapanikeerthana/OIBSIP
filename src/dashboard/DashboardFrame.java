package dashboard;

import javax.swing.*;
import reservation.ReservationFrame;

public class DashboardFrame extends JFrame {

    JButton reservationButton;
    JButton cancellationButton;
    JButton logoutButton;

    public DashboardFrame() {

        setTitle("Online Reservation System");
        setSize(500,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel title = new JLabel("Welcome to Online Reservation System");
        title.setBounds(100,30,300,30);
        add(title);

        reservationButton = new JButton("Book Ticket");
        reservationButton.setBounds(150,80,180,35);
        add(reservationButton);

        cancellationButton = new JButton("Cancel Ticket");
        cancellationButton.setBounds(150,130,180,35);
        add(cancellationButton);

        logoutButton = new JButton("Logout");
        logoutButton.setBounds(150,180,180,35);
        add(logoutButton);

        // Open Reservation Page
        reservationButton.addActionListener(e -> {

            new ReservationFrame();
            dispose();

        });

        // Logout
        logoutButton.addActionListener(e -> {

            new login.LoginFrame();
            dispose();

        });

        setVisible(true);
    }
}