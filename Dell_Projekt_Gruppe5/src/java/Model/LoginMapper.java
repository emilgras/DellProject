package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * * @author Anders, Emil, Frederik **
 */
public class LoginMapper {

    protected LoginMapper() {
        try {
            Class.forName(DBDetail.DRIVER);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected String getLogin(String username, String password) {
        int user = 0;
        String role = null;

        try (Connection con = DriverManager.getConnection(DBDetail.URL, DBDetail.ID, DBDetail.PW)) {
            String SQLString1
                    = "select count(brugernavn) as users,rolle from bruger where brugernavn = ? "
                    + "and password = ? group by rolle";
            PreparedStatement statement = null;
            ResultSet rs = null;

            statement = con.prepareStatement(SQLString1);
            statement.setString(1, username);
            statement.setString(2, password);

            rs = statement.executeQuery();
            if (rs.next()) {
                user = rs.getInt(1);
                role = rs.getString(2);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PartnerMapper.class.getName()).log(Level.SEVERE, null, ex);
            user = 2;
        }

        String message = "";

        if (user == 0) {
            message = "Invalid username or password";
        } else if (user == 1 && role.equals("admin")) {
            message = "admin";
        } else if (user == 1 && role.equals("partner")) {
            message = "partner";
        } else if (user == 2) {
            message = "Ups, something went wrong. Please, try again";
        }
        return message;
    }
}