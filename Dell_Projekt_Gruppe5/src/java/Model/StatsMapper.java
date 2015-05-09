package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * * @author EmilGras **
 */
public class StatsMapper {

    protected StatsMapper() {
    }

    protected int countPartners() {
        int count = 0;

        try (Connection con = DriverManager.getConnection(DBDetail.URL, DBDetail.ID, DBDetail.PW)) {
            String sql = "select count(pno) as count from partner where dato != 'NULL'";
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CampaignMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    protected int countCampaigns() {
        int count = 0;

        try (Connection con = DriverManager.getConnection(DBDetail.URL, DBDetail.ID, DBDetail.PW)) {
            String sql = "select count(kno) as count from kampagne where status != 'Pending'";
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CampaignMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    protected int countCountries() {
        int count = 0;
        Map<String, Integer> occurrencies = new HashMap<String, Integer>();

        try (Connection con = DriverManager.getConnection(DBDetail.URL, DBDetail.ID, DBDetail.PW)) {
            String sql = "select land from partner where dato != 'NULL'";
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String country = rs.getString(1);
                occurrencies.put(country, occurrencies.containsKey(country) ? occurrencies.get(country) + 1 : 1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CampaignMapper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return occurrencies.size();
    }
}
