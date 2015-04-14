/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;


import Control.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "AdminServlet", urlPatterns = {"/AdminServlet"})
public class AdminServlet extends HttpServlet {
   
    Controller con = new Controller();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
            case "login":
                request.getRequestDispatcher("login_admin.jsp").forward(request, response);
                break;
            case "dashboardcampaigns":
                String id = request.getParameter("id");
                request.getRequestDispatcher("campaigns_admin.jsp").forward(request, response);
                break;
            case "logout":
                // currentUser = null;
                request.getRequestDispatcher("login_admin.jsp").forward(request, response);
                break;
                
            case "dashboardpartners":
                String spID = request.getParameter("id");
                int pID = Integer.parseInt(spID);
                con.getNewPartnerArrayList(pID-1);
                request.getRequestDispatcher("dashboard_admin.jsp").forward(request, response);
                
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
                // VALIDATE
               
                request.setAttribute("pnl", con.showAllNewPartners());
                request.setAttribute("newestCampaigns", con.showAllNewCampaigns());
                request.getRequestDispatcher("dashboard_admin.jsp").forward(request, response);

                break;
        }

    }

}
