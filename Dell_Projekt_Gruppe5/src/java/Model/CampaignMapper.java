/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Frederik
 */
public class CampaignMapper {
    
    static boolean testRun = true;
    
    public boolean insertCampaign(ArrayList<Campaign> list, Connection con) throws SQLException{
        int rowsInserted = 0;
        String sqlString = "insert into kampagne values (?,?,?,?,?,?,?,?)";
        PreparedStatement statement = null;
        statement = con.prepareStatement(sqlString);
        for (int i = 0; i < list.size(); i++) {
            
            Campaign c = list.get(i);
            statement.setInt(1, list.get(i).getKno());
            statement.setString(2, list.get(i).getBeskrivelse());
            statement.setString(3, list.get(i).getStatus());
            statement.setDate(4, list.get(i).getOprettelse_dato());
            //statement.setDate(5, list.get(i).getStart_dato());
            //statement.setDate(6, list.get(i).getSlut_dato());
            //statement.setInt(7, list.get(i).getPris());
            statement.setInt(8, list.get(i).getPno());
            rowsInserted += statement.executeUpdate();
        }
        if (testRun)
        {
            System.out.println("insertCampaign(): " + (rowsInserted == list.size())); // for test
        }
        statement.close();
        return (rowsInserted == list.size());
    }
    
    
}
