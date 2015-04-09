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
     private PartnerMapper om; 
	  private Connection con;
	  private DBConnector dbcon;
	  //== Singleton start
	  private static PartnerFacade instance;
	 
	  private PartnerFacade() {
		  om 	= new PartnerMapper();
		  con 	= dbcon.getConnection();
	  }
          
	  public static PartnerFacade getInstance()
	  {
		if(instance == null)
		instance = new PartnerFacade();
		return instance;
	  }
	  //== Singleton end
          
          public String getLogin(String username, String password){
              return om.getLogin(username, password, con);
              
          }
          
          public String createPartner(Partner partner){
              return om.createPartner(partner, con);
          }
    
}
