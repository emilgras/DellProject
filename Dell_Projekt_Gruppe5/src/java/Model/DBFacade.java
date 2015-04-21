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
    private PoeMapper poem;

    ArrayList<Partner> pendingPartners = new ArrayList<>();
    ArrayList<Campaign> pendingCampaigns = new ArrayList<>();
    ArrayList<Campaign> newestCampaigns = new ArrayList<>();
    //ArrayList<Partner> allPartners = new ArrayList<>();

    private DBConnector dbcon = new DBConnector();
    private Connection con = null;

//== Singleton start
    private static DBFacade instance = null;

    private DBFacade() {
        pm = new PartnerMapper();
        cm = new CampaignMapper();
        lm = new LoginMapper();
        poem = new PoeMapper();
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

    /**
     * * Dashboard view **
     */
    public ArrayList<Partner> getAllPendingPartners() {
        pendingPartners = pm.getAllPendingPartners(con);
        return pendingPartners;
    }

    public ArrayList<Campaign> getAllPendingCampaigns() {
        pendingCampaigns = cm.getAllPendingCampaigns(con);
        return pendingCampaigns;
    }

    public ArrayList<Campaign> getAllNewestCampaigns() {
        newestCampaigns = cm.getAllNewestCampaigns(con);
        return newestCampaigns;
    }

    /**
     * * Dashboard button interaction **
     */
    public boolean acceptPartner(int id) {
        boolean success = true;

        String cvr = pendingPartners.get(id).getCvr();

        if (pm.acceptPartner(cvr, con)) {
            pendingPartners.remove(id);
        } else {
            success = false;
        }
        return success;
    }

    public boolean acceptCampaign(int id) {
        boolean success = true;
        int kno = pendingCampaigns.get(id).getKno();
        if (cm.acceptCampaign(kno, con)) {
            System.out.println("SUCCESS...");
            newestCampaigns.add(pendingCampaigns.get(id));
            pendingCampaigns.remove(id);
            
            getInstance().updateCampaign(kno);
        } else {
            success = false;
        }
        return success;
    }

    public boolean updateCampaign(int kno) {
        return cm.updateCampaign(kno, dbcon.getConnection());
    }

    public boolean rollBackCampaign(int kno) {
        return cm.rollBackCampaign(kno, dbcon.getConnection());
    }

    /**
     * * POE **
     */
    public void uploadPoe(int kno, ArrayList<CustomFile> files) {
        poem.uploadPoe(kno, files, con);
    }
    
    public Campaign getCampaignDetail(int id) {
        return newestCampaigns.get(id);
    }
    
    public ArrayList<Campaign> getAllOwnPartnerCampaigns(int pno){
        return cm.getAllOwnPartnerCampaigns(pno, con);
    }
}
