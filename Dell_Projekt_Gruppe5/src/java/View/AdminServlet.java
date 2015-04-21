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
                System.out.println("ArrayList index: " + (intId - 1));
                control.acceptPartner(intId - 1);
                request.getRequestDispatcher("dashboard_admin.jsp").forward(request, response);
                break;
            case "acceptcampaign":
                String cId = request.getParameter("id");
                int intcId = Integer.parseInt(cId);
                control.acceptCampaign(intcId - 1);
                break;
                //getNewestPartners
                //session.setAttribute("newestCampaigns", control.getAllNewestCampaigns());

            case "rollbackcampaign":
                String rId = request.getParameter("id");
                int intRID =  Integer.parseInt(rId);
                control.rollBackCampaign(intRID);

                request.getRequestDispatcher("dashboard_admin.jsp").forward(request, response);
                break;
            case "viewNewestCampaignDetail":
                String vId = request.getParameter("id");
                int intvId = Integer.parseInt(vId);
                Campaign campaign = control.getNewestCampaignDetail(intvId - 1);

                session.setAttribute("campaignDetail", campaign);

                request.getRequestDispatcher("detailCampaign_admin.jsp").forward(request, response);
                break;
            case "viewPendingCampaignsDetail":
                String pendCamId = request.getParameter("id");
                int pendCamIdInd = Integer.parseInt(pendCamId) - 1;
                Campaign pendCampaign = control.getPendingCampaignDetail(pendCamIdInd);

                session.setAttribute("campaignDetail", pendCampaign);

                request.getRequestDispatcher("detailCampaign_admin.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        String action = request.getParameter("action");
//        HttpSession session = request.getSession();
    }

}
