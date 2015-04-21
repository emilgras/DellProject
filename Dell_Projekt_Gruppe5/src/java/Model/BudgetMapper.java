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
        String sql = "select kno, navn, pris from partner join kampagne on parter.pno = kampagne.pno";
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
    
}
