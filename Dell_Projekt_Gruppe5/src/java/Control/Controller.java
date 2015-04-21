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
import Model.DBFacade;
import Model.CustomFile;

/**
 *
 * @author AndersBjergfelt
 */
public class Controller implements LoginIF, PartnerIF, AdminIF {

    DBFacade facade = DBFacade.getInstance();

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

    
/*
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
    */

    @Override
    public DBFacade getInstance() {
        DBFacade dbf = DBFacade.getInstance();
        return dbf;
    }
    
    /*** Dashboard view ***/
    
    @Override
    public ArrayList<Partner> getAllPendingPartners() {
        return facade.getAllPendingPartners();
    }
    
    @Override
    public ArrayList<Campaign> getAllPendingCampaigns() {  
        return facade.getAllPendingCampaigns();
    }
    
    @Override
    public ArrayList<Campaign> getAllNewestCampaigns() {
        return facade.getAllNewestCampaigns();
    }
    
    @Override
    public ArrayList<Budget> getAllPrices() {
        return facade.getAllPrices();
    }
    
    /*** Dashboard button interaction ***/

    @Override
    public boolean acceptPartner(int id) {
        return facade.acceptPartner(id);
    }
    
    @Override
    public boolean acceptCampaign(int id) {
        return facade.acceptCampaign(id);
    }

    @Override
    public boolean updateCampaign(int id) {
        boolean succes = true;
        int kno = getInstance().getAllNewestCampaigns().get(id).getKno();
        System.out.println("KNO: " + kno);
        if (getInstance().updateCampaign(kno)) {
            
        }else{
            succes = false;
        }
        
      return succes;
      
    }

    @Override
    public Campaign getCampaignDetail(int id) {
        return facade.getCampaignDetail(id);
    }
    
    public boolean rollBackCampaign(int id) {
        // CONTENT
        return false;
    }

    @Override
    public boolean uploadPoe(int kno, int id, ArrayList<CustomFile> files) {
        return facade.uploadPoe(kno, id,  files);
    }

    @Override
    public ArrayList<CustomFile> getFiles(int kno) {
        
        // content
        
        return new ArrayList<CustomFile>();
    }
    
    @Override
    public ArrayList<Campaign>getAllOwnPartnerCampaigns(int pno){
        return getInstance().getAllOwnPartnerCampaigns(pno);
    }

    @Override
    public int getKnoForCampaign(int id) {
        return facade.getKnoForCampaign(id);
    }

    @Override
    public boolean updateCampaignWithKno(int kno) {
        boolean success = false;
        if (getInstance().updateCampaign(kno)) {
            success = true;
        }        
      return success;
    }
    
    @Override
    public ArrayList<Partner> getPendingPartners() {
        return facade.getPendingPartners();
    }

    @Override
    public ArrayList<Campaign> getPendingCampaigns() {
        return facade.getPendingCampaigns();
    }

    @Override
    public ArrayList<Campaign> getNewestCampaigns() {
        return facade.getNewestCampaigns();
    }

    @Override
    public ArrayList<Campaign> getPartnersCampaigns() {
        return facade.getPartnersCampaigns();
    }

    

    
}
