package Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestInsertPartner {

    private DBConnector db;
    private Connection conn;

    public String createPartner(Partner partner) {
        db = new DBConnector();
        conn = db.getConnection();

        String errorMessage = "";

        String sql = "INSERT INTO partner (cvr, navn, dato, brugernavn, password, rolle) VALUES (?,?,?,?,?,?)";

        String user = partner.getUsername();
        String pass = partner.getPassword();
        String name = partner.getName();
        String cvr = partner.getCvr();
        Date date = partner.getDate();

        try {
            PreparedStatement insertStatement = conn.prepareStatement(sql);

            insertStatement.setString(1, cvr);
            insertStatement.setString(2, name);
            insertStatement.setDate(3, date);
            insertStatement.setString(4, user);
            insertStatement.setString(5, pass);
            insertStatement.setString(6, "Partner");

            insertStatement.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            errorMessage = "Not able to create user at the moment, please try again.";
        }
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(TestInsertPartner.class.getName()).log(Level.SEVERE, null, ex);
        }

        return errorMessage;
    }

}
