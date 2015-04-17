/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Control.AdminIF;
import Control.Controller;
import Model.Campaign;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "AdminServlet", urlPatterns = {"/AdminServlet"})
public class AdminServlet extends HttpServlet {

    //DBFacade partnerFacade = DBFacade.getInstance();
    AdminIF control = new Controller();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        String action = request.getParameter("action");

        switch (action) {

            case "dashboard":
                request.getRequestDispatcher("dashboard_admin.jsp").forward(request, response);
                break;
            case "campaigns":
                request.getRequestDispatcher("campaigns_admin.jsp").forward(request, response);
                break;
            case "partners":
                request.getRequestDispatcher("partners_admin.jsp").forward(request, response);
                break;
            case "acceptpartner":
                String stringId = request.getParameter("id");
                int intId = Integer.parseInt(stringId);
                control.acceptPartner(intId - 1);
                request.getRequestDispatcher("dashboard_admin.jsp").forward(request, response);
                break;
            case "acceptcampaign":
                String cId = request.getParameter("id");
                int intcId = Integer.parseInt(cId);
                control.acceptCampaign(intcId - 1);

                //getNewestPartners
                //session.setAttribute("newestCampaigns", control.getAllNewestCampaigns());
                
                request.getRequestDispatcher("dashboard_admin.jsp").forward(request, response);
                break;
            case "viewCampaign":
                String vId = request.getParameter("id");
                int intvId = Integer.parseInt(vId);
                Campaign campaign = control.getCampaignDetail(intvId - 1);
                
                session.setAttribute("campaignDetail", campaign);
                
                request.getRequestDispatcher("detailCampaign_admin.jsp").forward(request, response);
                break;
            case "logout":
                // currentUser = null;
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        HttpSession session = request.getSession();

       switch (action) {

            case "adminLogin":
                // VALIDATION

                //getPendingPartners
                session.setAttribute("pendingPartners", control.getAllPendingPartners());

                //getPendingCampaigns
                session.setAttribute("pendingCampaigns", control.getAllPendingCampaigns());

                //getNewestPartners
                //session.setAttribute("newestCampaigns", control.);

                // Sender brugeren videre til dashboard
                request.getRequestDispatcher("dashboard_admin.jsp").forward(request, response);

                break;
        }

    }

}
