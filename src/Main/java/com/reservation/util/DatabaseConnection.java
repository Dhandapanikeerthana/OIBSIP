package com.reservation.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.File;

public class DatabaseConnection {
    // Relative path to db folder
    private static final String URL = "jdbc:sqlite:db/reservation.db";
    
    public static Connection getConnection() throws SQLException {
        // Ensure directory exists
        File dbDir = new File("db");
        if (!dbDir.exists()) {
            dbDir.mkdirs();
        }
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL);
    }
    
    public static void initializeDatabase() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
             
            // Create Users Table
            stmt.execute("CREATE TABLE IF NOT EXISTS Users (" +
                         "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                         "username TEXT NOT NULL UNIQUE," +
                         "password TEXT NOT NULL)");
                         
            // Create Trains Table
            stmt.execute("CREATE TABLE IF NOT EXISTS Trains (" +
                         "train_no INTEGER PRIMARY KEY," +
                         "train_name TEXT NOT NULL)");
                         
            // Create Reservations Table
            stmt.execute("CREATE TABLE IF NOT EXISTS Reservations (" +
                         "pnr TEXT PRIMARY KEY," +
                         "passenger_name TEXT NOT NULL," +
                         "train_no INTEGER NOT NULL," +
                         "train_name TEXT NOT NULL," +
                         "class_type TEXT NOT NULL," +
                         "journey_date TEXT NOT NULL," +
                         "source TEXT NOT NULL," +
                         "destination TEXT NOT NULL," +
                         "FOREIGN KEY (train_no) REFERENCES Trains(train_no))");
                         
            // Insert default data only when absent
            stmt.execute("INSERT OR IGNORE INTO Users (username, password) VALUES ('admin', 'admin123')");
            stmt.execute("INSERT OR IGNORE INTO Trains (train_no, train_name) VALUES (12627, 'Karnataka Express')");
            stmt.execute("INSERT OR IGNORE INTO Trains (train_no, train_name) VALUES (12621, 'Tamil Nadu Express')");
            stmt.execute("INSERT OR IGNORE INTO Trains (train_no, train_name) VALUES (12637, 'Pandian Express')");
            stmt.execute("INSERT OR IGNORE INTO Trains (train_no, train_name) VALUES (12675, 'Kovai Express')");
            stmt.execute("INSERT OR IGNORE INTO Trains (train_no, train_name) VALUES (12631, 'Nellai Express')");
            stmt.execute("INSERT OR IGNORE INTO Trains (train_no, train_name) VALUES (12007, 'Shatabdi Express')");
            stmt.execute("INSERT OR IGNORE INTO Trains (train_no, train_name) VALUES (12951, 'Rajdhani Express')");
            stmt.execute("INSERT OR IGNORE INTO Trains (train_no, train_name) VALUES (12841, 'Coromandel Express')");
            stmt.execute("INSERT OR IGNORE INTO Trains (train_no, train_name) VALUES (12296, 'Sanghamitra Express')");
            stmt.execute("INSERT OR IGNORE INTO Trains (train_no, train_name) VALUES (12693, 'Pearl City Express')");
            stmt.execute("INSERT OR IGNORE INTO Trains (train_no, train_name) VALUES (16127, 'Guruvayur Express')");
            stmt.execute("INSERT OR IGNORE INTO Trains (train_no, train_name) VALUES (12635, 'Vaigai Express')");
            stmt.execute("INSERT OR IGNORE INTO Trains (train_no, train_name) VALUES (12605, 'Pallavan Express')");
            stmt.execute("INSERT OR IGNORE INTO Trains (train_no, train_name) VALUES (12633, 'Kanyakumari Express')");
            stmt.execute("INSERT OR IGNORE INTO Trains (train_no, train_name) VALUES (16105, 'Tiruchendur Express')");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
