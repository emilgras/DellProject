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

    /**
     * * Admin **
     */
    ArrayList<Partner> pendingPartners = new ArrayList<>();
    ArrayList<Campaign> pendingCampaigns = new ArrayList<>();
    ArrayList<Campaign> newestCampaigns = new ArrayList<>();
    ArrayList<Budget> prices = new ArrayList<>();
    //ArrayList<Partner> allPartners = new ArrayList<>();

    /**
     * * Partner **
     */
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

    /**
     * * ArrayList Getters **
     */
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
     * * Budget **
     */
    public int getNuvaerendeBelob() {
        return bm.getNuvaerendeBelob(con);
    }

    public int getStartsBelob() {
        return bm.getStartsBelob(con);
    }

    private boolean updateMoneyUsed(int i) {
        return bm.updateMoneyUsed(i, con);
    }

    /**
     * * Dashboard view **
     */
    public ArrayList<Partner> getAllPendingPartners() {
        pendingPartners = pm.getAllPendingPartners(con);
        return pm.getAllPendingPartners(con);
    }

    public ArrayList<Campaign> getAllPendingCampaigns() {
        pendingCampaigns = cm.getAllPendingCampaigns(con);
        return cm.getAllPendingCampaigns(con);
    }

    public ArrayList<Campaign> getAllNewestCampaigns() {
        newestCampaigns = cm.getAllNewestCampaigns(con);
        return cm.getAllNewestCampaigns(con);
    }

    public ArrayList<Campaign> getAllOwnPartnerCampaigns(int pno) {
        partnersCampaigns = cm.getAllOwnPartnerCampaigns(pno, con);
        return cm.getAllOwnPartnerCampaigns(pno, con);
    }

    public ArrayList<Budget> getAllPrices() {
        prices = bm.getAllPrices(con);
        return prices;
    }

    /**
     * * Dashboard button interaction **
     */
    public boolean acceptPartner(String cvr) {
        return pm.acceptPartner(cvr, con);
    }

    public boolean acceptCampaign(int kno) {
        return cm.acceptCampaign(kno, con);
    }
    
    public boolean updateCampaign(int kno) {
        return cm.updateCampaign(kno, con);
    }
    
    public String getCampaignStatus(int kno) {
        return cm.getCampaignStatus(kno, con);
    }
    
    public boolean deleteOldPoe(int kno) {
        return poem.deleteOldPoe(kno, con);
    }

    public boolean rollBackCampaign(int kno) {
        return cm.rollBackCampaign(kno, con);
    }
    

    

    /**
     * * POE **
     */
    public boolean uploadPoe(int kno, int id, ArrayList<CustomFile> files) {
        boolean success = false;
        if (poem.uploadPoe(kno, files, con)) {
            cm.updateCampaign(kno, con);
            success = true;
        }
        return success;
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
    
    public boolean isPartnerAccepted(int pno) {
        System.out.println("TÅÅÅST: " + pm.isPartnerAccepted(pno, con));
        return pm.isPartnerAccepted(pno, con);
    }

}
