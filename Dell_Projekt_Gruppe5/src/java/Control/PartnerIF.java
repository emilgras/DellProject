/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.Campaign;
import Model.CustomFile;
import Model.DBFacade;
import Model.Partner;
import Model.Poe;
import java.nio.file.Files;
import java.util.ArrayList;

/**
 *
 * @author EmilGras
 */
public interface PartnerIF {
    
    DBFacade getInstance();

    String createPartner(Partner partner);

    String createCampaign(Campaign campaign);
    
    boolean updateCampaign(int id);
    
    int getPno(String username);
    
    ArrayList<Campaign> getAllPendingCampaigns();
    
    ArrayList<Campaign> getAllNewestCampaigns();
    
    ArrayList<Partner> getAllPendingPartners();
    
    String uploadPoe(int kno, ArrayList<CustomFile> files);
    
    public Poe getPoe(int kno);
    
    ArrayList<Campaign>getAllOwnPartnerCampaigns(int pno);
    
    int getKnoForCampaign(int id);
    
    boolean updateCampaignWithKno(int kno);
 
    ArrayList getPendingCampaigns();
    
    ArrayList getPendingPartners();
    
    ArrayList getNewestCampaigns();
    
    ArrayList getPartnersCampaigns();
    
    String isPartnerAccepted(int pno);
        
}
