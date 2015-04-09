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
                String pass = request.getParameter("password");
                String confirmPass = request.getParameter("confirmpassword");
                String name = request.getParameter("company");
                String cvr = request.getParameter("cvr");
                
                Partner partner = new Partner(user, pass, name, cvr, null);
                String errorMessage = Validate.signupErrorMessage(partner, confirmPass);
                
                if (!errorMessage.equals("")) {
                    // Fejl i signup formular
                    request.setAttribute("signupErrorMessage", errorMessage);
                    request.getRequestDispatcher("signup_partner.jsp").forward(request, response);
                } else {
                    // Yes du kan oprettes
                    request.getRequestDispatcher("dashboard_partner.jsp").forward(request, response);
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
