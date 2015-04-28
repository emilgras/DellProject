/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Entities.Budget;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Frederik
 */
public class BudgetMapper {
    DBConnector db = new DBConnector();
    
    public ArrayList<Budget> getAllPrices(){
       ArrayList<Budget> list = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(db.getURL(), db.getId(),db.getPw())){
            
        
        
        String sql = "select kno, navn, pris from partner join kampagne on partner.pno = kampagne.pno";
        PreparedStatement statement = null;
        
            statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Budget b = new Budget(rs.getInt(1),rs.getString(2),rs.getFloat(3));
                list.add(b);
            }
       
        } catch (Exception e) {
            System.out.println("Fejl i getAllPrices()");
        }
        return list;
    }
    
    public int getStartsBelob(){
        int i = 0;
        try(Connection connection = DriverManager.getConnection(db.getURL(), db.getId(),db.getPw())){
        String sql = "select starts_belob from budget";
        PreparedStatement statement = null;
        ResultSet rs = null;
        
        
            statement = connection.prepareStatement(sql);
            rs = statement.executeQuery();
            rs.next();
            i = rs.getInt(1);
        } catch (Exception e) {
            System.out.println("Fejl i getStartsBelob");
        }
        return i;
    }
    
    public int getNuvaerendeBelob(){
        int i = 0;
        try(Connection connection = DriverManager.getConnection(db.getURL(), db.getId(),db.getPw())){
        String sql = "select nuvaernde_belob from budget";
        PreparedStatement statement = null;
        ResultSet rs = null;
        
     
            statement = connection.prepareStatement(sql);
            rs = statement.executeQuery();
            rs.next();
            i = rs.getInt(1);
        } catch (Exception e) {
            System.out.println("Fejl i getNuavaerendeBelob()");
        }
        return i;
    }
    
    public boolean updateMoneyUsed(int i){
         try(Connection connection = DriverManager.getConnection(db.getURL(), db.getId(),db.getPw())){
        String sql = "update budget set nuvaernde_belob = " + (getNuvaerendeBelob() + i + " where starts_belob = " + getStartsBelob());
        PreparedStatement statement = null;
        
            statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            rs.next();
            
        } catch (Exception e) {
             System.out.println("Fejl i updateMoneyUsed()");
        }
        return true;
    }
}
