/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.Budget;
import Model.Campaign;
import Model.Partner;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author EmilGras
 */
public interface LoginIF {
    
    ArrayList<Campaign> getAllPendingCampaigns();

    ArrayList<Campaign> getAllNewestCampaigns();

    ArrayList<Partner> getAllPendingPartners();
    
    ArrayList<Budget> getAllPrices();
    
    int getPno(String username);
    
    String getLogin(String username, String password);
    
    ArrayList<Campaign> getAllOwnPartnerCampaigns(int pno);
    
    int getStartsBelob();
    
    int getNuvaerendeBelob();
    
    String isPartnerAccepted(int pno);
    
    ArrayList<Partner> getAllPartners();
    
}
