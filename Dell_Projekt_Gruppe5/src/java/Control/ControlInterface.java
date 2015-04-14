/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.Campaign;
import Model.DBFacade;
import Model.Partner;
import java.util.ArrayList;

/**
 *
 * @author AndersBjergfelt
 */
public interface ControlInterface {
//PARTER STUUF
    String getLogin(String username, String password);

    int getPno(String username);

    String createPartner(Partner partner);
        
    boolean createCampaign(Campaign campaign);

    ArrayList<Partner> showAllNewPartners();
       
    boolean updatePartnerStatus(String cvr);
    
    

// ADMIN STUUF
    ArrayList<Campaign> showAllNewCampaigns();
    
    ArrayList<Campaign> getAllPendingCampaigns();
    
    ArrayList<Partner> getNewPartnerArrayList(int id);
        
    ArrayList<Campaign> getNewCampaignArrayList();
    
    DBFacade getInstance();
    
    
}
