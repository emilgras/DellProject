/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Interfaces.FacadeIF;
import Entities.Budget;
import Entities.Partner;
import Entities.CustomFile;
import Entities.Campaign;
import Entities.Poe;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBFacade implements FacadeIF {

    private PartnerMapper pm;
    private CampaignMapper cm;
    private LoginMapper lm;
    private PoeMapper poem;
    private BudgetMapper bm;
    private StatsMapper sm;

    //== Singleton start 
    private static DBFacade instance = null;
    
    private DBFacade() {
        pm = new PartnerMapper();
        cm = new CampaignMapper();
        lm = new LoginMapper();
        poem = new PoeMapper();
        bm = new BudgetMapper();
        sm = new StatsMapper();
    }
    
    public static DBFacade getDBFacadeInstance() {
        if (instance == null) {
            instance = new DBFacade();
        }
        return instance;
    }
     //== Singleton end
    
    @Override
    public String getLogin(String username, String password) {
        return lm.getLogin(username, password);
    }

    @Override
    public int getPno(String username) {
        return pm.getPno(username);
    }

    @Override
    public boolean createPartner(Partner partner) {
        return pm.createPartner(partner);
    }

    @Override
    public boolean createCampaign(Campaign campaign) {
        return cm.insertCampaign(campaign);
    }

    /**
     * * Budget **
     */
    @Override
    public int getNuvaerendeBelob() {
        return bm.getNuvaerendeBelob();
    }

    @Override
    public int getStartsBelob() {
        return bm.getStartsBelob();
    }

    @Override
    public boolean updateMoneyUsed(int i) {
        return bm.updateMoneyUsed(i);
    }

    /**
     * * Dashboard view **
     */
    @Override
    public ArrayList<Partner> getAllPendingPartners() {
        return pm.getAllPendingPartners();
    }

    @Override
    public ArrayList<Campaign> getAllPendingCampaigns() {
        return cm.getAllPendingCampaigns();
    }

    @Override
    public ArrayList<Campaign> getAllNewestCampaigns() {
        return cm.getAllNewestCampaigns();
    }

    @Override
    public ArrayList<Campaign> getAllOwnPartnerCampaigns(int pno) {
        return cm.getAllOwnPartnerCampaigns(pno);
    }
    @Override
    public ArrayList<Partner> getAllPartners(){
        return pm.getAllPartners();
    }

    @Override
    public ArrayList<Budget> getAllPrices() {
        return bm.getAllPrices();
    }

    /**
     * * Dashboard button interaction **
     */
    @Override
    public boolean acceptPartner(String cvr) {
        return pm.acceptPartner(cvr);
    }
    
    @Override
    public boolean deletePartner(int pno) {
        return pm.deletePartner(pno);
    }

    @Override
    public boolean acceptCampaign(int kno) {
        return cm.acceptCampaign(kno);
    }
    
    @Override
    public boolean updateCampaign(int kno) {
        return cm.updateCampaign(kno);
    }
    
    @Override
    public String getCampaignStatus(int kno) {
        return cm.getCampaignStatus(kno);
    }
    
    @Override
    public boolean deleteOldPoe(int kno) {
        return poem.deleteOldPoe(kno);
    }

    @Override
    public boolean rollBackCampaign(int kno) {
        return cm.rollBackCampaign(kno);
    }
    
    @Override
    public boolean deleteCampaign(int kno) {
        return cm.deleteCampaign(kno);
    }
    

    

    /**
     * * POE **
     */
    @Override
    public boolean uploadPoe(int kno, ArrayList<CustomFile> files) {
        return poem.uploadPoe(kno, files);
    }

    @Override
    public Poe getPoe(int kno) {
        return poem.getPoe(kno);
    }
    
    @Override
    public boolean isPartnerAccepted(int pno) {
        return pm.isPartnerAccepted(pno);
    }
    
    /**
     * * STATS **
     */
    public int countPartners() {
        return sm.countPartners();
    }
    
    public int countCampaigns() {
        return sm.countCampaigns();
    }
    
    public int countCountries() {
        return sm.countCountries();
    }

}
