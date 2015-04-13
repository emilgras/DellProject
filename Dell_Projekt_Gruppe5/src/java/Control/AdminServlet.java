/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.Partner;
import Model.DBFacade;

import Model.DBFacade;
import com.sun.corba.se.spi.presentation.rmi.StubAdapter;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "AdminServlet", urlPatterns = {"/AdminServlet"})
public class AdminServlet extends HttpServlet {

    
DBFacade partnerFacade = DBFacade.getInstance();



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
            case "logout":
                // currentUser = null;
                request.getRequestDispatcher("login_admin.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");

        switch (action) {

            case "adminLogin":
                // VALIDATE

                request.setAttribute("pnl", partnerFacade.showPartnerName());
                System.out.println(partnerFacade.showPartnerName().get(0).getName());

                ArrayList list = new ArrayList();
               
                session.setAttribute("list", list);

                request.getRequestDispatcher("dashboard_admin.jsp").forward(request, response);
                
                break;
        }
    }
    
    
}
