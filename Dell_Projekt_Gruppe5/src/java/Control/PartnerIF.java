/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.Campaign;
import Model.DBFacade;
import Model.Partner;

/**
 *
 * @author EmilGras
 */
public interface PartnerIF {
    
    DBFacade getInstance();
    
    String getLogin(String username, String password);

    int getPno(String username);

    String createPartner(Partner partner);

    boolean createCampaign(Campaign campaign);
    
    boolean updateCampaign(int id);
}
