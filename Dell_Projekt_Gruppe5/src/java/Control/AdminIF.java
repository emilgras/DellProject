/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.Budget;
import Model.Campaign;
import Model.DBFacade;
import Model.CustomFile;
import Model.Partner;
import Model.Poe;
import java.util.ArrayList;

/**
 *
 * @author EmilGras
 */
public interface AdminIF {
    
    DBFacade getInstance();   

    ArrayList<Campaign> getAllPendingCampaigns();

    ArrayList<Campaign> getAllNewestCampaigns();

    ArrayList<Partner> getAllPendingPartners();
    
    ArrayList<Budget> getAllPrices();
    
    Campaign getNewestCampaignDetail(int id);
    
    boolean acceptCampaign(int id);
    
    boolean updateCampaign(int id);
    
    boolean acceptPartner(int id);
    
    boolean rollBackCampaign(int id);
    
    Campaign getPendingCampaignDetail(int id);
    
    public Poe getPoe(int kno);
    
    public ArrayList<Campaign>getAllOwnPartnerCampaigns(int pno);
    
    boolean deleteOldPoe(int id);
    public int getStartsBelob();
    
    public int getNuvaerendeBelob();
    
}
