/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;
import Control.Controller;
import Model.Campaign;
import Model.CampaignMapper;
import Model.DBConnector;
import Model.Partner;
import Model.DBFacade;
import Model.LoginMapper;
import Model.PartnerMapper;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
/**
 *
 * @author Frederik
 */
public class Test {
    
    public static void main(String[] args) throws SQLException {
        DBConnector db = new DBConnector();
        Connection con = db.getConnection();
        CampaignMapper cm = new CampaignMapper();
        PartnerMapper pm = new PartnerMapper();
        LoginMapper lm = new LoginMapper();
        java.util.Date date = new java.util.Date();
        
        java.sql.Date sqldate = new java.sql.Date(date.getTime());
        DBFacade pf = DBFacade.getInstance();
        Partner p = new Partner("frederik","hans","hans","12345678","2015-05-05","Danmark");
        //pf.getPartnerName();
        Controller cont = new Controller();
        
       // cm.getAllPartnerAcceptedCampaigns(con);
       //pf.showPartnerName();
//        
//        Campaign camp = new Campaign(12346,"Hans Service", "det kører","2015-05-05","2015-05-05","2015-05-05",2000,1234);
//        cont.createCampaign(camp);
       
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
        //PartnerFacade.getInstance().test();
        
        //PartnerFacade facade = PartnerFacade.getInstance();
        //System.out.println("Test: " + facade.getLogin("hans", "hans"));

        
        
//        PartnerMapper pm = new PartnerMapper();
//        System.out.println(pm.getLogin("hans", "haans", con));
//        try {
//            ArrayList<Campaign> list = new ArrayList<Campaign>();
//            list = cm.getAllCampaigns(con);
//            for (int i = 0; i < list.size(); i++) {
//                System.out.println(list.get(i).toString());
//            }
//        } catch (Exception e) {
//            System.out.println("første ups");
//        }
        System.out.println(pm.createPartner(p, con));
        
        try {
            con.close();
        } catch (Exception e) {
            System.out.println("ups");
        }
        
        
    }
}
