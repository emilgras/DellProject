/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import static Model.CampaignMapper.testRun;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PoeMapper {

    public void uploadPoe(int kno, ArrayList<CustomFile> files, Connection con) {
        String sqlPrimary = "select poeNo_increment.nextval from dual";
        String sqlPoe = "insert into poe values (?, ?)";
        String sqlFil = "insert into filer values(?, ?, ?)";

        PreparedStatement statement = null;

        try {

            /*** Makes POE primary number ***/
            statement = con.prepareStatement(sqlPrimary);
            ResultSet rs = statement.executeQuery();
            rs.next();
            int poeNo = rs.getInt(1);

            /***** OBS OBS OBS Skal første statement lukkes --> statement.close(); *****/

            /*** Creates POE tuple and inserts data ***/
            statement = con.prepareStatement(sqlPoe);
            statement.setInt(1, poeNo);
            statement.setInt(2, kno);
            statement.executeUpdate();

            /*** Creates multiple FILE tuples and inserts data ***/
            statement = con.prepareStatement(sqlFil);
            for (CustomFile file : files) {
                statement.setString(1, file.getName());
                statement.setString(2, file.getExtension());
                statement.setInt(3, poeNo);
                statement.executeUpdate();
            }
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(CampaignMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Poe getPoe(int kno, Connection con) {
        Poe poe = new Poe();
        
        String sql = "select navn, extension from filer join poe on filer.poeno = poe.poeno where kno = ?";
        
        PreparedStatement statement = null;
        
        try {
            statement = con.prepareStatement(sql);
            statement.setInt(1, kno);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String name = rs.getString(1);
                String ext = rs.getString(2);
                CustomFile file  = new CustomFile(name, ext);
                
                /*** image files ***/
                if (ext.equals("png") || ext.equals("jpg") || ext.equals("jpeg") || ext.equals("pdf")) {
                    poe.addImageFile(file);
                }
                /*** video files ***/
                if (ext.equals("mp4") || ext.equals("avi")) {
                    poe.addVideoFile(file);
                }
                /*** music files ***/
                if (ext.equals("mp3")) {
                    poe.addMusicFile(file);
                }
                /*** text files ***/
                if (ext.equals("doc")) {
                    poe.addTextFile(file);
                }
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return poe;
    }
}
