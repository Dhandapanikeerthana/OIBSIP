package com.reservation.ui;

import com.reservation.dao.ReservationDAO;
import com.reservation.dao.TrainDAO;
import com.reservation.model.Reservation;
import com.reservation.model.Train;
import com.reservation.util.PNRGenerator;
import com.reservation.util.ValidationUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class ReservationFrame extends JFrame {
    
    private JTextField txtPassengerName;
    private JTextField txtTrainNo;
    private JTextField txtTrainName;
    private JComboBox<String> cbClassType;
    private JTextField txtJourneyDate;
    private JTextField txtSource;
    private JTextField txtDestination;
    
    private JButton btnBook;
    private JButton btnClear;
    private JButton btnCancellation;
    
    private TrainDAO trainDAO;
    private ReservationDAO reservationDAO;

    public ReservationFrame() {
        trainDAO = new TrainDAO();
        reservationDAO = new ReservationDAO();
        
        setTitle("Train Reservation");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        initUI();
    }
    
    private void initUI() {
        ImagePanel bgPanel = new ImagePanel(new BorderLayout(), "/images/bg.png");
        setContentPane(bgPanel);
        
        JPanel panel = new JPanel(new GridLayout(9, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setOpaque(false); // Make transparent for background
        
        addStyledLabel(panel, "Passenger Name:");
        txtPassengerName = new JTextField();
        panel.add(txtPassengerName);
        
        addStyledLabel(panel, "Train Number:");
        txtTrainNo = new JTextField();
        panel.add(txtTrainNo);
        
        addStyledLabel(panel, "Train Name:");
        txtTrainName = new JTextField();
        txtTrainName.setEditable(false);
        panel.add(txtTrainName);
        
        // Train Auto-fill Feature
        txtTrainNo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String tNoStr = txtTrainNo.getText();
                if (ValidationUtil.isNumeric(tNoStr)) {
                    int tNo = Integer.parseInt(tNoStr);
                    Train train = trainDAO.getTrainByNumber(tNo);
                    if (train != null) {
                        txtTrainName.setText(train.getTrainName());
                    } else {
                        txtTrainName.setText("");
                        JOptionPane.showMessageDialog(ReservationFrame.this, "Train number does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        
        addStyledLabel(panel, "Class Type:");
        String[] classes = {"Sleeper", "AC 3 Tier", "AC 2 Tier", "First Class"};
        cbClassType = new JComboBox<>(classes);
        panel.add(cbClassType);
        
        addStyledLabel(panel, "Date of Journey (yyyy-MM-dd):");
        txtJourneyDate = new JTextField();
        panel.add(txtJourneyDate);
        
        addStyledLabel(panel, "Source Station:");
        txtSource = new JTextField();
        panel.add(txtSource);
        
        addStyledLabel(panel, "Destination Station:");
        txtDestination = new JTextField();
        panel.add(txtDestination);
        
        btnBook = new JButton("Book Ticket");
        btnClear = new JButton("Clear");
        btnCancellation = new JButton("Go to Cancellation");
        
        JPanel btnPanel = new JPanel();
        btnPanel.setOpaque(false);
        btnPanel.add(btnBook);
        btnPanel.add(btnClear);
        btnPanel.add(btnCancellation);
        
        bgPanel.add(panel, BorderLayout.CENTER);
        bgPanel.add(btnPanel, BorderLayout.SOUTH);
        
        // Actions
        btnClear.addActionListener(e -> clearForm());
        
        btnCancellation.addActionListener(e -> {
            dispose();
            new CancellationFrame().setVisible(true);
        });
        
        btnBook.addActionListener(e -> bookTicket());
    }
    
    private void addStyledLabel(JPanel panel, String text) {
        JLabel label = new JLabel(text);
        label.setFont(label.getFont().deriveFont(Font.BOLD, 14f));
        panel.add(label);
    }
    
    private void bookTicket() {
        String pName = txtPassengerName.getText();
        String tNoStr = txtTrainNo.getText();
        String tName = txtTrainName.getText();
        String cType = (String) cbClassType.getSelectedItem();
        String jDate = txtJourneyDate.getText();
        String src = txtSource.getText();
        String dest = txtDestination.getText();
        
        // Validations
        if (!ValidationUtil.isValidString(pName) || !ValidationUtil.isValidString(tNoStr) ||
            !ValidationUtil.isValidString(jDate) || !ValidationUtil.isValidString(src) || !ValidationUtil.isValidString(dest)) {
            JOptionPane.showMessageDialog(this, "All fields are required.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (!ValidationUtil.isAlpha(pName)) {
            JOptionPane.showMessageDialog(this, "Passenger name must contain only alphabets.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (!ValidationUtil.isNumeric(tNoStr)) {
            JOptionPane.showMessageDialog(this, "Train number must be numeric.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (tName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Invalid Train Number. Train Name not found.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (!ValidationUtil.isValidDate(jDate)) {
            JOptionPane.showMessageDialog(this, "Invalid Date format. Use yyyy-MM-dd.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (src.equalsIgnoreCase(dest)) {
            JOptionPane.showMessageDialog(this, "Source and Destination cannot be the same.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // PNR Generation and Booking
        String pnr = PNRGenerator.generatePNR();
        
        Reservation res = new Reservation();
        res.setPnr(pnr);
        res.setPassengerName(pName);
        res.setTrainNo(Integer.parseInt(tNoStr));
        res.setTrainName(tName);
        res.setClassType(cType);
        res.setJourneyDate(jDate);
        res.setSource(src);
        res.setDestination(dest);
        
        if (reservationDAO.insertReservation(res)) {
            String msg = String.format("Ticket Booked Successfully!\n\nPNR: %s\nPassenger Name: %s\nTrain Name: %s\nTrain Number: %s\nDate: %s\nSource: %s\nDestination: %s\nClass: %s",
                    pnr, pName, tName, tNoStr, jDate, src, dest, cType);
            JOptionPane.showMessageDialog(this, msg, "Booking Confirmation", JOptionPane.INFORMATION_MESSAGE);
            clearForm();
        } else {
            JOptionPane.showMessageDialog(this, "Database error during booking.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void clearForm() {
        txtPassengerName.setText("");
        txtTrainNo.setText("");
        txtTrainName.setText("");
        cbClassType.setSelectedIndex(0);
        txtJourneyDate.setText("");
        txtSource.setText("");
        txtDestination.setText("");
    }
}
