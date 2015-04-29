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
    
    /************** METODE: createPartner **************/
    @Test
    public void testCreatePartnerReturnsEmptyErrorMessage() throws Exception {
        FacadeStub stub = new FacadeStub();
        Controller control = new Controller(stub);
        
        String message = control.createPartner(null);
        
        assertThat(stub.createPartnerCount, is(1));
        
        assertThat(message, is(""));
    }
    
    @Test
    public void testCreatePartnerReturnsErrorMessage() throws Exception {
        FacadeStub stub = new FacadeStub();
        Controller control = new Controller(stub);
        
        stub.createPartner = false;
        String message = control.createPartner(null);
        
        assertThat(stub.createPartnerCount, is(1));
        
        assertThat(message, is("Due to technical problems, we cannot register you as a partner right now. Please, try again later"));
    }
    
    /************** METODE: createCampaign **************/
    @Test
    public void testCreateCampaignReturnsEmptyErrorMessage() throws Exception {
        FacadeStub stub = new FacadeStub();
        Controller control = new Controller(stub);
        
        String message = control.createCampaign(null);
        
        assertThat(stub.createCampaignCount, is(1));
        
        assertThat(message, is(""));
    }
    
    @Test
    public void testCreateCampaignReturnsErrorMessage() throws Exception {
        FacadeStub stub = new FacadeStub();
        Controller control = new Controller(stub);
        
        stub.createCampaign = false;
        String message = control.createCampaign(null);
        
        assertThat(stub.createCampaignCount, is(1));
        
        assertThat(message, is("Due to technical problems, we cannot create your campaign right now. Please try again later or contact our support for further help."));
    }
    

    @Test
    public void isPartnerAcceptedReturnsErrorMessage() throws Exception{
        FacadeStub stub = new FacadeStub();
        Controller control = new Controller(stub);
        stub.accepted = true;
        String message = control.isPartnerAccepted(3);
        
        assertThat(stub.isPartnerAcceptedCount, is(1));
        
        assertThat(message,is(""));      
    }
    
    @Test
    public void isPartnerAcceptedReturnsEmptyErrorMessage() throws Exception{
        FacadeStub stub = new FacadeStub();
        Controller control = new Controller(stub);
        stub.accepted = false;
        String message = control.isPartnerAccepted(3);
        
        assertThat(stub.isPartnerAcceptedCount, is(1));
        
        assertThat(message,is("You are not able to create campaigns before Dell accepts your partnership."));
    }
}
