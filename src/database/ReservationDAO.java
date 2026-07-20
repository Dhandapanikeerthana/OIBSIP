package database;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ReservationDAO {

    public boolean bookTicket(String passengerName,
                              String trainNumber,
                              String trainName,
                              String travelClass,
                              String journeyDate,
                              String source,
                              String destination) {

        String sql = "INSERT INTO reservations(passenger_name, train_number, train_name, class_type, journey_date, source, destination) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, passengerName);
            pst.setString(2, trainNumber);
            pst.setString(3, trainName);
            pst.setString(4, travelClass);
            pst.setString(5, journeyDate);
            pst.setString(6, source);
            pst.setString(7, destination);


            int result = pst.executeUpdate();

            pst.close();
            con.close();

            return result > 0;


        } catch(Exception e) {

            e.printStackTrace();
            return false;
        }
    }
}