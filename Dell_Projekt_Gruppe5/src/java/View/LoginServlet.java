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
    private String loginControl;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        switch (action) {

            case "loginPage":
                request.setAttribute("loginErrorMessage", "");
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
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        switch (action) {

            case "login":

                /********** Form validation **********/
                if (!(validationErrorMessage = Validate.loginErrorMessage(username, password)).equals("")) {
                    // Fejl i login form
                    request.setAttribute("loginErrorMessage", validationErrorMessage);
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                } else {

                    /********** Check DB for user excistence **********/
                    loginControl = control.getLogin(username, password);
                    currentPno = control.getPno(username);
                    request.getSession().setAttribute("PNO", currentPno);
                    switch (loginControl) {
                        case "admin":
                            // Register a new admin user
                            
                            // Set arraylist containing all current partners campiagns as attribute
                            //getPendingPartners
                session.setAttribute("pendingPartners", control.getAllPendingPartners());

                //getPendingCampaigns
                session.setAttribute("pendingCampaigns", control.getAllPendingCampaigns());

                //getNewestPartners
                session.setAttribute("newestCampaigns", control.getAllNewestCampaigns());

                            request.getRequestDispatcher("dashboard_admin.jsp").forward(request, response);
                            break;

                        case "partner":
                            // Register a new partner user
                            
                            // Set arraylists as attributes to be shown in dashboard
                            session.setAttribute("pendingPartners", control.getAllPendingPartners());
                            session.setAttribute("pendingCampaigns", control.getAllPendingCampaigns());
                            session.setAttribute("newestCampaigns", control.getAllNewestCampaigns());
                            session.setAttribute("PNO", control.getPno(username));                            
                            System.out.println(currentPno);
                            request.getRequestDispatcher("dashboard_partner.jsp").forward(request, response);
                            break;

                        case "invalid login":
                            request.setAttribute("loginErrorMessage", loginControl);
                            request.getRequestDispatcher("index.jsp").forward(request, response);
                            break;

                        default:
                            request.setAttribute("loginErrorMessage", "Ups! Something went wrong, please try again.");
                            request.getRequestDispatcher("index.jsp").forward(request, response);
                            break;
                    }
                    break;

                }
        }
    
} 
    
  
}

  

