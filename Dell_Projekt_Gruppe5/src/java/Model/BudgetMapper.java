/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Frederik
 */
public class BudgetMapper {
    
    public ArrayList<Budget> getAllPrices(Connection con){
        ArrayList<Budget> list = new ArrayList<>();
        String sql = "select kno, navn, pris from partner join kampagne on partner.pno = kampagne.pno";
        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement(sql);
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
    
    public int getStartsBelob(Connection con){
        String sql = "select starts_belob from budget";
        PreparedStatement statement = null;
        ResultSet rs = null;
        int i = 0;
        try {
            statement = con.prepareStatement(sql);
            rs = statement.executeQuery();
            rs.next();
            i = rs.getInt(1);
        } catch (Exception e) {
        }
        return i;
    }
    
    public int getNuvaerendeBelob(Connection con){
        String sql = "select nuvaernde_belob from budget";
        PreparedStatement statement = null;
        ResultSet rs = null;
        int i = 0;
        try {
            statement = con.prepareStatement(sql);
            rs = statement.executeQuery();
            rs.next();
            i = rs.getInt(1);
        } catch (Exception e) {
        }
        return i;
    }
    
    public boolean updateMoneyUsed(int i, Connection con){
        String sql = "update budget set nuvaernde_belob = " + (getNuvaerendeBelob(con) + i) + " where starts_belob = " + getStartsBelob(con);
        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            rs.next();
            
        } catch (Exception e) {
        }
        return true;
    }
}
