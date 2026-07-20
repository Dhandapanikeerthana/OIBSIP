
package database;
import database.LoginDAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDAO {

    public boolean checkLogin(String username, String password) {

        String sql = "SELECT * FROM users WHERE username=? AND password=?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, username);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();

            if(rs.next()) {
                return true;
            }

        } catch(Exception e) {

            e.printStackTrace();

        }

        return false;
    }
}