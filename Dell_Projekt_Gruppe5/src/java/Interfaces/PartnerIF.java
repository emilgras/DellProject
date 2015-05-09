/*
 * Frederik, Emil og Anders har arbejdet i denne klasse
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
    
    String uploadPoe(int kno, ArrayList<CustomFile> files);
    
    Poe getPoe(int kno);
    
    ArrayList<Campaign>getAllOwnPartnerCampaigns(int pno);
    
    boolean updateCampaignWithKno(int kno);
    
    String isPartnerAccepted(int pno);
        
}
