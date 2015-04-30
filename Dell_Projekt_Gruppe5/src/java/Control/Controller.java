/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Interfaces.PartnerIF;
import Interfaces.LoginIF;
import Interfaces.AdminIF;
import Entities.Budget;
import Entities.Campaign;
import Entities.Partner;
import java.util.ArrayList;
import Model.DBFacade;
import Entities.CustomFile;
import Entities.Poe;
import Interfaces.Facade;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Controller implements LoginIF, PartnerIF, AdminIF {

    private Facade facade;

    public Controller() {
        facade = DBFacade.getDBFacadeInstance();
    }

    public Controller(Facade facade) {
        this.facade = facade;
    }

    
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
        if (!facade.acceptPartner(cvr)) {
            message = "Something went wrong, could not accept partner right now. Please try again later";
        }
        return message;
    }

    @Override
    public String deletePartner(int tableRowSelected) {
        String message = "";
        int pno = facade.getAllPendingPartners().get(tableRowSelected).getPno();
        System.out.println("pno: " + pno);
        if(!facade.deletePartner(pno)){
            message = "Something went wrong, could not decline partner right now. Please try agian later?";
        }
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
    public String rollBackCampaign(int tableRowSelected) { //(Tjek) 
        String message = "";
        int kno = facade.getAllPendingCampaigns().get(tableRowSelected).getKno();

        if (facade.deleteOldPoe(kno)) {           
            if (!facade.rollBackCampaign(kno)) {
                message = "Could not decline campaign at the moment du to a techical problems. Please, try again";
            }
        } else {
            message = "Could not decline campaign at the moment du to a techical problems. Please, try again";
        }
        return message;
    }

    /*
     *
     *  Used in Login, Partner and Admin Interface
     *    
     */
    public Poe getPoe(int kno) { //(Tjek)
        Poe poe = null;
        if (!facade.getCampaignStatus(kno).equals("Pending") && !facade.getCampaignStatus(kno).equals("In Progress")) {
            poe = facade.getPoe(kno);
        }
        return poe;
    }

    @Override
    public int getPno(String username) {
        return facade.getPno(username);
    }

    /*
     *
     *  Partner Interface
     *    
     */
    @Override
    public ArrayList<Campaign> getAllOwnPartnerCampaigns(int pno) {
        return facade.getAllOwnPartnerCampaigns(pno);
    }

    /*
     *
     *    Login Interface
     *
     */
    @Override
    public String getLogin(String username, String password) {
        return facade.getLogin(username, password);
    }

//////////// SLET FRA IF ////////////
    @Override
    public Campaign getPendingCampaignDetail(int tableRowSelected) {
        return facade.getAllPendingCampaigns().get(tableRowSelected);
    }

    @Override
    public Campaign getNewestCampaignDetail(int tableRowSelected) {
        return facade.getAllNewestCampaigns().get(tableRowSelected);
    }

    @Override
    public ArrayList<Budget> getAllPrices() {
        return facade.getAllPrices();
    }

    /**
     * ************* Campaign **************
     */
    
    @Override
    public boolean updateCampaignWithKno(int kno) {
        boolean success = false;
        if (facade.updateCampaign(kno)) {
            success = true;
        }
        return success;
    }

    /**
     * ************* Budget **************
     */
    @Override
    public int getStartsBelob() {
        return facade.getStartsBelob();
    }

    @Override
    public int getNuvaerendeBelob() {
        return facade.getNuvaerendeBelob();
    }

    /**
     * ************* Admin Dashboard button interaction **************
     */
    // Ændringer i Interface så den returnerer en streng
    // Hvilken metode skal den hente kno fra ? pending, newest eller... ? 
    @Override
    public boolean updateCampaign(int tableRowSelected) {
        int kno = facade.getAllNewestCampaigns().get(tableRowSelected).getKno();
        return facade.updateCampaign(kno);
    }
    
    @Override
    public String deleteCampaign(int tableRowSelected) {
        String message = "";
        int kno = facade.getAllPendingCampaigns().get(tableRowSelected).getKno();
        if(!facade.deleteCampaign(kno)) message = "The campaign has been succesfully deleted, and a notification has been sent to the partner with further details.";
        return message;
    }

    /**
     * **************** POE *******************
     */
    @Override
    public String uploadPoe(int kno, ArrayList<CustomFile> files) {
        String message = "";
        if (facade.uploadPoe(kno, files)) {
            // upload succesful
            facade.updateCampaign(kno);
        } else {
            message = "Not able to upload files at the moment. Please, try again.";
        }
        return message;
    }

    /**
     * **************** Partner *******************
     */
    @Override
    public String createPartner(Partner partner) {
        String message = "";
        if (!facade.createPartner(partner)) {
            message = "Due to technical problems, we cannot register you as a partner right now. Please, try again later";
        }
        return message;
    }

    @Override
    public String createCampaign(Campaign campaign) {
        String message = "";
        if (!facade.createCampaign(campaign)) {
            message = "Due to technical problems, we cannot create your campaign right now. Please try again later or contact our support for further help.";
        }
        return message;
    }

    /**
     * ************ Partner Dashboard *************
     */
    @Override
    public String isPartnerAccepted(int pno) {
        String message = "";
        if (!facade.isPartnerAccepted(pno)) {
            message = "You are not able to create campaigns before Dell accepts your partnership.";
        }
        return message;
    }

    @Override
    public ArrayList<Partner> getAllPartners() {
        return facade.getAllPartners();
    }
    
    /**
     * ************ STATS *************
     */
    
    @Override
    public int countPartners() {
        return facade.countPartners();
    }
    
    @Override
    public int countCampaigns() {
        return facade.countCampaigns();
    }
    
    @Override
    public int countCountries() {
        return facade.countCountries();
    }

}
