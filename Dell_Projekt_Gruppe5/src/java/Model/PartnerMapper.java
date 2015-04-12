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
    String message = "";
    
    
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
//        finally														// must close statement
//      {
//    	  try {
//			statement.close();
//		} catch (SQLException e) {
//			System.out.println("Fail");
//			System.out.println(e.getMessage());
//		}  
//      }
        if (count == 0) {
            message = "Invalid username or password";
            
        }
        System.out.println(count);
      return message;                         
       
      
        
    }
    
    public String createPartner(Partner partner, Connection con) {

        String errorMessage = "";

        String sql = "INSERT INTO partner (pno, cvr, navn, dato, brugernavn, password, rolle) VALUES (?,?,?,?,?,?,?)";
        String SQLString1 = 
        "select pno_increment.nextval  " +
        "from dual" ;
        
        String user = partner.getUsername();
        String pass = partner.getPassword();
        String name = partner.getName();
        String cvr = partner.getCvr();
        Date date = partner.getDate();

        try {
            
         PreparedStatement  insertStatement = con.prepareStatement(SQLString1);
        ResultSet rs  = insertStatement.executeQuery();
        if (rs.next())
        {
          partner.setPno(rs.getInt(1));
        } 
        insertStatement = con.prepareStatement(sql);
            insertStatement.setInt(1,partner.getPno());
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
//        try {
//            conn.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(PartnerMapper.class.getName()).log(Level.SEVERE, null, ex);
//        }

        return errorMessage;
    }
    
    public boolean updatePartnerStatus(int pno, Connection con) throws SQLException{
        
        
        String sqlString2 = "update partner set dato = ? where pno = ?";
        
        PreparedStatement  statement = con.prepareStatement(sqlString2);
        try {
             java.util.Date date = new java.util.Date();
            java.sql.Date sqldate = new java.sql.Date(date.getTime());
            statement = con.prepareStatement(sqlString2);
            statement.setInt(2,pno);
            statement.setDate(1, sqldate);

            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
          
        }
       
      
            statement.close();
        
        return true;
    }
    
    public String showPartnerName(Connection con) throws SQLException{
        String partnerName = "";
        String sqlString = "select navn from partner where dato is NULL";
        PreparedStatement statement = con.prepareStatement(sqlString);
        
        try{
            ResultSet rs  = statement.executeQuery();
            while (rs.next())
          {
            
                 partnerName = rs.getString(1);
                 System.out.println(partnerName); 
          }   
            
           
            
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        return partnerName; 
    }
    
    public String showPartnerCVR(Connection con) throws SQLException{
        String partnerCVR = "";
        String sqlString = "select CVR from partner where dato is NULL";
        PreparedStatement statement =null;
        
        try{
            statement = con.prepareStatement(sqlString);
            ResultSet rs  = statement.executeQuery();
            while (rs.next())
          {
            
                partnerCVR = rs.getString(1);
                  System.out.println(partnerCVR);
                //return partnerCVR;   
       
//                partnerCVR = rs.getString(1);
//                return partnerCVR;
         }   
           
            
           
            
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        
        return partnerCVR;
    
   
    }

}
    

