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
        int i = 2;
        int j = 2;
        String k = null;
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
            i = rs.getInt(1);
            j = rs.getInt(2);
            k = rs.getString(3);
            //statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(PartnerMapper.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (i == 1 && j == 1 && k.equals("admin")) {
            message = "admin";
        } else if (i == 1 && j == 1 && k.equals("partner")) {
            message = "partner";
        } else if (i == 0 || j == 0) {
            message = "Invalid username or password";
        } else {
            message = "Ups, something went wrong. Please, try again.";
        }
        return message;
    }

}
