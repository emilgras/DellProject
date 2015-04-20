/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.Campaign;
import Model.DBFacade;
import Model.CustomFile;
import Model.Partner;
import java.util.ArrayList;

/**
 *
 * @author EmilGras
 */
public interface AdminIF {
    
    DBFacade getInstance();
    

    
    ArrayList<Campaign> showAllNewCampaigns();

    ArrayList<Partner> getNewPartnerArrayList(int id);

    ArrayList<Campaign> getNewCampaignArrayList();
    
    ArrayList<Campaign> getAllPendingCampaigns();

    ArrayList<Campaign> getAllNewestCampaigns();

    ArrayList<Partner> getAllPendingPartners();
    
    boolean acceptCampaign(int id);
    
    boolean updateCampaign(int id);
    
    boolean acceptPartner(int id);
    
    boolean rollBackCampaign(int id);
    
    Campaign getCampaignDetail(int id);
    
    ArrayList<CustomFile> getFiles(int kno);
}
