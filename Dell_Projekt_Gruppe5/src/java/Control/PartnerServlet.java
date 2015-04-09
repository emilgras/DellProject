/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.AdminFacade;
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
        
        partnerFacade = new PartnerFacade();
        
        String action = request.getParameter("action");

        switch (action) {

            case "partnerLogin":
                // VALIDATE
                request.getRequestDispatcher("dashboard_partner.jsp").forward(request, response);
                break;
            case "partnerSignup":
                String user = request.getParameter("username");
                String password = request.getParameter("password");
                String confirmPassword = request.getParameter("confirmpassword");
                String company = request.getParameter("company");
                String cvr = request.getParameter("cvr");
                
                if (!Validate.signupErrorMessage(user, cvr, company, cvr, cvr).equals("")) {
                    // Fejl i signup formular
                    
                } else {
                    // Yes du kan oprettes
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
