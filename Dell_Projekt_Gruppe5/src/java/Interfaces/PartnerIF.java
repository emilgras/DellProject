/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.Campaign;
import Entities.CustomFile;
import Model.DBFacade;
import Entities.Partner;
import Entities.Poe;
import java.nio.file.Files;
import java.util.ArrayList;

/**
 *
 * @author EmilGras
 */
public interface PartnerIF {

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
    
    boolean updateCampaignWithKno(int kno);
    
    String isPartnerAccepted(int pno);
        
}
