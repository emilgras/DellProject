/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serlvets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author EmilGras
 */
@WebServlet(name = "MyServlet", urlPatterns = {"/MyServlet"})
public class MyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        switch (action) {
            case "dashboard":
                request.getRequestDispatcher("dashboard_admin.jsp").forward(request, response);
                break;
            case "addpartner":
                request.getRequestDispatcher("addpartner.jsp").forward(request, response);
                break;
            case "campaigns":
                request.getRequestDispatcher("campaigns.jsp").forward(request, response);
                break;
            case "statistics":
                request.getRequestDispatcher("statistics.jsp").forward(request, response);
                break;
            case "partners":
                request.getRequestDispatcher("partners.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
