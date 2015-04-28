package Model;

import Entities.Partner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ABjergfelt
 */
public class PartnerMapper {

    DBConnector db = new DBConnector();
    int count = 0;
    private String message = "";

    public String getLogin(String username, String password) {
        Partner p = null;
        try (Connection con = DriverManager.getConnection(db.getURL(), db.getId(), db.getPw())) {
            String SQLString1
                    = "select count(*) as count from partner where brugernavn = ? and password = ?";

            PreparedStatement statement = null;

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

        if (count == 0) {
            message = "Invalid username or password";

        }
        System.out.println(count);
        return message;

    }

    public boolean createPartner(Partner partner) {

        boolean success = true;
        try (Connection con = DriverManager.getConnection(db.getURL(), db.getId(), db.getPw())) {
            String sql1 = "INSERT INTO bruger values (?,?,?)";
            String sql2 = "INSERT INTO partner (pno, cvr, land, navn, dato, brugernavn) VALUES (?,?,?,?,?,?)";
            String SQLString1
                    = "select pno_increment.nextval  "
                    + "from dual";

            String user = partner.getUsername();
            String pass = partner.getPassword();
            String name = partner.getName();
            String cvr = partner.getCvr();
            String country = partner.getCountry();
            String date = "NULL";

            PreparedStatement insertStatement = con.prepareStatement(SQLString1);
            ResultSet rs = insertStatement.executeQuery();
            if (rs.next()) {
                partner.setPno(rs.getInt(1));
            }

            insertStatement = con.prepareStatement(sql1);
            insertStatement.setString(1, user);
            insertStatement.setString(2, pass);
            insertStatement.setString(3, "partner");
            insertStatement.executeUpdate();

            insertStatement = con.prepareStatement(sql2);
            insertStatement.setInt(1, partner.getPno());
            insertStatement.setString(2, cvr);
            insertStatement.setString(3, country);
            insertStatement.setString(4, name);
            insertStatement.setString(5, date);
            insertStatement.setString(6, user);
            insertStatement.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            success = false;
        }

        return success;
    }

    public boolean acceptPartner(String cvr) {
        try (Connection con = DriverManager.getConnection(db.getURL(), db.getId(), db.getPw())) {
            String sqlString2 = "update partner set dato = ? where cvr = ?";

            PreparedStatement statement = con.prepareStatement(sqlString2);
            java.util.Date date = new java.util.Date();
            java.sql.Date sqldate = new java.sql.Date(date.getTime());
            statement = con.prepareStatement(sqlString2);
            statement.setString(2, cvr);
            statement.setDate(1, sqldate);

            statement.executeUpdate();
            return true;

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return true;
    }

    public ArrayList<Partner> getAllPendingPartners() {
        ArrayList<Partner> pArray = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(db.getURL(), db.getId(), db.getPw())) {
            String sqlString = "select navn,cvr,land from partner where dato = 'NULL'";

            PreparedStatement statement = con.prepareStatement(sqlString);
            ResultSet rs = statement.executeQuery();
            int count = 0;
            while (rs.next()) {

                String partnerName = rs.getString(1);
                String partnerCVR = rs.getString(2);
                String partnerCountry = rs.getString(3);

                Partner p = new Partner(partnerName, partnerCVR, partnerCountry);

                pArray.add(p);
                count++;
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return pArray;

    }

    public ArrayList<Partner> getAllPartners() {
        ArrayList<Partner> pArray = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(db.getURL(), db.getId(), db.getPw())) {
            String sqlString = "select navn,cvr,land from partner where dato is not null";

            PreparedStatement statement = con.prepareStatement(sqlString);
            ResultSet rs = statement.executeQuery();
            int count = 0;
            while (rs.next()) {

                String partnerName = rs.getString(1);
                String partnerCVR = rs.getString(2);
                String partnerCountry = rs.getString(3);

                Partner p = new Partner(partnerName, partnerCVR, partnerCountry);

                pArray.add(p);
                count++;
            }
            //statement.close();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return pArray;

    }

    public int getPno(String username) {
        int pno = 0;
        try (Connection con = DriverManager.getConnection(db.getURL(), db.getId(), db.getPw())) {
            String sqlString = "select pno from partner where brugernavn = ?";
            PreparedStatement statement = null;

            statement = con.prepareStatement(sqlString);
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            String status = "noget gik galt";
            if (rs.next()) {
                pno = Integer.parseInt(rs.getString(1));
            }
            statement.close();
        } catch (SQLException | NumberFormatException e) {
            System.out.println(e);
        }
        return pno;
    }

    public boolean isPartnerAccepted(int pno) {
        String status = "";
        boolean accepted = true;
        try (Connection con = DriverManager.getConnection(db.getURL(), db.getId(), db.getPw())) {
            String sqlString = "select dato from partner where pno = ?";
            PreparedStatement statement = null;
            ResultSet rs = null;

            statement = con.prepareStatement(sqlString);
            statement.setInt(1, pno);
            rs = statement.executeQuery();
            if (rs.next()) {
                status = rs.getString(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (status.equals("NULL")) {
            accepted = false;
        }

        return accepted;
    }
}
