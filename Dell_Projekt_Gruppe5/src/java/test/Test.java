/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;
import Model.Campaign;
import Model.CampaignMapper;
import Model.DBConnector;
import Model.Partner;
import Model.PartnerFacade;
import Model.PartnerMapper;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
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
        java.util.Date date = new java.util.Date();
        java.sql.Date sqldate = new java.sql.Date(date.getTime());
        
        //pm.updatePartnerStatus(5, con);
        
        
        //Campaign camp = new Campaign(12346,"Hans Service", "det kører","2015-05-05","2015-05-05","2015-05-05",2000,1234);
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
        //PartnerFacade.getInstance().test();
        
        //PartnerFacade facade = PartnerFacade.getInstance();
        //System.out.println("Test: " + facade.getLogin("hans", "hans"));

        
        
//        PartnerMapper pm = new PartnerMapper();
//        System.out.println(pm.getLogin("hans", "haans", con));
        try {
            //cm.insertCampaign(camp, con);
        } catch (Exception e) {
            System.out.println("første ups");
        }
        try {
            con.close();
        } catch (Exception e) {
            System.out.println("ups");
        }
        
        
    }
}
