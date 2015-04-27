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

    //== Singleton start
    private static DBFacade instance = null;

    private DBFacade() {
        pm = new PartnerMapper();
        cm = new CampaignMapper();
        lm = new LoginMapper();
        poem = new PoeMapper();
        bm = new BudgetMapper();

    }

    public static DBFacade getInstance() {
        if (instance == null) {
            instance = new DBFacade();
        }
        return instance;
    }
    //== Singleton end

    public String getLogin(String username, String password) {
        return lm.getLogin(username, password);
    }

    public int getPno(String username) {
        return pm.getPno(username);
    }

    public boolean createPartner(Partner partner) {
        return pm.createPartner(partner);
    }

    public boolean createCampaign(Campaign campaign) {
        return cm.insertCampaign(campaign);
    }

    /**
     * * Budget **
     */
    public int getNuvaerendeBelob() {
        return bm.getNuvaerendeBelob();
    }

    public int getStartsBelob() {
        return bm.getStartsBelob();
    }

    private boolean updateMoneyUsed(int i) {
        return bm.updateMoneyUsed(i);
    }

    /**
     * * Dashboard view **
     */
    public ArrayList<Partner> getAllPendingPartners() {
        return pm.getAllPendingPartners();
    }

    public ArrayList<Campaign> getAllPendingCampaigns() {
        return cm.getAllPendingCampaigns();
    }

    public ArrayList<Campaign> getAllNewestCampaigns() {
        return cm.getAllNewestCampaigns();
    }

    public ArrayList<Campaign> getAllOwnPartnerCampaigns(int pno) {
        return cm.getAllOwnPartnerCampaigns(pno);
    }
    public ArrayList<Partner> getAllPartners(){
        return pm.getAllPartners();
    }

    public ArrayList<Budget> getAllPrices() {
        return bm.getAllPrices();
    }

    /**
     * * Dashboard button interaction **
     */
    public boolean acceptPartner(String cvr) {
        return pm.acceptPartner(cvr);
    }

    public boolean acceptCampaign(int kno) {
        return cm.acceptCampaign(kno);
    }
    
    public boolean updateCampaign(int kno) {
        return cm.updateCampaign(kno);
    }
    
    public String getCampaignStatus(int kno) {
        return cm.getCampaignStatus(kno);
    }
    
    public boolean deleteOldPoe(int kno) {
        return poem.deleteOldPoe(kno);
    }

    public boolean rollBackCampaign(int kno) {
        return cm.rollBackCampaign(kno);
    }
    

    

    /**
     * * POE **
     */
    public boolean uploadPoe(int kno, ArrayList<CustomFile> files) {
        return poem.uploadPoe(kno, files);
    }

    public Poe getPoe(int kno) {
        return poem.getPoe(kno);
    }
    
    public boolean isPartnerAccepted(int pno) {
        return pm.isPartnerAccepted(pno);
    }

}
