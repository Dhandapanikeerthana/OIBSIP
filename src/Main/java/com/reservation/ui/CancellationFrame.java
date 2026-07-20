package com.reservation.ui;

import com.reservation.dao.ReservationDAO;
import com.reservation.model.Reservation;

import javax.swing.*;
import java.awt.*;

public class CancellationFrame extends JFrame {
    
    private JTextField txtPnr;
    private JTextArea txtDetails;
    private JButton btnFetch;
    private JButton btnCancel;
    private JButton btnBack;
    
    private ReservationDAO reservationDAO;
    private Reservation currentReservation;

    public CancellationFrame() {
        reservationDAO = new ReservationDAO();
        setTitle("Ticket Cancellation");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        initUI();
    }
    
    private void initUI() {
        ImagePanel bgPanel = new ImagePanel(new BorderLayout(), "/images/bg.png");
        setContentPane(bgPanel);
        
        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        JLabel lblPnr = new JLabel("Enter PNR Number:");
        lblPnr.setFont(lblPnr.getFont().deriveFont(Font.BOLD, 14f));
        topPanel.add(lblPnr);
        txtPnr = new JTextField(15);
        topPanel.add(txtPnr);
        btnFetch = new JButton("Fetch Booking");
        topPanel.add(btnFetch);
        
        txtDetails = new JTextArea();
        txtDetails.setEditable(false);
        txtDetails.setMargin(new Insets(10,10,10,10));
        
        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);
        btnCancel = new JButton("Cancel Ticket");
        btnCancel.setEnabled(false);
        btnBack = new JButton("Back to Reservation");
        bottomPanel.add(btnCancel);
        bottomPanel.add(btnBack);
        
        bgPanel.add(topPanel, BorderLayout.NORTH);
        bgPanel.add(new JScrollPane(txtDetails), BorderLayout.CENTER);
        bgPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        // Actions
        btnFetch.addActionListener(e -> fetchBooking());
        btnCancel.addActionListener(e -> cancelTicket());
        btnBack.addActionListener(e -> {
            dispose();
            new ReservationFrame().setVisible(true);
        });
    }
    
    private void fetchBooking() {
        String pnr = txtPnr.getText().trim();
        if (pnr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a PNR number.");
            return;
        }
        
        currentReservation = reservationDAO.getReservationByPNR(pnr);
        
        if (currentReservation != null) {
            String details = String.format("Passenger Name: %s\nTrain Number: %d\nTrain Name: %s\nDate: %s\nSource: %s\nDestination: %s\nClass: %s",
                    currentReservation.getPassengerName(),
                    currentReservation.getTrainNo(),
                    currentReservation.getTrainName(),
                    currentReservation.getJourneyDate(),
                    currentReservation.getSource(),
                    currentReservation.getDestination(),
                    currentReservation.getClassType());
            txtDetails.setText(details);
            btnCancel.setEnabled(true);
        } else {
            txtDetails.setText("");
            btnCancel.setEnabled(false);
            JOptionPane.showMessageDialog(this, "No booking found with this PNR.", "Not Found", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void cancelTicket() {
        if (currentReservation != null) {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to cancel this ticket?", "Confirm Cancellation", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                if (reservationDAO.deleteReservation(currentReservation.getPnr())) {
                    JOptionPane.showMessageDialog(this, "Ticket Cancelled Successfully");
                    txtPnr.setText("");
                    txtDetails.setText("");
                    btnCancel.setEnabled(false);
                    currentReservation = null;
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to cancel ticket due to database error.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
