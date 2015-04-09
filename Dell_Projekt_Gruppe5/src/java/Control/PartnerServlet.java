/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.AdminFacade;
import Model.Partner;
import Model.PartnerFacade;
import Utils.Validate;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static jdk.nashorn.internal.runtime.regexp.RegExpFactory.validate;

@WebServlet(name = "PartnerServlet", urlPatterns = {"/PartnerServlet"})
public class PartnerServlet extends HttpServlet {

    PartnerFacade partnerFacade;
    AdminFacade adminFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        switch (action) {

            case "dashboard":
                request.getRequestDispatcher("dashboard_partner.jsp").forward(request, response);
                break;
            case "newcampaign":
                request.getRequestDispatcher("newcampaign_partner.jsp").forward(request, response);
                break;
            case "index":
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;
            case "signup":
                request.setAttribute("username", "");
                request.setAttribute("company", "");
                request.setAttribute("cvr", "");
                request.setAttribute("signupErrorMessage", "");
                request.setAttribute("dbErrorMessage", "");
                request.getRequestDispatcher("signup_partner.jsp").forward(request, response);
                break;
            case "logout":
                //currentUser = null;
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String validationErrorMessage;
        String dbErrorMessage;
        
        switch (action) {

            case "partnerLogin":
                String username = request.getParameter("username");
                String password = request.getParameter("password");

                request.setAttribute("username", username);

                validationErrorMessage = Validate.loginErrorMessage(username, password);
                
                if (!validationErrorMessage.equals("")) {
                    // Fejl i login form
                    request.setAttribute("loginErrorMessage", validationErrorMessage);
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                } else {
                    
                    dbErrorMessage = partnerFacade.getLogin(username, password);
                    
                    // Succes ved login form
                    if (!dbErrorMessage.equals("")) {
                        // Fejl ved oprettelse i DB
                        request.setAttribute("loginErrorMessage", dbErrorMessage);
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    } else {
                        // Oprettelse lykkedes
                        request.getRequestDispatcher("dashboard_partner.jsp").forward(request, response);
                    }
                }
                
                request.getRequestDispatcher("dashboard_partner.jsp").forward(request, response);
                break;
            case "partnerSignup":
                String user = request.getParameter("username");
                String pass = request.getParameter("password");
                String confirmPass = request.getParameter("confirmpassword");
                String name = request.getParameter("company");
                String cvr = request.getParameter("cvr");

                Partner partner = new Partner(11, user, pass, name, cvr, null);

                request.setAttribute("username", user);
                request.setAttribute("company", name);
                request.setAttribute("cvr", cvr);

                // Tjekker om brugeren har udftldt formularen korrekt
                validationErrorMessage = Validate.signupErrorMessage(partner, confirmPass);
                
                if (!validationErrorMessage.equals("")) {
                    // Fejl i signup formular
                    validationErrorMessage = "";
                    request.setAttribute("signupErrorMessage", validationErrorMessage);
                    request.getRequestDispatcher("signup_partner.jsp").forward(request, response);
                } else {
                    
                    // Tjekker om partneren kan oprettes i databasen
                    dbErrorMessage = partnerFacade.createPartner(partner);
                    
                    if (!dbErrorMessage.equals("")) {
                        // Kunne ikke oprettes i DB
                        request.setAttribute("signupErrorMessage", dbErrorMessage);
                        request.getRequestDispatcher("signup_partner.jsp").forward(request, response);
                    } else {
                        // Yay - du er oprettet i DB
                        request.getRequestDispatcher("dashboard_partner.jsp").forward(request, response);
                    }
                }

                // VALIDATE
                // Forward to partner dashboard
                break;
            case "sendcampaign":
                // Forward to partner dashboard
                break;
        }
    }
}
