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
import java.nio.file.Files;
import java.util.ArrayList;

/**
 *
 * @author EmilGras
 */
public interface PartnerIF {
    
    DBFacade getInstance();

    String createPartner(Partner partner);

    boolean createCampaign(Campaign campaign);
    
    boolean updateCampaign(int id);
    
    int getPno(String username);
    
    ArrayList<Campaign> getAllPendingCampaigns();
    
    ArrayList<Campaign> getAllNewestCampaigns();
    
    void uploadPoe(int kno, ArrayList<CustomFile> files);
}
