package Interfaces;

import Entities.Budget;
import Entities.Campaign;
import Entities.CustomFile;
import Entities.Partner;
import Entities.Poe;
import java.util.ArrayList;

/*** @author EmilGras ***/

public interface FacadeIF {

    boolean acceptCampaign(int kno);

    boolean acceptPartner(String cvr);
    
    boolean deletePartner(int pno);

    boolean createCampaign(Campaign campaign);

    boolean createPartner(Partner partner);

    boolean deleteOldPoe(int kno);

    ArrayList<Campaign> getAllNewestCampaigns();

    ArrayList<Campaign> getAllOwnPartnerCampaigns(int pno);

    ArrayList<Partner> getAllPartners();

    ArrayList<Campaign> getAllPendingCampaigns();
    
    boolean deleteCampaign(int kno);

    ArrayList<Partner> getAllPendingPartners();

    ArrayList<Budget> getAllPrices();

    String getCampaignStatus(int kno);

    String getLogin(String username, String password);

    int getPno(String username);

    Poe getPoe(int kno);
    
    boolean updateMoneyUsed(int i);
    
    boolean isPartnerAccepted(int pno);

    boolean rollBackCampaign(int kno);

    boolean updateCampaign(int kno);

    boolean uploadPoe(int kno, ArrayList<CustomFile> files);

    int countPartners();

    int countCampaigns();
    
    int countCountries();
    
    int getStartingFund();
    
    int getCurrentFund();
    
    boolean newQuarterBudget(int i);
    
    void newQuarterCampaign();
    
    void newQuarterPoe();
    
}
