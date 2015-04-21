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
    private BudgetMapper bm;

    /*** Admin ***/
    ArrayList<Partner> pendingPartners = new ArrayList<>();
    ArrayList<Campaign> pendingCampaigns = new ArrayList<>();
    ArrayList<Campaign> newestCampaigns = new ArrayList<>();
    ArrayList<Budget> prices = new ArrayList<>();
    //ArrayList<Partner> allPartners = new ArrayList<>();
    
    /*** Partner ***/
    ArrayList<Campaign> partnersCampaigns = new ArrayList<>();

    private DBConnector dbcon = new DBConnector();
    
    private Connection con = null;

    //== Singleton start
    private static DBFacade instance = null;

    private DBFacade() {
        pm = new PartnerMapper();
        cm = new CampaignMapper();
        lm = new LoginMapper();
        poem = new PoeMapper();
        bm = new BudgetMapper();
        con = dbcon.getConnection();
    }
    
    /*** ArrayList Getters ***/
    public ArrayList<Partner> getPendingPartners() {
        return pendingPartners;
    }

    public ArrayList<Campaign> getPendingCampaigns() {
        return pendingCampaigns;
    }

    public ArrayList<Campaign> getNewestCampaigns() {
        return newestCampaigns;
    }

    public ArrayList<Campaign> getPartnersCampaigns() {
        return partnersCampaigns;
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
    
    public ArrayList<Budget> getAllPrices(){
        prices = bm.getAllPrices(con);
        return prices;
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
            newestCampaigns.add(pendingCampaigns.get(id));
            pendingCampaigns.remove(id);
            cm.updateCampaign(kno, con);
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
    public boolean uploadPoe(int kno, int id, ArrayList<CustomFile> files) {
        boolean success = false;
        if (poem.uploadPoe(kno, files, con)) {
            pendingCampaigns.add(partnersCampaigns.get(id));
            updateCampaign(kno);
            success = true;
        } 
        return success;
    }
    
    
    public ArrayList<Campaign> getAllOwnPartnerCampaigns(int pno){
        partnersCampaigns = cm.getAllOwnPartnerCampaigns(pno, con);
        return partnersCampaigns;
    }
    
    public int getKnoForCampaign(int id) {
        return partnersCampaigns.get(id).getKno();
    }
    
    public Campaign getNewestCampaignDetail(int id) {
        return newestCampaigns.get(id);
    }
    
    public Campaign getPendingCampaignDetail(int id) {
        return pendingCampaigns.get(id);
    }
    
    public Poe getPoe(int kno) {
        return poem.getPoe(kno, con);
    }
    
    public Poe getPoeFromNewestCampaigns(int id) {
        int pno = newestCampaigns.get(id).getPno();
        return poem.getPoe(pno, con);
    }
    
    public Poe getPoeFromPendingCampaigns(int id) {
        int pno = pendingCampaigns.get(id).getPno();
        return poem.getPoe(pno, con);
    }

}
