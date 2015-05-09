package View;

import Interfaces.AdminIF;
import Control.Controller;
import Entities.Campaign;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * * author Ander, Emil, Fredrik **
 */

@WebServlet(name = "AdminServlet", urlPatterns = {"/AdminServlet"})
public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        AdminIF control = (AdminIF) session.getAttribute("control");
        if (control == null) {
            control = new Controller();
            session.setAttribute("control", control);
        }

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
                session.setAttribute("AllPartners", control.getAllPartners());
                request.getRequestDispatcher("partners_admin.jsp").forward(request, response);
                break;
            case "acceptpartner": //(Tjek)
                tableRowSelected = Integer.parseInt(request.getParameter("id"));
                request.setAttribute("errorMessage", control.acceptPartner(tableRowSelected - 1));
                session.setAttribute("pendingPartners", control.getAllPendingPartners());
                session.setAttribute("countPartners", control.countPartners());
                session.setAttribute("countCountries", control.countCountries());
                request.getRequestDispatcher("dashboard_admin.jsp").forward(request, response);
                break;
            case "declinepartner":
                tableRowSelected = Integer.parseInt(request.getParameter("id"));
                request.setAttribute("errorMessage", control.deletePartner(tableRowSelected - 1));
                session.setAttribute("pendingPartners", control.getAllPendingPartners());
                session.setAttribute("countPartners", control.countPartners());
                session.setAttribute("countCountries", control.countCountries());
                request.getRequestDispatcher("dashboard_admin.jsp").forward(request, response);
                break;
            case "acceptcampaign": //(Tjek)
                tableRowSelected = Integer.parseInt(request.getParameter("id"));
                request.setAttribute("errorMessage", control.acceptCampaign(tableRowSelected - 1));
                session.setAttribute("pendingCampaigns", control.getAllPendingCampaigns());
                session.setAttribute("newestCampaigns", control.getAllNewestCampaigns());
                session.setAttribute("countCampaigns", control.countCampaigns());
                session.setAttribute("nuvaerendeBelob", control.getCurrentFund());
                session.setAttribute("prices", control.getAllPrices());
                request.getRequestDispatcher("dashboard_admin.jsp").forward(request, response);
                break;
            case "declinecampaign": 
                tableRowSelected = Integer.parseInt(request.getParameter("id"));
                request.setAttribute("errorMessage", control.deleteCampaign(tableRowSelected - 1));
                session.setAttribute("pendingCampaigns", control.getAllPendingCampaigns());
                session.setAttribute("newestCampaigns", control.getAllNewestCampaigns());
                request.getRequestDispatcher("dashboard_admin.jsp").forward(request, response);
                break;
            case "declinecampaignpoe": //(Tjek)
                tableRowSelected = Integer.parseInt(request.getParameter("id"));
                request.setAttribute("errorMessage", control.rollBackCampaign(tableRowSelected - 1));
                session.setAttribute("pendingCampaigns", control.getAllPendingCampaigns());
                session.setAttribute("newestCampaigns", control.getAllNewestCampaigns());
                request.getRequestDispatcher("dashboard_admin.jsp").forward(request, response);
                break;
            case "viewNewestCampaignDetail":  //(Tjek)
                tableRowSelected = Integer.parseInt(request.getParameter("id"));
                Campaign newCampaign = control.getAllNewestCampaigns().get(tableRowSelected - 1);
                session.setAttribute("campaignDetail", newCampaign);
                session.setAttribute("poe", control.getPoe(newCampaign.getKno()));
                request.getRequestDispatcher("detailCampaign_admin.jsp").forward(request, response);
                break;
            case "viewPendingCampaignsDetail": //(Tjek)
                tableRowSelected = Integer.parseInt(request.getParameter("id"));
                Campaign pendCampaign = control.getAllPendingCampaigns().get(tableRowSelected - 1);
                session.setAttribute("campaignDetail", pendCampaign);
                session.setAttribute("poe", control.getPoe(pendCampaign.getKno()));
                request.getRequestDispatcher("detailCampaign_admin.jsp").forward(request, response);
                break;
            case "viewAllCampaignsDetail": //(Tjek)
                tableRowSelected = Integer.parseInt(request.getParameter("id"));
                Campaign AllCampaign = control.getAllNewestCampaigns().get(tableRowSelected - 1);
                session.setAttribute("campaignDetail", AllCampaign);
                session.setAttribute("poe", control.getPoe(AllCampaign.getKno()));
                request.getRequestDispatcher("detailCampaign_admin.jsp").forward(request, response);
                break;
        }
    }
}
