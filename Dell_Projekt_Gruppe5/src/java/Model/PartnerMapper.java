
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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ABjergfelt
 */
public class PartnerMapper {

    int count = 0;
    String message = "";

    public String getLogin(String username, String password, Connection con) {
        Partner p = null;
        String SQLString1
                = "select count(*) as count from partner where brugernavn = ? and password = ?";

        PreparedStatement statement = null;

        try {
            // get login
            statement = con.prepareStatement(SQLString1);

            statement.setString(1, username);
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

        String sql = "INSERT INTO partner (pno, cvr, land, navn, dato, brugernavn, password, rolle) VALUES (?,?,?,?,?,?,?,?)";
        String SQLString1
                = "select pno_increment.nextval  "
                + "from dual";

        String user = partner.getUsername();
        String pass = partner.getPassword();
        String name = partner.getName();
        String cvr = partner.getCvr();
        String country = partner.getCountry();
        Date date = partner.getDate();

        try {

            PreparedStatement insertStatement = con.prepareStatement(SQLString1);
            ResultSet rs = insertStatement.executeQuery();
            if (rs.next()) {
                partner.setPno(rs.getInt(1));
            }
            insertStatement = con.prepareStatement(sql);
            insertStatement.setInt(1, partner.getPno());
            insertStatement.setString(2, cvr);
            insertStatement.setString(3, country);
            insertStatement.setString(4, name);
            insertStatement.setDate(5, date);
            insertStatement.setString(6, user);
            insertStatement.setString(7, pass);
            insertStatement.setString(8, "Partner");

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

    public boolean acceptPartner(String cvr, Connection con) {

        String sqlString2 = "update partner set dato = ? where cvr = ?";

        try {
            PreparedStatement statement = con.prepareStatement(sqlString2);
            java.util.Date date = new java.util.Date();
            java.sql.Date sqldate = new java.sql.Date(date.getTime());
            statement = con.prepareStatement(sqlString2);
            statement.setString(2,cvr );
            statement.setDate(1, sqldate);

            statement.executeUpdate();
            statement.close();
            return true; 
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return true;
    }
    
    public ArrayList<Partner> getAllPendingPartners(Connection con) {
       
        String sqlString = "select navn,cvr,land from partner where dato is NULL";
        ArrayList<Partner> pArray = new ArrayList<>();
        
        try {
            PreparedStatement statement = con.prepareStatement(sqlString);
            ResultSet rs = statement.executeQuery();
            int count = 0;
            while (rs.next()) {

                String partnerName = rs.getString(1);
                String partnerCVR = rs.getString(2);
                String partnerCountry = rs.getString(3);
                
                Partner p = new Partner(partnerName, partnerCVR, partnerCountry);
                
                pArray.add(p); 
                System.out.println(pArray.get(count).getName());
                count++;
                System.out.println(count);
               
                
            }
            

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return pArray;
        
    }

//    public String showPartnerCVR(Connection con){
//        String partnerCVR = "";
//        String sqlString = "select CVR from partner where dato is NULL";
//        PreparedStatement statement =null;
//        
//        try{
//            statement = con.prepareStatement(sqlString);
//            ResultSet rs  = statement.executeQuery();
//            while (rs.next())
//          {
//            
//                partnerCVR = rs.getString(1);
//                  System.out.println(partnerCVR);
//                //return partnerCVR;   
//       
////                partnerCVR = rs.getString(1);
////                return partnerCVR;
//         }   
//           
//            
//           
//            
//        }catch(SQLException ex){
//            System.err.println(ex.getMessage());
//        }
//        
//        return partnerCVR;
//    
//   
//    }
    public int getPno(String username, Connection con) {
        int pno = 0;
        String sqlString = "select pno from partner where brugernavn = ?";
        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement(sqlString);
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            String status = "noget gik galt";
            if (rs.next()) {
                pno = Integer.parseInt(rs.getString(1));
            }
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("ups");
        }
        return pno;
    }
}
