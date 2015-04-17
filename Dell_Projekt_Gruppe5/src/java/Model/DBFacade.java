/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBFacade {

    private PartnerMapper pm;
    private CampaignMapper cm;
    private LoginMapper lm;
    
    private DBConnector dbcon = new DBConnector();
    private Connection con = null;
    
//== Singleton start
    
    private static DBFacade instance = null;
    
    private DBFacade() {
        pm = new PartnerMapper();
        cm = new CampaignMapper();
        lm = new LoginMapper();
        con = dbcon.getConnection();
    }

    public static DBFacade getInstance() {
        if (instance == null) {
            instance = new DBFacade();
        }
        return instance;
    }
	  //== Singleton end

    public String getLogin(String username, String password) {
        return lm.getLogin(username, password, con);
    }
    
    public int getPno(String username) {
        return pm.getPno(username, con);
    }

    public String createPartner(Partner partner) {
        return pm.createPartner(partner, con);
    }

    public boolean createCampaign(Campaign campaign) {
        return cm.insertCampaign(campaign, con);
    }
    
    /*** Dashboard view ***/
    
    public ArrayList<Partner> getAllPendingPartners(){
        return pm.getAllPendingPartners(con);
    }
    
    public ArrayList<Campaign> getAllPendingCampaigns() {
       return cm.getAllPendingCampaigns(con);
    }
   
    public ArrayList<Campaign> getAllNewestCampaigns() {
        return cm.getAllNewestCampaigns(con);
    }

    /*** Dashboard button interaction ***/
    
    public boolean acceptPartner(String cvr){
        return pm.acceptPartner(cvr, con);
    }
    
    public boolean acceptCampaign(int kno) {
        System.out.println("DBFacade: " + cm.acceptCampaign(kno, con));
        return cm.acceptCampaign(kno, con);
    }
    
    public boolean updateCampaign(int kno) {
        return cm.updateCampaign(kno, con);
    }
    
     public boolean rollBackCampaign(int kno) {
        return cm.rollBackCampaign(kno, con);
    }
}
