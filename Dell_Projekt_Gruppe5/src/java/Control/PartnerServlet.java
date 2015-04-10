/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.Partner;
import Model.PartnerFacade;
import Utils.Validate;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "PartnerServlet", urlPatterns = {"/PartnerServlet"})
public class PartnerServlet extends HttpServlet {

    PartnerFacade partnerFacade = PartnerFacade.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        switch (action) {

            case "dashboard":
                request.getRequestDispatcher("dashboard_partner.jsp").forward(request, response);
                break;
            case "newcampaign":
                request.setAttribute("campaignstart", "");
                request.setAttribute("campaignend", "");
                request.setAttribute("price", "");
                request.setAttribute("description", "");
                request.setAttribute("validationErrorMessage", "");
                request.setAttribute("dbErrorMessage", "");
                request.getRequestDispatcher("newcampaign_partner.jsp").forward(request, response);
                break;
            case "index":
                request.setAttribute("username", "");
                request.setAttribute("signupErrorMessage", "");
                request.setAttribute("dbErrorMessage", "");
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

                if (!(validationErrorMessage = Validate.loginErrorMessage(username, password)).equals("")) {
                    // Fejl i login form
                    request.setAttribute("loginErrorMessage", validationErrorMessage);
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                } else {

                    // Succes ved login form
                    if (!(dbErrorMessage = partnerFacade.getLogin(username, password)).equals("")) {
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
                System.out.println("JATAK");
                
                String user = request.getParameter("username");
                String pass = request.getParameter("password");
                String confirmPass = request.getParameter("confirmpassword");
                String name = request.getParameter("company");
                String cvr = request.getParameter("cvr");

                Partner partner = new Partner(user, pass, name, cvr, null);

                request.setAttribute("username", user);
                request.setAttribute("company", name);
                request.setAttribute("cvr", cvr);

                if (!(validationErrorMessage = Validate.signupErrorMessage(partner, confirmPass)).equals("")) {
                    // Fejl i signup formular
                    request.setAttribute("signupErrorMessage", validationErrorMessage);
                    request.getRequestDispatcher("signup_partner.jsp").forward(request, response);
                } else {

                    if (!(dbErrorMessage = partnerFacade.createPartner(partner)).equals("")) {
                        // Kunne ikke oprettes i DB
                        request.setAttribute("signupErrorMessage", dbErrorMessage);
                        request.getRequestDispatcher("signup_partner.jsp").forward(request, response);
                    } else {
                        // Yay - du er oprettet i DB
                        request.getRequestDispatcher("dashboard_partner.jsp").forward(request, response);
                    }
                }
                break;

            case "sendcampaign":
                String campaignStart = request.getParameter("campaignstart");   /// Lav streng om til sql date
                String campaignEnd = request.getParameter("campaignend");       /// Lav streng om til sql date
                String priceString = request.getParameter("price");
                float price = Float.parseFloat(priceString);
                String description = request.getParameter("description");

                // Campaign campaign = new Campaign(description, ".....II.....", null, campaignStart, campaignEnd, price);
                request.setAttribute("campaignstart", campaignStart);
                request.setAttribute("campaignend", campaignEnd);
                request.setAttribute("price", price);
                request.setAttribute("description", description);

                /*if ((validationErrorMessage = Validate.campaignErrorMessage(campaignStart, campaignEnd, price, description)).equals("")) {
                    // Fejl i login form
                    request.setAttribute("campaignErrorMessage", validationErrorMessage);
                    request.getRequestDispatcher("newcampaign.jsp").forward(request, response);
                } else {
                    if (!partnerFacade.createCampaign(campaign)) {
                        // Kunne ikke oprette kampagne i database
                        dbErrorMessage = "Due to technical problems, we cannot create your campaign right now. Please try again later or contact our support for further help.";
                        request.setAttribute("campaignErrorMessage", dbErrorMessage);
                        request.getRequestDispatcher("newcampaign_partner.jsp").forward(request, response);
                    } else {
                        // Success!
                        request.getRequestDispatcher("dashboard_partner.jsp").forward(request, response);
                    }
                }*/
                break;
        }
    }
}
