package reservation;

import javax.swing.*;
import database.ReservationDAO;
import dashboard.DashboardFrame;
public class ReservationFrame extends JFrame {

    JLabel passengerLabel, trainNoLabel, trainNameLabel, classLabel;
    JLabel dateLabel, sourceLabel, destinationLabel;

    JTextField passengerField;
    JTextField trainNoField;
    JTextField trainNameField;
    JTextField dateField;
    JTextField sourceField;
    JTextField destinationField;

    JComboBox<String> classBox;

    JButton bookButton;
    JButton backButton;

    public ReservationFrame() {

        setTitle("Reservation Form");
        setSize(600,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        passengerLabel = new JLabel("Passenger Name");
        passengerLabel.setBounds(40,30,120,25);
        add(passengerLabel);

        passengerField = new JTextField();
        passengerField.setBounds(180,30,250,25);
        add(passengerField);

        trainNoLabel = new JLabel("Train Number");
        trainNoLabel.setBounds(40,70,120,25);
        add(trainNoLabel);

        trainNoField = new JTextField();
        trainNoField.setBounds(180,70,250,25);
        add(trainNoField);

        trainNameLabel = new JLabel("Train Name");
        trainNameLabel.setBounds(40,110,120,25);
        add(trainNameLabel);

        trainNameField = new JTextField();
        trainNameField.setBounds(180,110,250,25);
        trainNameField.setEditable(true);
        add(trainNameField);

        classLabel = new JLabel("Class");
        classLabel.setBounds(40,150,120,25);
        add(classLabel);

        classBox = new JComboBox<>();
        classBox.addItem("Sleeper");
        classBox.addItem("AC");
        classBox.addItem("First Class");
        classBox.setBounds(180,150,250,25);
        add(classBox);

        dateLabel = new JLabel("Journey Date");
        dateLabel.setBounds(40,190,120,25);
        add(dateLabel);

        dateField = new JTextField();
        dateField.setBounds(180,190,250,25);
        add(dateField);

        sourceLabel = new JLabel("Source");
        sourceLabel.setBounds(40,230,120,25);
        add(sourceLabel);

        sourceField = new JTextField();
        sourceField.setBounds(180,230,250,25);
        add(sourceField);

        destinationLabel = new JLabel("Destination");
        destinationLabel.setBounds(40,270,120,25);
        add(destinationLabel);

        destinationField = new JTextField();
        destinationField.setBounds(180,270,250,25);
        add(destinationField);

        bookButton = new JButton("Book Ticket");
        bookButton.setBounds(120,350,140,35);
        add(bookButton);

        backButton = new JButton("Back");
        backButton.setBounds(300,350,120,35);
        add(backButton);

        bookButton.addActionListener(e -> {

            String passengerName = passengerField.getText();
            String trainNumber = trainNoField.getText();
            String trainName = trainNameField.getText();
            String travelClass = classBox.getSelectedItem().toString();
            String journeyDate = dateField.getText();
            String source = sourceField.getText();
            String destination = destinationField.getText();

            if(passengerName.isEmpty() ||
                    trainNumber.isEmpty() ||
                    trainName.isEmpty() ||
                    journeyDate.isEmpty() ||
                    source.isEmpty() ||
                    destination.isEmpty())
            {
                JOptionPane.showMessageDialog(this,"Please fill all fields");
                return;
            }

            ReservationDAO dao = new ReservationDAO();

            boolean status = dao.bookTicket(
                    passengerName,
                    trainNumber,
                    trainName,
                    travelClass,
                    journeyDate,
                    source,
                    destination
            );

            if(status)
            {
                JOptionPane.showMessageDialog(this,"Ticket Booked Successfully!");
            }
            else
            {
                JOptionPane.showMessageDialog(this,"Booking Failed!");
            }

        });
        // Back Button Action
        backButton.addActionListener(e -> {

            new DashboardFrame();

            dispose();

        });
        setVisible(true);
    }
}