/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ABjergfelt
 */
public class DBFacade {

    private PartnerMapper pm;
    private CampaignMapper cm;
    
    private DBConnector dbcon = new DBConnector();
    private Connection con = null;
    
//== Singleton start
    
    private static DBFacade instance = null;
    
    private DBFacade() {
        pm = new PartnerMapper();
        cm = new CampaignMapper();
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
        return pm.getLogin(username, password, con);
    }
    
    public int getPno(String username) {
        return pm.getPno(username, con);
    }

    public String createPartner(Partner partner) {
        return pm.createPartner(partner, con);
    }

    public boolean createCampaign(Campaign campaign, int pno) {
        return cm.insertCampaign(campaign, pno, con);
    }
    
    public void test() {
        System.out.println("VIRK FOR HELVED!!!");
    }
    
    public ArrayList<Partner> showPartnerName(){
       
        return pm.showPartnerName(con);
    }
   
}
