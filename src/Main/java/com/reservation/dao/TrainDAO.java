package com.reservation.dao;

import com.reservation.model.Train;
import com.reservation.util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TrainDAO {
    
    public Train getTrainByNumber(int trainNo) {
        String query = "SELECT * FROM Trains WHERE train_no = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
             
            pstmt.setInt(1, trainNo);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Train(rs.getInt("train_no"), rs.getString("train_name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Train not found
    }
}
