/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Entities.Poe;
import Model.FacadeStub;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import static org.junit.Assert.*;

public class ControllerTest {
    
    /************** METODE: acceptPartner **************/
    @Test
    public void testAcceptPartnerReturnsEmptyErrorMessage() throws Exception {
        FacadeStub stub = new FacadeStub();        
        Controller controller = new Controller(stub);
        stub.acceptPartner = true;
        
        String message = controller.acceptPartner(1);
        
        assertThat(stub.acceptPartnerCount, is(1));
        
        assertThat(message, is(""));
    }
    
    @Test
    public void testAcceptPartnerReturnsErrorMessage() throws Exception {
        FacadeStub stub = new FacadeStub();        
        Controller control = new Controller(stub);
        stub.acceptPartner = false;
        
        String message = control.acceptPartner(1);
        
        assertThat(stub.acceptPartnerCount, is(1));
        
        assertThat(message, is("Something went wrong, could not accept partner right now. Please try again later"));
    }
    
    /************** METODE: getPoe **************/
    @Test
    public void testGetPoeIsNotNull() throws Exception {
        FacadeStub stub = new FacadeStub();
        Controller control = new Controller(stub);
        stub.status = "POE Pending";
        
        stub.poe = new Poe();
        
        Poe poe = control.getPoe(13);
        
        assertThat(stub.getPoeCount, is(1));
        
        assertNotNull(poe);
    }
    
    @Test
    public void testGetPoeIsNull() throws Exception {
        FacadeStub stub = new FacadeStub();
        Controller control = new Controller(stub);
        stub.status = "Pending";

        Poe poe = control.getPoe(13);
        
        assertThat(stub.getPoeCount, is(0));
        
        assertNull(poe);
    }
    
    /************** METODE: updateCampaignWithKno **************/
    @Test
    public void testUpdateCampaignWithKnoReturnsTrue() throws Exception {
        FacadeStub stub = new FacadeStub();
        Controller control = new Controller(stub);
        
        boolean update = control.updateCampaignWithKno(13);
        
        assertThat(stub.updateCampaignCount, is(1));
        
        assertThat(update, is(true));
    }
    
    @Test
    public void testUpdateCampaignWithKnoReturnsFalse() throws Exception {
        FacadeStub stub = new FacadeStub();
        Controller control = new Controller(stub);
       
        stub.updateCampaign = false;
        boolean update = control.updateCampaignWithKno(13);
        
        assertThat(stub.updateCampaignCount, is(1));
        
        assertThat(update, is(false));
    }
    
    /************** METODE: updateCampaign **************/
    @Test
    public void testUpdateCampaignReturnsTrue() throws Exception {
        FacadeStub stub = new FacadeStub();
        Controller control = new Controller(stub);
        
        boolean update = control.updateCampaign(2);
        
        assertThat(stub.updateCampaignCount, is(1));
        
        assertThat(update, is(true));
    }
    
    @Test
    public void testUpdateCampaignReturnsFalse() throws Exception {
        FacadeStub stub = new FacadeStub();
        Controller control = new Controller(stub);
        
        stub.updateCampaign = false;
        boolean update = control.updateCampaign(2);
        
        assertThat(stub.updateCampaignCount, is(1));
        
        assertThat(update, is(false));
    }
    
    /************** METODE: uploadPoe **************/
    @Test
    public void testUpdloadPoeNoErrorMessage() throws Exception {
        FacadeStub stub = new FacadeStub();
        Controller control = new Controller(stub);
        
        String message = control.uploadPoe(13, null);
        
        assertThat(stub.uploadPoeCount, is(1));
        assertThat(stub.updateCampaignCount, is(1));
        
        assertThat(message, is(""));
    }
    
    @Test
    public void testUpdloadPoeWithErrorMessage() throws Exception {
        FacadeStub stub = new FacadeStub();
        Controller control = new Controller(stub);
        
        stub.uploadPoe = false;
        String message = control.uploadPoe(13, null);
        
        assertThat(stub.uploadPoeCount, is(1));
        assertThat(stub.updateCampaignCount, is(0));
        
        assertThat(message, is("Not able to upload files at the moment. Please, try again."));
    }
    
    
    // createPartner
    
    // createCampaign
    
    // isPartnerAccepted
}
