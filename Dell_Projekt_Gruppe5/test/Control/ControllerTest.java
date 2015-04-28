/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.FacadeStub;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author EmilGras
 */
public class ControllerTest {
    
    @Test
    public void testAcceptPartner() throws Exception {
        Controller controller = new Controller(new FacadeStub());
        
        String message = controller.acceptPartner(1);
        
        assertThat(message, is(""));
    }
}
