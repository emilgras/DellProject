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
        return lm.getLogin(username, password, dbcon.getConnection());
    }
    
    public int getPno(String username) {
        return pm.getPno(username, dbcon.getConnection());
    }

    public String createPartner(Partner partner) {
        return pm.createPartner(partner, dbcon.getConnection());
    }

    public boolean createCampaign(Campaign campaign) {
        return cm.insertCampaign(campaign, dbcon.getConnection());
    }
    
    /*** Dashboard view ***/
    
    public ArrayList<Partner> getAllPendingPartners(){
        return pm.getAllPendingPartners(dbcon.getConnection());
    }
    
    public ArrayList<Campaign> getAllPendingCampaigns() {
       return cm.getAllPendingCampaigns(dbcon.getConnection());
    }
   
    public ArrayList<Campaign> getAllNewestCampaigns() {
        return cm.getAllNewestCampaigns(dbcon.getConnection());
    }

    /*** Dashboard button interaction ***/
    
    public boolean acceptPartner(String cvr){
        return pm.acceptPartner(cvr, dbcon.getConnection());
    }
    
    public boolean acceptCampaign(int kno) {
        System.out.println("DBFacade: " + cm.acceptCampaign(kno, dbcon.getConnection()));
        return cm.acceptCampaign(kno, dbcon.getConnection());
    }
    
    public boolean updateCampaign(int kno) {
        return cm.updateCampaign(kno, dbcon.getConnection());
    }
    
     public boolean rollBackCampaign(int kno) {
        return cm.rollBackCampaign(kno, dbcon.getConnection());
    }
}
