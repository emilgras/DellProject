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

    HttpSession session;
    //DBFacade partnerFacade = DBFacade.getInstance();
    AdminIF control = new Controller();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        session = request.getSession();

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
                updateSessions();
                request.getRequestDispatcher("dashboard_admin.jsp").forward(request, response);
                break;
            case "acceptcampaign":
                String cId = request.getParameter("id");
                int intcId = Integer.parseInt(cId) - 1;
                control.acceptCampaign(intcId);
                updateSessions();
                request.getRequestDispatcher("dashboard_admin.jsp").forward(request, response);
                break;
            case "declinecampaign":
                String dId = request.getParameter("id");
                int intdId = Integer.parseInt(dId) - 1;
                control.deleteOldPoe(intdId);
                control.rollBackCampaign(intdId);
                updateSessions();
                request.getRequestDispatcher("dashboard_admin.jsp").forward(request, response);
                break;

            case "viewNewestCampaignDetail":
                String vId = request.getParameter("id");
                int intvId = Integer.parseInt(vId) - 1;
                Campaign campaign = control.getNewestCampaignDetail(intvId);
                session.setAttribute("campaignDetail", campaign);
                if (!campaign.getStatus().equals("Pending") || !campaign.getStatus().equals("In Progress")) {
                    session.setAttribute("poe", control.getPoe(campaign.getKno()));
                }

                request.getRequestDispatcher("detailCampaign_admin.jsp").forward(request, response);
                break;
            case "viewPendingCampaignsDetail":
                String pendCamId = request.getParameter("id");
                int pendCamIdInd = Integer.parseInt(pendCamId) - 1;
                Campaign pendCampaign = control.getPendingCampaignDetail(pendCamIdInd);
                session.setAttribute("campaignDetail", pendCampaign);
                if (!pendCampaign.getStatus().equals("Pending") || !pendCampaign.getStatus().equals("In Progress")) {
                    session.setAttribute("poe", control.getPoe(pendCampaign.getKno()));
                }

                request.getRequestDispatcher("detailCampaign_admin.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    private void updateSessions() {
        session.setAttribute("pendingPartners", control.getAllPendingPartners());

        session.setAttribute("pendingCampaigns", control.getAllPendingCampaigns());

        session.setAttribute("newestCampaigns", control.getAllNewestCampaigns());
        
    }
}
