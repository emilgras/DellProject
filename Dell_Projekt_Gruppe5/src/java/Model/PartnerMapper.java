/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
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
    String message = "";
    public String getLogin(String username, String password, Connection con){
        Partner p = null;
        String SQLString1 =       
          "select * " +
          "from partner " +
          "where brugernavn and password = ?,?";
        
        PreparedStatement statement= null;
        
        try
        {
            // get login
            statement = con.prepareStatement(SQLString1);
            
            statement.setString(1,username);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            rs.next();

          count = rs.getInt(5);
          
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
        
      return message;                         
       
      
        
    }
}
    

