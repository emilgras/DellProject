/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;
import Model.Campaign;
import Model.CampaignMapper;
import Model.DBConnector;
import Model.Partner;
import Model.PartnerMapper;
import java.sql.Connection;
import java.util.ArrayList;
/**
 *
 * @author Frederik
 */
public class Test {
    
    public static void main(String[] args) {
        DBConnector db = new DBConnector();
        Connection con = db.getConnection();
        
//        CampaignMapper cm = new CampaignMapper();
        java.util.Date date = new java.util.Date();
        java.sql.Date sqldate = new java.sql.Date(date.getTime());
//        Campaign camp = new Campaign(12345,"Hans Service", "det kører", sqldate,sqldate,sqldate,20000,1234);
//        ArrayList<Campaign> list = new ArrayList<Campaign>();
//        list.add(camp);
//        try {
//            cm.insertCampaign(list, con);
//        } catch (Exception e) {
//            System.out.println("ups");
//        }
//        
//        try {
//            con.close();
//        } catch (Exception e) {
//            
//        }
        
        
        
        PartnerMapper pm = new PartnerMapper();
        pm.createPartner(new Partner(1234, "jeppe", "jeppe", "jeppe", "88888888", sqldate), con);
        //System.out.println(pm.getLogin("hans", "haans", con));
        
        
        try {
            con.close();
        } catch (Exception e) {
        }
        
        
    }
}
