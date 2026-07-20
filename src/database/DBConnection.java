package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final String URL = "jdbc:sqlite:reservation.db";

    public static Connection getConnection() {

        try {
            Connection connection = DriverManager.getConnection(URL);
            System.out.println("Database Connected Successfully");
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}