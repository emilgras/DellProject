/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;

/**
 *
 * @author ABjergfelt
 */
public class PartnerFacade {

    private PartnerMapper pm;
    private CampaignMapper cm;
    private Connection con;
    private DBConnector dbcon;
    //== Singleton start
    private static PartnerFacade instance;

    private PartnerFacade() {
        pm = new PartnerMapper();
        con = dbcon.getConnection();
    }

    public static PartnerFacade getInstance() {
        if (instance == null) {
            instance = new PartnerFacade();
        }
        return instance;
    }
	  //== Singleton end

    public String getLogin(String username, String password) {
        return pm.getLogin(username, password, con);
    }

    public String createPartner(Partner partner) {
        return pm.createPartner(partner, con);
    }

    public String createCampaign(Campaign campaign) {
        /* return cm.insertCampaign(campaign, con);  Hvorfor skal den tage imod en liste og ikke bare et Campaign object? */
        return "";
    }

}
