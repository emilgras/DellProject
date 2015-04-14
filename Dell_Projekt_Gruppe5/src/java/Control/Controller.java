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
    
    ArrayList <Partner> newPartnerArray = new ArrayList<>();
    ArrayList <Campaign> newCampaignArray = new ArrayList<>();
    
    @Override
    public String getLogin(String username, String password) {
     return   getInstance().getLogin(username, password);
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
    public ArrayList<Partner> showAllNewPartners() {
       newPartnerArray = getInstance().showPartnerName();
        return getInstance().showPartnerName();
    }

    @Override
    public boolean updatePartnerStatus(String cvr) {
        return getInstance().updatePartnerStatus(cvr);
    }

    @Override
    public ArrayList<Campaign> showAllNewCampaigns() {
        
        return getInstance().getAllCampaigns();
        
    }

    @Override
    public ArrayList<Campaign> getAllPendingCampaigns() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Partner> getNewPartnerArrayList(int id) {
       String partnerCVR = newPartnerArray.get(id).getCvr();
        getInstance().updatePartnerStatus(partnerCVR);
        System.out.println(newPartnerArray.get(id).getCvr());
        return newPartnerArray;
    }

    @Override
    public ArrayList<Campaign> getNewCampaignArrayList() {
        
        return newCampaignArray;
    }
    
    @Override 
    public DBFacade getInstance(){
        DBFacade dbf = DBFacade.getInstance();
        return dbf ;
    }
    
}
