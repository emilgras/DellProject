/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataSourceLayer;
import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author AndersBjergfelt
 */
public class DBConnector
{

    private static String driver = "oracle.jdbc.driver.OracleDriver";
    private static String URL = "jdbc:oracle:thin:@datdb.cphbusiness.dk:1521:dat";
    private static String id = "cphfl66";			
    private static String pw = "cphfl66";
    
    private Connection con;
    
    private DBConnector()
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
    
    
    
    public Connection getConnection()
    {
      return con;
    }
}
