/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Entities.Partner;
import Model.PartnerMapper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Frederik
 */
public class LoginMapper {

    private DBConnector conn = new DBConnector();

    
    

    public String getLogin(String username, String password) {
        String message = "";
        Partner p = null;
        int user = 2;
        int pass = 2;
        String role = null;
        try (Connection con = DriverManager.getConnection(conn.getURL(), conn.getId(), conn.getPw())) {
            String SQLString1
                    = "select count(brugernavn), count(password),rolle from bruger where brugernavn = ? and password = ? group by rolle";

            PreparedStatement statement = null;
            ResultSet rs = null;

            statement = con.prepareStatement(SQLString1);
            statement.setString(1, username);
            statement.setString(2, password);

            rs = statement.executeQuery();
            rs.next();
            user = rs.getInt(1);
            pass = rs.getInt(2);
            role = rs.getString(3);
            //statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(PartnerMapper.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (user == 1 && pass == 1 && role.equals("admin")) {
            message = "admin";
        } else if (user == 1 && pass == 1 && role.equals("partner")) {
            message = "partner";
        } else if (user == 2 || pass == 2) {
            message = "Invalid username or password";
        } else {
            message = "Ups, something went wrong. Please, try again.";
        }
        return message;
    }
}
