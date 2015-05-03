/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.Budget;
import Entities.Campaign;
import Model.DBFacade;
import Entities.CustomFile;
import Entities.Partner;
import Entities.Poe;
import java.util.ArrayList;

/**
 *
 * @author EmilGras
 */
public interface AdminIF {

    ArrayList<Campaign> getAllPendingCampaigns();

    ArrayList<Campaign> getAllNewestCampaigns();

    ArrayList<Partner> getAllPendingPartners();
    
    ArrayList<Partner> getAllPartners();
    
    ArrayList<Budget> getAllPrices();
    
    Campaign getNewestCampaignDetail(int id);
    
    String acceptCampaign(int id);
    
    boolean updateCampaign(int id);
    
    String acceptPartner(int id);
    
    String deletePartner(int id);
    
    String deleteCampaign(int id);
    
    String rollBackCampaign(int id);
    
    Campaign getPendingCampaignDetail(int id);
    
    public Poe getPoe(int kno);
    
    public ArrayList<Campaign>getAllOwnPartnerCampaigns(int pno);
    
    public int getStartingFund();
    
    public int getCurrentFund();
    
    // Stats
    int countPartners();

    int countCampaigns();
    
    int countCountries();
    
    // New Quarter
    boolean newQuarter(int i);
    
}
