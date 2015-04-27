/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import static Model.CampaignMapper.testRun;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PoeMapper {

    DBConnector db = new DBConnector();

    public boolean uploadPoe(int kno, ArrayList<CustomFile> files) {
        boolean success = true;
        try (Connection con = DriverManager.getConnection(db.getURL(), db.getId(), db.getPw())) {
            System.out.println("POEM KNO: " + kno);
            String sqlPrimary = "select poeNo_increment.nextval from dual";
            String sqlPoe = "insert into poe values (?, ?)";
            String sqlFil = "insert into filer values(?, ?, ?)";

            ResultSet rs = null;
            PreparedStatement statement = null;

            /**
             * * Makes POE primary number **
             */
            statement = con.prepareStatement(sqlPrimary);
            rs = statement.executeQuery();
            rs.next();
            int poeNo = rs.getInt(1);

            /**
             * * Creates POE tuple and inserts data **
             */
            statement = con.prepareStatement(sqlPoe);
            statement.setInt(1, poeNo);
            statement.setInt(2, kno);
            statement.executeUpdate();

            /**
             * * Creates multiple FILE tuples and inserts data **
             */
            System.out.println("POEM FILE SIZE: " + files.size());
            System.out.println("POEM FILE #1 : " + files.get(0).getName());
            System.out.println("POEM FILE #1 : " + files.get(0).getExtension());
            statement = con.prepareStatement(sqlFil);
            for (CustomFile file : files) {
                statement.setString(1, file.getName());
                statement.setString(2, file.getExtension());
                statement.setInt(3, poeNo);
                statement.executeUpdate();
            }

        } catch (SQLException ex) {
            success = false;
            Logger.getLogger(CampaignMapper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return success;
    }

    public Poe getPoe(int kno) {
        Poe poe = new Poe();
        try (Connection con = DriverManager.getConnection(db.getURL(), db.getId(), db.getPw())) {

            String sql = "select navn, extension from filer join poe on filer.poeno = poe.poeno where kno = ?";

            PreparedStatement statement = null;

            statement = con.prepareStatement(sql);
            statement.setInt(1, kno);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String name = rs.getString(1);
                String ext = rs.getString(2);
                CustomFile file = new CustomFile(name, ext);

                /**
                 * * image files **
                 */
                if (ext.equals("png") || ext.equals("jpg") || ext.equals("jpeg") || ext.equals("pdf")) {
                    poe.addImageFile(file);
                }
                /**
                 * * video files **
                 */
                if (ext.equals("mp4") || ext.equals("avi")) {
                    poe.addVideoFile(file);
                }
                /**
                 * * music files **
                 */
                if (ext.equals("mp3")) {
                    poe.addMusicFile(file);
                }
                /**
                 * * text files **
                 */
                if (ext.equals("doc")) {
                    poe.addTextFile(file);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return poe;
    }

    public boolean deleteOldPoe(int kno) {

        boolean success = true;
        int result = 0;
        int poeNo = 0;
        try (Connection con = DriverManager.getConnection(db.getURL(), db.getId(), db.getPw())) {
            PreparedStatement statement = null;
            ResultSet rs = null;

            String sql1 = "select POENO from POE join KAMPAGNE on kampagne.kno = poe.KNO where kampagne.kno = ?";
            String sql2 = "delete from FILER where POENO = ?";
            String sql3 = "delete from POE where KNO = ?";

            /**
             * * Get PoeNo **
             */
            statement = con.prepareStatement(sql1);
            statement.setInt(1, kno);
            rs = statement.executeQuery();
            if (rs.next()) {
                System.out.println("DELETEOLDPOE RESULTSET: " + rs.getInt(1));
                poeNo = rs.getInt(1);
            } else {
                System.out.println("FEJL I RS.NEXT");
            }

            /**
             * * Delete Old Poe Files **
             */
            statement = con.prepareStatement(sql2);
            statement.setInt(1, poeNo);
            result = statement.executeUpdate();

            /**
             * * Delete Old Poe Files **
             */
            statement = con.prepareStatement(sql3);
            statement.setInt(1, kno);
            result = statement.executeUpdate();

        } catch (SQLException ex) {
            success = false;
            Logger.getLogger(PoeMapper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return success;
    }
}
