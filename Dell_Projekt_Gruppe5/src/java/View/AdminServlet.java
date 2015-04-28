/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Control.AdminIF;
import Control.Controller;
import Entities.Campaign;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "AdminServlet", urlPatterns = {"/AdminServlet"})
public class AdminServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        AdminIF control = new Controller();
        HttpSession session = request.getSession();
        session.setAttribute("control", control);
        
        String errorMessage = "";
        int tableRowSelected = 0;
        String action = request.getParameter("action");

        switch (action) {

            case "dashboard": //(Tjek)
                request.getRequestDispatcher("dashboard_admin.jsp").forward(request, response);
                break;
            case "campaigns": //(Tjek)
                request.getRequestDispatcher("campaigns_admin.jsp").forward(request, response);
                break;
            case "partners":
                session.setAttribute("AllPartners", ((Controller)session.getAttribute("control")).getAllPartners());
                request.getRequestDispatcher("partners_admin.jsp").forward(request, response);
                break;
            case "acceptpartner": //(Tjek)
                tableRowSelected = Integer.parseInt(request.getParameter("id"));
                request.setAttribute("errorMessage", ((Controller)session.getAttribute("control")).acceptPartner(tableRowSelected - 1));
                session.setAttribute("pendingPartners", ((Controller)session.getAttribute("control")).getAllPendingPartners());
                request.getRequestDispatcher("dashboard_admin.jsp").forward(request, response);
                break;
            case "acceptcampaign": //(Tjek)
                tableRowSelected = Integer.parseInt(request.getParameter("id"));
                request.setAttribute("errorMessage", ((Controller)session.getAttribute("control")).acceptCampaign(tableRowSelected - 1));
                session.setAttribute("pendingCampaigns", ((Controller)session.getAttribute("control")).getAllPendingCampaigns());
                session.setAttribute("newestCampaigns", ((Controller)session.getAttribute("control")).getAllNewestCampaigns());
                request.getRequestDispatcher("dashboard_admin.jsp").forward(request, response);
                break;
            case "declinecampaign": //(Tjek)
                tableRowSelected = Integer.parseInt(request.getParameter("id"));
                request.setAttribute("errorMessage", ((Controller)session.getAttribute("control")).rollBackCampaign(tableRowSelected - 1));
                session.setAttribute("pendingCampaigns", ((Controller)session.getAttribute("control")).getAllPendingCampaigns());
                session.setAttribute("newestCampaigns", ((Controller)session.getAttribute("control")).getAllNewestCampaigns());
                request.getRequestDispatcher("dashboard_admin.jsp").forward(request, response);
                break;
            case "viewNewestCampaignDetail":  //(Tjek)
                tableRowSelected = Integer.parseInt(request.getParameter("id"));
                Campaign newCampaign = ((Controller)session.getAttribute("control")).getAllNewestCampaigns().get(tableRowSelected - 1);
                session.setAttribute("campaignDetail", newCampaign);
                session.setAttribute("poe", ((Controller)session.getAttribute("control")).getPoe(newCampaign.getKno()));
                request.getRequestDispatcher("detailCampaign_admin.jsp").forward(request, response);
                break;
            case "viewPendingCampaignsDetail": //(Tjek)
                tableRowSelected = Integer.parseInt(request.getParameter("id"));
                Campaign pendCampaign = ((Controller)session.getAttribute("control")).getAllPendingCampaigns().get(tableRowSelected - 1);
                session.setAttribute("campaignDetail", pendCampaign);
                session.setAttribute("poe", ((Controller)session.getAttribute("control")).getPoe(pendCampaign.getKno()));
                request.getRequestDispatcher("detailCampaign_admin.jsp").forward(request, response);
                break;
            case "viewAllCampaignsDetail": //(Tjek)
                tableRowSelected = Integer.parseInt(request.getParameter("id"));
                Campaign AllCampaign = ((Controller)session.getAttribute("control")).getAllNewestCampaigns().get(tableRowSelected - 1);
                session.setAttribute("campaignDetail", AllCampaign);
                session.setAttribute("poe", ((Controller)session.getAttribute("control")).getPoe(AllCampaign.getKno()));
                request.getRequestDispatcher("detailCampaign_admin.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
