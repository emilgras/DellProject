/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ABjergfelt
 */
public class PartnerMapper {
    int count = 0;
    String message = "logged in";
    public String getLogin(String username, String password, Connection con){
        Partner p = null;
        String SQLString1 =       
          "select count(*) as count from partner where brugernavn = ? and password = ?";
        
        PreparedStatement statement= null;
        
        try
        {
            // get login
            statement = con.prepareStatement(SQLString1);
            
            statement.setString(1,username);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            rs.next();
            
            count = rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(PartnerMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally														// must close statement
      {
    	  try {
			statement.close();
		} catch (SQLException e) {
			System.out.println("Fail");
			System.out.println(e.getMessage());
		}  
      }
        if (count == 0) {
            message = "Invalid username or password";
            
        }
        System.out.println(count);
      return message;                         
       
      
        
    }
    
    public String createPartner(Partner partner, Connection conn) {

        String errorMessage = "";

        String sql = "INSERT INTO partner (pno, cvr, navn, dato, brugernavn, password, rolle) VALUES (?,?,?,?,?,?,?)";

        int pno = partner.getPno();
        String user = partner.getUsername();
        String pass = partner.getPassword();
        String name = partner.getName();
        String cvr = partner.getCvr();
        Date date = partner.getDate();

        try {
            PreparedStatement insertStatement = conn.prepareStatement(sql);
            
            insertStatement.setInt(1, pno);
            insertStatement.setString(2, cvr);
            insertStatement.setString(3, name);
            insertStatement.setDate(4, date);
            insertStatement.setString(5, user);
            insertStatement.setString(6, pass);
            insertStatement.setString(7, "Partner");

            insertStatement.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            errorMessage = "Not able to create user at the moment, please try again.";
        }
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(PartnerMapper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return errorMessage;
    }
}
    

