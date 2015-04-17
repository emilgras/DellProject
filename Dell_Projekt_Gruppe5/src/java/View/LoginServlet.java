package View;

import Control.Controller;
import Control.LoginIF;
import Utils.Validate;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    LoginIF control = new Controller();

    private int currentPno = 0;
    private String validationErrorMessage;
    private String dbErrorMessage;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        switch (action) {
            
            case "loginPage":
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;

            case "signupPage":
                request.getRequestDispatcher("signup.jsp").forward(request, response);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String action = request.getParameter("action");

        switch (action) {

            case "loginPartner":    
                String username = request.getParameter("username");
                String password = request.getParameter("password");

                request.setAttribute("username", username);

                if (!(validationErrorMessage = Validate.loginErrorMessage(username, password)).equals("")) {
                    // Fejl i login form
                    request.setAttribute("loginErrorMessage", validationErrorMessage);
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                } else {

                    // Succes ved login form
                    if (!(dbErrorMessage = control.getLogin(username, password)).equals("")) {
                        // Fejl ved oprettelse i DB
                        request.setAttribute("loginErrorMessage", dbErrorMessage);
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    } else {

                        currentPno = control.getPno(username);
                        //getPendingCampaigns
                        session.setAttribute("pendingCampaigns", control.getAllPendingCampaigns());

                        //getNewestPartners
                        session.setAttribute("newestCampaigns", control.getAllNewestCampaigns());

                        // Sender brugeren videre til dashboard
                        request.getRequestDispatcher("dashboard_partner.jsp").forward(request, response);

                    }
                }
                break;

            case "loginAdmin":
                // VALIDATION

                //getPendingPartners
                session.setAttribute("pendingPartners", control.getAllPendingPartners());

                //getPendingCampaigns
                session.setAttribute("pendingCampaigns", control.getAllPendingCampaigns());

                //getNewestPartners
                session.setAttribute("newestCampaigns", control.getAllNewestCampaigns());

                // Sender brugeren videre til dashboard
                request.getRequestDispatcher("dashboard_admin.jsp").forward(request, response);

                break;
        }

    }

}
