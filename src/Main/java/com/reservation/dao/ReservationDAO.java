package com.reservation.dao;

import com.reservation.model.Reservation;
import com.reservation.util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservationDAO {
    
    public boolean insertReservation(Reservation reservation) {
        String query = "INSERT INTO Reservations (pnr, passenger_name, train_no, train_name, class_type, journey_date, source, destination) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
             
            pstmt.setString(1, reservation.getPnr());
            pstmt.setString(2, reservation.getPassengerName());
            pstmt.setInt(3, reservation.getTrainNo());
            pstmt.setString(4, reservation.getTrainName());
            pstmt.setString(5, reservation.getClassType());
            pstmt.setString(6, reservation.getJourneyDate());
            pstmt.setString(7, reservation.getSource());
            pstmt.setString(8, reservation.getDestination());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public Reservation getReservationByPNR(String pnr) {
        String query = "SELECT * FROM Reservations WHERE pnr = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
             
            pstmt.setString(1, pnr);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Reservation res = new Reservation();
                    res.setPnr(rs.getString("pnr"));
                    res.setPassengerName(rs.getString("passenger_name"));
                    res.setTrainNo(rs.getInt("train_no"));
                    res.setTrainName(rs.getString("train_name"));
                    res.setClassType(rs.getString("class_type"));
                    res.setJourneyDate(rs.getString("journey_date"));
                    res.setSource(rs.getString("source"));
                    res.setDestination(rs.getString("destination"));
                    return res;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean deleteReservation(String pnr) {
        String query = "DELETE FROM Reservations WHERE pnr = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
             
            pstmt.setString(1, pnr);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
