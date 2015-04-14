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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Frederik
 */
public class CampaignMapper {

    static boolean testRun = true;

    public boolean insertCampaign(Campaign camp, Connection con) {
        int rowsInserted = 0;
        String sqlString = "insert into kampagne values (?,?,?,?,?,?,?,?)";
        String primary = "select kno_increment.nextval from dual";
        System.out.println("CM: " + camp.getPno());
        PreparedStatement statement = null;
        try {
            
            PreparedStatement stmt = con.prepareStatement(primary);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                camp.setKno(rs.getInt(1));
            }
            
            statement = con.prepareStatement(sqlString);

            statement.setInt(1, camp.getKno());
            statement.setString(2, camp.getBeskrivelse());
            statement.setString(3, "Pending");
            statement.setString(4, "Pending");
            statement.setString(5, camp.getStart_dato());
            statement.setString(6, camp.getSlut_dato());
            statement.setFloat(7, camp.getPris());
            statement.setInt(8, camp.getPno());
            rowsInserted += statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CampaignMapper.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (testRun) {
            System.out.println("insertCampaign(): " + (rowsInserted == 1)); // for test
        }
//        try {
//            statement.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(CampaignMapper.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return (rowsInserted == 1);
    }

    public boolean updateCampaign(int kno, Connection con) throws SQLException {
        int rowsUpdated = 0;
        String sqlString = "update kampagne set status = ? where kno = ?";
        PreparedStatement statement = null;
        statement = con.prepareStatement(sqlString);
        String status = getCampaignStatus(kno, con);
        status = status.toLowerCase();
        switch (status) {
            case "pending":
                statement.setInt(2, kno);
                statement.setString(1, "In-Progress");
                break;

            case "in-progress":
                statement.setInt(2, kno);
                statement.setString(1, "POE Pending");
                break;

            case "poe pending":
                statement.setInt(2, kno);
                statement.setString(1, "POE Accepted");
                break;

            case "poe accepted":
                statement.setInt(2, kno);
                statement.setString(1, "Invoice Pending");
                break;

            case "invoice pending":
                statement.setInt(2, kno);
                statement.setString(1, "Complete");
                break;

            case "complete":
                statement.setInt(2, kno);
                statement.setString(1, "Complete");
                break;

            default:
                statement.setInt(2, kno);
                statement.setString(1, "Noget gik galt");
                break;
        }
        statement.executeUpdate();
        statement.close();

        return !status.equals(getCampaignStatus(kno, con));
    }

    public String getCampaignStatus(int kno, Connection con) throws SQLException {
        String sqlString = "select status from kampagne where kno = ?";
        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement(sqlString);
            statement.setInt(1, kno);
            ResultSet rs = statement.executeQuery();

            String status = "tom";
            if (rs.next()) {
                status = rs.getString(1);
            }
            return status;
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("ups");
        }
        return "noget gik galt";
    }

    
    public ArrayList<Campaign> getAllCampaigns(Connection con) {
        ArrayList<Campaign> list = new ArrayList<Campaign>();
        String sqlString = "select kno,beskrivelse,status,oprettelse_dato,start_dato,slut_dato,pris,kampagne.pno,navn,cvr from kampagne join partner on kampagne.PNO = PARTNER.PNO";
        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement(sqlString);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Campaign tmp = new Campaign(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getFloat(7),rs.getInt(8),rs.getString(9),rs.getString(10));
                tmp.setKno(rs.getInt(1));
                list.add(tmp);
            }
            
        } catch (SQLException e) {
        }
        return list;
    }
    
}
