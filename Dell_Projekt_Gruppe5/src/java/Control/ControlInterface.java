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

public interface ControlInterface {

    /**
     * * Dashboard **
     */
    ArrayList<Campaign> getAllPendingCampaigns();

    ArrayList<Campaign> getAllNewestCampaigns();

    ArrayList<Partner> getAllPendingPartners();

    //PARTER STUUF
    String getLogin(String username, String password);

    int getPno(String username);

    String createPartner(Partner partner);

    boolean createCampaign(Campaign campaign);
     public ArrayList<Campaign>getAllOwnPartnerCampaigns(int pno);
      
    

    // ADMIN STUUF
    ArrayList<Campaign> showAllNewCampaigns();

    ArrayList<Partner> getNewPartnerArrayList(int id);

    ArrayList<Campaign> getNewCampaignArrayList();

    DBFacade getInstance();
    
    
    
    /*** Dashboard interaction ***/
    
    boolean acceptCampaign(int id);
    
    boolean updateCampaign(int id);
    
    boolean acceptPartner(int id);
    
    Campaign getCampaignDetail(int id);

}
