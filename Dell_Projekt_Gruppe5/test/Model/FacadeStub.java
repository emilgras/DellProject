/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Interfaces.Facade;
import Entities.Budget;
import Entities.Campaign;
import Entities.CustomFile;
import Entities.Partner;
import Entities.Poe;
import java.util.ArrayList;

/**
 *
 * @author EmilGras
 */
public class FacadeStub implements Facade {
    

    @Override
    public boolean acceptCampaign(int kno) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int acceptPartnerCount = 0;
    public boolean acceptPartner;
    @Override
    public boolean acceptPartner(String cvr) {
        acceptPartnerCount++;
        return acceptPartner;
    }

    public int createCampaignCount = 0;
    public boolean createCampaign = true;
    @Override
    public boolean createCampaign(Campaign campaign) {
        createCampaignCount++;
        return createCampaign;
    }

    public int createPartnerCount = 0;
    public boolean createPartner = true;
    @Override
    public boolean createPartner(Partner partner) {
        createPartnerCount++;
        return createPartner;
    }

    @Override
    public boolean deleteOldPoe(int kno) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Campaign> getAllNewestCampaigns() {
        ArrayList<Campaign> campaigns = new ArrayList();
        campaigns.add(new Campaign(234, "Dette er en beskrivelse", "test_dato_slut", "test_dato_start", 3000, 34));
        campaigns.add(new Campaign(235, "Dette er en beskrivelse", "test_dato_slut", "test_dato_start", 4000, 35));
        campaigns.add(new Campaign(236, "Dette er en beskrivelse", "test_dato_slut", "test_dato_start", 5000, 36));
        campaigns.add(new Campaign(237, "Dette er en beskrivelse", "test_dato_slut", "test_dato_start", 6000, 37));
        return campaigns;
    }

    @Override
    public ArrayList<Campaign> getAllOwnPartnerCampaigns(int pno) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Partner> getAllPartners() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Campaign> getAllPendingCampaigns() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Partner> getAllPendingPartners() {
        ArrayList<Partner> partners = new ArrayList();
        partners.add(new Partner("hans", "grethe", "Hans A/S", "12233445", "Denmark"));
        partners.add(new Partner("lars", "larsen", "Jysk A/S", "88997766", "Denmark"));
        partners.add(new Partner("billy", "jean", "RoCK A/S", "12131415", "Denmark"));
        return partners;
    }

    @Override
    public ArrayList<Budget> getAllPrices() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getCampaignStatusCount = 0;
    public String status;
    @Override
    public String getCampaignStatus(int kno) {
        getCampaignStatusCount++;
        return status;
    }

    @Override
    public String getLogin(String username, String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getNuvaerendeBelob() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getPno(String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getPoeCount = 0;
    public Poe poe = null;
    @Override
    public Poe getPoe(int kno) {
        getPoeCount++;
        return poe;
    }

    @Override
    public int getStartsBelob() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int isPartnerAcceptedCount = 0;
    public boolean accepted = true;
    @Override
    public boolean isPartnerAccepted(int pno) {
        isPartnerAcceptedCount++;
        return accepted;
    }

    @Override
    public boolean rollBackCampaign(int kno) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int updateCampaignCount = 0;
    public boolean updateCampaign = true;
    @Override
    public boolean updateCampaign(int kno) {
        updateCampaignCount++;
        return updateCampaign;
    }

    public int uploadPoeCount = 0;
    public boolean uploadPoe = true;
    @Override
    public boolean uploadPoe(int kno, ArrayList<CustomFile> files) {
        uploadPoeCount++;
        return uploadPoe;
    }

    @Override
    public int countPartners() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public int countCampaigns() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public int countCountries() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean deletePartner(int pno) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteCampaign(int kno) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
