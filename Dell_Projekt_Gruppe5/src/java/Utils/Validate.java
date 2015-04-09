package Utils;

import Model.Partner;

public class Validate {
    
    public static String signupErrorMessage(Partner partner, String confirmPass) {
        
        String errorMessage = "";
        
        if (partner.getUsername().equals("")) errorMessage = "Please, remember to fill out the userfield";
        if (partner.getPassword().equals("")) errorMessage = "Please, remember to fill out the password field";
        if (confirmPass.equals("")) errorMessage = "Please, remember to fill out the confirm password field";
        if (partner.getName().equals("")) errorMessage = "Please, remember to fill out the company name field";
        if (partner.getCvr().equals("")) errorMessage = "Please, remember to fill out the cvr field";
        if (partner.getUsername().length() < 6) errorMessage = "Username must be more at least 6 characters";
        if (!partner.getPassword().equals(confirmPass)) errorMessage = "Confirm password does not match password";
        if (partner.getPassword().length() < 8) errorMessage = "Password must be at least 8 characters";
        if (partner.getCvr().length() != 8) errorMessage = "You must enter valid CVR number";
        
        return errorMessage;
        
    }
    
}
