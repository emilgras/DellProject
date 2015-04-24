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
import Model.Poe;

public class Controller implements LoginIF, PartnerIF, AdminIF {
 
    DBFacade facade = DBFacade.getInstance();
       
    /*
     *
     *  Admin Interface
     * 
     */
    
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
    public String acceptPartner(int tableRowSelected) { //(Tjek)
        String message = "";
        String cvr = facade.getAllPendingPartners().get(tableRowSelected).getCvr();
        if (!facade.acceptPartner(cvr)) message = "Something went wrong, could not accept partner right now. Please try again later";
        return message;
    }
    
    @Override
    public String acceptCampaign(int tableRowSelected) { //(Tjek)
        String message = "";
        int kno = facade.getAllPendingCampaigns().get(tableRowSelected).getKno();   
        if (facade.getCampaignStatus(kno).equals("Pending")) {
            // Her accepteres kampagnen ved at oprettelsesdato initialisere
            // og kampagne status opdateres til 'In-Progess'.
            if (!facade.acceptCampaign(kno)) {
                message = "Something went wrong, could not accept partner right now. Please try again later";       
            }
            if (!facade.updateCampaign(kno)) {
                message = "Something went wrong, could not accept partner right now. Please try again later";
            }

        } else {
            // Her opdateres kampagnens status til 'In-Progess'.
            if (!facade.updateCampaign(kno)) {
                message = "Something went wrong, could not accept partner right now. Please try again later";
            }
        }
        return message;
    }
    
     @Override
    public String deleteOldPoe(int tableRowSelected) { //(Tjek)
        String message = "";
        int kno = facade.getAllPendingCampaigns().get(tableRowSelected).getKno();  
        if (!facade.deleteOldPoe(kno)) message = "Could not decline cmapaign at the moment du to a techical problem. Please, try again";
        return message;
    }

    @Override
    public String rollBackCampaign(int tableRowSelected) { //(Tjek) 
        String message = "";
        int kno = facade.getAllPendingCampaigns().get(tableRowSelected).getKno();  
        if (!facade.rollBackCampaign(kno)) message = "Could not decline campaign at the moment du to a techical problem. Please, try again";
        return message;
    }
    
    public Poe getPoe(int kno) { //(Tjek)
        Poe poe = null;
        if (!facade.getCampaignStatus(kno).equals("Pending") || !facade.getCampaignStatus(kno).equals("In Progress")) poe = facade.getPoe(kno);
        return poe;
    }
    
    /*
     *
     *  Partner Interface
     *    
     */
    
    @Override
    public ArrayList<Campaign>getAllOwnPartnerCampaigns(int pno){
        return getInstance().getAllOwnPartnerCampaigns(pno);
    }
    
    /*
     *
     *    Login Interface
     *
     */
    
    @Override
    public String getLogin(String username, String password) {
        return getInstance().getLogin(username, password);
    }

    @Override
    public int getPno(String username) {     
        return getInstance().getPno(username);
    }


    @Override
    public DBFacade getInstance() {
        DBFacade dbf = DBFacade.getInstance();
        return dbf;
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
    
    @Override
    public Campaign getPendingCampaignDetail(int id) {
        return facade.getPendingCampaignDetail(id);
    }
    
    @Override
    public Campaign getNewestCampaignDetail(int id) {
        return facade.getNewestCampaignDetail(id);
    }
    
    @Override
    public ArrayList<Budget> getAllPrices() {
        return facade.getAllPrices();
    }
    
    
    /*************** Campaign ***************/
    
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
    
    /*************** Budget ***************/
    

    @Override
    public int getStartsBelob() {
        return facade.getStartsBelob();
    }

    @Override
    public int getNuvaerendeBelob() {
        return facade.getNuvaerendeBelob();
    }
    
    /*************** Admin Dashboard button interaction ***************/
    
    
    
    // Ændringer i Interface så den returnerer en streng
    // Hvilken metode skal den hente kno fra ? pending, newest eller... ? 
    @Override
    public boolean updateCampaign(int tableRowSelected) {
        boolean succes = true;
        int kno = facade.getAllNewestCampaigns().get(tableRowSelected).getKno();
        System.out.println("KNO: " + kno);
        if (getInstance().updateCampaign(kno)) {
            
        }else{
            succes = false;
        }
        
      return succes;
      
    }

    
   
    
    
    
    /****************** POE ********************/

    @Override
    public boolean uploadPoe(int kno, int id, ArrayList<CustomFile> files) {
        return facade.uploadPoe(kno, id,  files);
    }
    
    
    
    
    /****************** Partner ********************/
    
    @Override
    public String createPartner(Partner partner) {
        return getInstance().createPartner(partner);
    }

    @Override
    public boolean createCampaign(Campaign campaign) {
        return getInstance().createCampaign(campaign);
    }
    
    
    /************** Partner Dashboard **************/
    
    

    @Override
    public String isPartnerAccepted(int pno) {
        String message = "";
        if (!facade.isPartnerAccepted(pno)) message = "You are not able to create campaigns before Dell accepts your partnership.";
        System.out.println("MESSAGE: " + message);
        return message;
    }
    
}
