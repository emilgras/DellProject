/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.Campaign;
import Model.Partner;
import java.util.ArrayList;
import Model.DBFacade;

/**
 *
 * @author AndersBjergfelt
 */
public class Controller implements ControlInterface {

    DBFacade facade = DBFacade.getInstance();
    
    ArrayList<Partner> pendingPartners = new ArrayList<>();
    ArrayList<Campaign> pendingCampaigns = new ArrayList<>();
    ArrayList<Campaign> newestCampaigns = new ArrayList<>();
    ArrayList<Partner> allPartners = new ArrayList<>();

    @Override
    public String getLogin(String username, String password) {
        return getInstance().getLogin(username, password);
    }

    @Override
    public int getPno(String username) {
        return getInstance().getPno(username);
    }

    @Override
    public String createPartner(Partner partner) {
        return getInstance().createPartner(partner);
    }

    @Override
    public boolean createCampaign(Campaign campaign) {
        return getInstance().createCampaign(campaign);
    }

    

    

    @Override
    public ArrayList<Campaign> showAllNewCampaigns() {

        return getInstance().getAllNewestCampaigns();

    }

    

    @Override
    public ArrayList<Partner> getNewPartnerArrayList(int id) {
        String partnerCVR = pendingPartners.get(id).getCvr();
        //getInstance().updatePartnerStatus(partnerCVR);
        System.out.println(pendingPartners.get(id).getCvr());
        return pendingPartners;
    }

    @Override
    public ArrayList<Campaign> getNewCampaignArrayList() {

        return pendingCampaigns;
    }

    @Override
    public DBFacade getInstance() {
        DBFacade dbf = DBFacade.getInstance();
        return dbf;
    }
    
    /*** Dashboard view ***/
    
    @Override
    public ArrayList<Partner> getAllPendingPartners() {
        pendingPartners = getInstance().getAllPendingPartners();
        return pendingPartners;
    }
    
    @Override
    public ArrayList<Campaign> getAllPendingCampaigns() {
        pendingCampaigns = getInstance().getAllPendingCampaigns();
        return pendingCampaigns;
    }
    
    @Override
    public ArrayList<Campaign> getAllNewestCampaigns() {
        newestCampaigns = getInstance().getAllNewestCampaigns();
        return newestCampaigns;
    }
    
    /*** Dashboard button interaction ***/

    @Override
    public boolean acceptPartner(int id) {
        boolean success = true;
        String cvr = pendingPartners.get(id).getCvr();
        if (getInstance().acceptPartner(cvr)) {
            pendingPartners.remove(id);
        } else {
            success = false;
        }
        return success;
    }
    
    @Override
    public boolean acceptCampaign(int id) {
        boolean success = true;
        int kno = pendingCampaigns.get(id).getKno();
        System.out.println("KNO: " + kno);
        if (getInstance().acceptCampaign(kno)) {
            System.out.println("SUCCESS...");
            pendingCampaigns.remove(id);
            updateCampaign(kno);
        } else {
            success = false;
        }
        return success;
    }

    @Override
    public boolean updateCampaign(int id) {
        boolean succes = true;
        int kno = pendingCampaigns.get(id).getKno();
        System.out.println("KNO: " + kno);
        if (getInstance().updateCampaign(kno)) {
            System.out.println("SUCCES...");
            
        }else{
            succes = false;
        }
        
      return succes;
      
    }

    @Override
    public Campaign getCampaignDetail(int id) {
        return newestCampaigns.get(id);
    }
    
    

    
}
