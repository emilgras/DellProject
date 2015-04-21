/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.Budget;
import Model.Campaign;
import Model.DBFacade;
import Model.Partner;
import java.util.ArrayList;

/**
 *
 * @author EmilGras
 */
public interface LoginIF {
    
    DBFacade getInstance();
    
    ArrayList<Campaign> getAllPendingCampaigns();

    ArrayList<Campaign> getAllNewestCampaigns();

    ArrayList<Partner> getAllPendingPartners();
    
    ArrayList<Budget> getAllPrices();
    
    int getPno(String username);
    
    String getLogin(String username, String password);
    
    public ArrayList<Campaign>getAllOwnPartnerCampaigns(int pno);
}
