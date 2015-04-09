package Utils;

public class Validate {
    
    public static String signupErrorMessage(String user, String pass, String confPass, String name, String cvr) {
        
        String errorMessage = "";
        
        if (user.equals("")) errorMessage = "Please, remember to fill out the userfield";
        if (pass.equals("")) errorMessage = "Please, remember to fill out the password field";
        if (confPass.equals("")) errorMessage = "Please, remember to fill out the confirm password field";
        if (name.equals("")) errorMessage = "Please, remember to fill out the company name field";
        if (cvr.equals("")) errorMessage = "Please, remember to fill out the cvr field";
        if (user.length() < 6) errorMessage = "Username must be more at least 6 characters";
        if (!pass.equals(confPass)) errorMessage = "Confirm password does not match password";
        if (cvr.length() != 8) errorMessage = "You must enter valid CVR number";
        
        return errorMessage;
        
    }
    
}
