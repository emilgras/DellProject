/*
 * Frederik, Emil og Anders har arbejdet i denne klasse
 */
package Interfaces;

import Entities.Budget;
import Entities.Campaign;
import Entities.Partner;
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
    
    int getStartingFund();
    
    int getCurrentFund();
    
    String isPartnerAccepted(int pno);
    
    ArrayList<Partner> getAllPartners();
    
    // Stats
    int countPartners();

    int countCampaigns();
    
    int countCountries();
    
}
