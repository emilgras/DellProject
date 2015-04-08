/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AdminServlet", urlPatterns = {"/AdminServlet"})
public class AdminServlet extends HttpServlet {

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
            /*case "statistics":
             request.getRequestDispatcher("statistics_admin.jsp").forward(request, response);
             break;*/
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

        String action = request.getParameter("action");

        switch (action) {

            case "adminLogin":
                // VALIDATE
                request.getRequestDispatcher("dashboard_admin.jsp").forward(request, response);
                break;
        }
    }
}
