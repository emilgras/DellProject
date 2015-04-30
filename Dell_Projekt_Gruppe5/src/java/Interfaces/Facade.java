/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

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
public interface Facade {

    boolean acceptCampaign(int kno);

    /**
     * * Dashboard button interaction **
     */
    boolean acceptPartner(String cvr);
    
    boolean deletePartner(int pno);

    boolean createCampaign(Campaign campaign);

    boolean createPartner(Partner partner);

    boolean deleteOldPoe(int kno);

    ArrayList<Campaign> getAllNewestCampaigns();

    ArrayList<Campaign> getAllOwnPartnerCampaigns(int pno);

    ArrayList<Partner> getAllPartners();

    ArrayList<Campaign> getAllPendingCampaigns();
    
    boolean deleteCampaign(int kno);

    /**
     * * Dashboard view **
     */
    ArrayList<Partner> getAllPendingPartners();

    ArrayList<Budget> getAllPrices();

    String getCampaignStatus(int kno);

    String getLogin(String username, String password);

    /**
     * * Budget **
     */
    int getNuvaerendeBelob();

    int getPno(String username);

    Poe getPoe(int kno);

    int getStartsBelob();

    boolean isPartnerAccepted(int pno);

    boolean rollBackCampaign(int kno);

    boolean updateCampaign(int kno);

    /**
     * * POE **
     */
    boolean uploadPoe(int kno, ArrayList<CustomFile> files);
    
    /**
     * * STATS **
     */
    
    int countPartners();

    int countCampaigns();
    
    int countCountries();
    
    
}