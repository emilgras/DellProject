/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author AndersBjergfelt
 */
public class DBConnector
{

    private static final String driver = "oracle.jdbc.driver.OracleDriver";
    private static final String URL = "jdbc:oracle:thin:@datdb.cphbusiness.dk:1521:dat";
    private static final String id = "cphfl66";	
    private static final String pw = "cphfl66";
    
    private Connection con;
    
    public DBConnector()
    {
        try
        {
            Class.forName(driver);
            con = DriverManager.getConnection(URL, id, pw);   // The connection will be released upon program 
		  					      // termination by the garbage collector	
        } catch (Exception e)
        {
           
            System.out.println("error in DBConnector.getConnection()");
            System.out.println(e);
        }
    }

    public static String getURL() {
        return URL;
    }

    public static String getId() {
        return id;
    }

    public static String getPw() {
        return pw;
    }
    
    
    public Connection getConnection()
    {
      return con;
    }
}
