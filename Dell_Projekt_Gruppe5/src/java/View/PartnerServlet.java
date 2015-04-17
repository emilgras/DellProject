/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Control.Controller;
import Control.PartnerIF;
import Model.Campaign;
import Model.Partner;
import Utils.Validate;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "PartnerServlet", urlPatterns = {"/PartnerServlet"})
public class PartnerServlet extends HttpServlet {

    PartnerIF con = new Controller();
    int currentPno = 0;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        HttpSession session = request.getSession();
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
                
            case "signup":
                request.setAttribute("username", "");
                request.setAttribute("company", "");
                request.setAttribute("cvr", "");
                request.setAttribute("country", "");
                request.setAttribute("signupErrorMessage", "");
                request.setAttribute("dbErrorMessage", "");
                request.getRequestDispatcher("signup_partner.jsp").forward(request, response);
                break;
            case "logout":
                //currentUser = null;
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;

            case "upload":
                String stringId = request.getParameter("id");
                int intId = Integer.parseInt(stringId);
                con.updateCampaign(intId - 1);
                session.setAttribute("newestCampaigns", con.getAllNewestCampaigns());
                request.getRequestDispatcher("dashboard_partner.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String validationErrorMessage;
        String dbErrorMessage;
        HttpSession session = request.getSession();

        switch (action) {

            
            case "partnerSignup":
                String user = request.getParameter("username");
                String pass = request.getParameter("password");
                String confirmPass = request.getParameter("confirmpassword");
                String name = request.getParameter("company");
                String cvr = request.getParameter("cvr");
                String country = request.getParameter("country");

                Partner partner = new Partner(user, pass, name, cvr, null, country);

                request.setAttribute("username", user);
                request.setAttribute("company", name);
                request.setAttribute("cvr", cvr);
                request.setAttribute("country", country);

                if (!(validationErrorMessage = Validate.signupErrorMessage(partner, confirmPass)).equals("")) {
                    // Fejl i signup formular
                    request.setAttribute("signupErrorMessage", validationErrorMessage);
                    request.getRequestDispatcher("signup_partner.jsp").forward(request, response);
                } else {

                    if (!(dbErrorMessage = con.createPartner(partner)).equals("")) {
                        // Kunne ikke oprettes i DB
                        request.setAttribute("signupErrorMessage", dbErrorMessage);
                        request.getRequestDispatcher("signup_partner.jsp").forward(request, response);
                    } else {
                        // Yay - du er oprettet i DB
                        currentPno = con.getPno(user);
                        System.out.println("PNO: " + currentPno);
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

                Campaign campaign = new Campaign(campaignStart, campaignEnd, price, description, currentPno);
                campaign.setPno(currentPno);

                request.setAttribute("campaignstart", campaignStart);
                request.setAttribute("campaignend", campaignEnd);
                request.setAttribute("price", price);
                request.setAttribute("description", description);

                if (!(validationErrorMessage = Validate.campaignErrorMessage(campaign)).equals("")) {
                    // Fejl i login form
                    System.out.println("Message: " + validationErrorMessage);
                    request.setAttribute("campaignErrorMessage", validationErrorMessage);
                    request.getRequestDispatcher("newcampaign.jsp").forward(request, response);
                } else {

                    if (!con.createCampaign(campaign)) {
                        // Kunne ikke oprette kampagne i database
                        dbErrorMessage = "Due to technical problems, we cannot create your campaign right now. Please try again later or contact our support for further help.";
                        request.setAttribute("campaignErrorMessage", dbErrorMessage);
                        request.getRequestDispatcher("newcampaign_partner.jsp").forward(request, response);
                    } else {
                        System.out.println("TEEST PNO: " + campaign.getPno());
                        System.out.println("TEEST price: " + campaign.getPris());

                        // Success!
                        session.setAttribute("pendingCampaigns", con.getAllPendingCampaigns());
                        request.getRequestDispatcher("dashboard_partner.jsp").forward(request, response);
                        
                    }
                }
                break;
        }
    }
}
