package database;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UserDAO {

    public boolean registerUser(String name, String username, String password) {

        String sql = "INSERT INTO users(name, username, password) VALUES (?, ?, ?)";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, name);
            pst.setString(2, username);
            pst.setString(3, password);

            int result = pst.executeUpdate();

            pst.close();
            con.close();

            return result > 0;

        } catch (Exception e) {

            e.printStackTrace();
            return false;

        }
    }
}