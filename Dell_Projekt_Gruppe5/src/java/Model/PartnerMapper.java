/*
* Frederik og Anders har arbejdet i denne klasse
*/
package Model;

import Entities.Partner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * * @author Anders **
 */
public class PartnerMapper {

    protected PartnerMapper() {
    }

    protected boolean createPartner(Partner partner) {

        boolean success = true;
        try (Connection con = DriverManager.getConnection(DBDetail.URL, DBDetail.ID, DBDetail.PW)) {
            String sql1
                    = "select pno_increment.nextval  "
                    + "from dual";
            String sql2 = "INSERT INTO bruger values (?,?,?)";
            String sql3 = "INSERT INTO partner (pno, cvr, land, navn, dato, brugernavn) VALUES (?,?,?,?,?,?)";

            String user = partner.getUsername();
            String pass = partner.getPassword();
            String name = partner.getName();
            String cvr = partner.getCvr();
            String country = partner.getCountry();
            String date = "NULL";

            PreparedStatement insertStatement = con.prepareStatement(sql1);
            ResultSet rs = insertStatement.executeQuery();
            if (rs.next()) {
                partner.setPno(rs.getInt(1));
            }

            insertStatement = con.prepareStatement(sql2);
            insertStatement.setString(1, user);
            insertStatement.setString(2, pass);
            insertStatement.setString(3, "partner");
            insertStatement.executeUpdate();

            insertStatement = con.prepareStatement(sql3);
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

    protected boolean acceptPartner(String cvr) {
        try (Connection con = DriverManager.getConnection(DBDetail.URL, DBDetail.ID, DBDetail.PW)) {
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

    protected ArrayList<Partner> getAllPendingPartners() {
        ArrayList<Partner> pArray = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(DBDetail.URL, DBDetail.ID, DBDetail.PW)) {
            String sqlString = "select navn,cvr,land,pno from partner where dato = 'NULL'";

            PreparedStatement statement = con.prepareStatement(sqlString);
            ResultSet rs = statement.executeQuery();
            int count = 0;
            while (rs.next()) {

                String partnerName = rs.getString(1);
                String partnerCVR = rs.getString(2);
                String partnerCountry = rs.getString(3);
                int partnerPno = rs.getInt(4);

                Partner p = new Partner(partnerName, partnerCVR, partnerCountry, partnerPno);

                pArray.add(p);
                count++;
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return pArray;

    }

    protected ArrayList<Partner> getAllPartners() {
        ArrayList<Partner> pArray = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(DBDetail.URL, DBDetail.ID, DBDetail.PW)) {
            String sqlString = "select navn,cvr,land from partner where dato != 'NULL'";

            PreparedStatement statement = con.prepareStatement(sqlString);
            ResultSet rs = statement.executeQuery();
            int count = 0;
            while (rs.next()) {

                String partnerName = rs.getString(1);
                String partnerCVR = rs.getString(2);
                String partnerCountry = rs.getString(3);

                Partner p = new Partner(partnerName, partnerCVR, partnerCountry, 0);

                pArray.add(p);
                count++;
            }
            //statement.close();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return pArray;

    }

    protected int getPno(String username) {
        int pno = 0;
        try (Connection con = DriverManager.getConnection(DBDetail.URL, DBDetail.ID, DBDetail.PW)) {
            String sqlString = "select pno from partner where brugernavn = ?";
            PreparedStatement statement = con.prepareStatement(sqlString);
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            String status = "noget gik galt";
            if (rs.next()) {
                pno = Integer.parseInt(rs.getString(1));
            }
        } catch (SQLException | NumberFormatException e) {
            System.out.println(e);
        }
        return pno;
    }

    protected boolean isPartnerAccepted(int pno) {
        String status = "";
        boolean accepted = true;
        try (Connection con = DriverManager.getConnection(DBDetail.URL, DBDetail.ID, DBDetail.PW)) {
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

    protected boolean deletePartner(int pno) {
        int i = 0;
        try (Connection con = DriverManager.getConnection(DBDetail.URL, DBDetail.ID, DBDetail.PW)) {
            String brugernavn = "";
            String sql1 = "select brugernavn from partner where pno = ?";
            String sql2 = "delete from partner where pno = ?";

            PreparedStatement statement = con.prepareStatement(sql1);
            statement.setInt(1, pno);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                brugernavn = rs.getString(1);
            }

            statement = con.prepareStatement(sql2);
            statement.setInt(1, pno);
            i += statement.executeUpdate();

            String sql3 = "delete from bruger where brugernavn = ?";
            statement = con.prepareStatement(sql3);
            statement.setString(1, brugernavn);
            i += statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return i == 2;
    }
}
