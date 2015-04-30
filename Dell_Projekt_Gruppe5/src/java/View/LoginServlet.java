package View;

import Control.Controller;
import Interfaces.LoginIF;
import Entities.Partner;
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

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        LoginIF control = (LoginIF)session.getAttribute("control");
        if (control == null) {
            control = new Controller();
            session.setAttribute("control", control);
        }
        
        
        String action = request.getParameter("action");

        switch (action) {

            case "loginPage":
                request.setAttribute("username", "");
                request.setAttribute("loginErrorMessage", "");
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;

            case "signupPage":
                request.setAttribute("partner", new Partner("", "", "", ""));
                request.setAttribute("signupErrorMessage", "");
                request.getRequestDispatcher("signup.jsp").forward(request, response);
                break;

            case "logout":
                request.getSession().invalidate();
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        LoginIF control = new Controller();
        HttpSession session = request.getSession();
        session.setAttribute("control", control);

        String errorMessage = "";

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        String action = request.getParameter("action");

        switch (action) {

            case "login":

                /**
                 * ******** Form validation *********
                 */
                if (!(errorMessage = Validate.loginErrorMessage(username, password)).equals("")) {
                    // Fejl i login form
                    request.setAttribute("loginErrorMessage", errorMessage);
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                } else {

                    /**
                     * ******** Check DB for user excistence *********
                     */
                    String userCheck = control.getLogin(username, password);

                    switch (userCheck) {
                        
                        case "admin":
                            //getPendingPartners
                            session.setAttribute("pendingPartners", control.getAllPendingPartners());

                            //getPendingCampaigns
                            session.setAttribute("pendingCampaigns", control.getAllPendingCampaigns());

                            //getNewestPartners
                            session.setAttribute("newestCampaigns", control.getAllNewestCampaigns());
                            
                            // All Dell Partners
                            session.setAttribute("AllPartners", control.getAllPartners());
                            
                            //getAllPrices
                            session.setAttribute("prices", control.getAllPrices());
                            
                            //getBudget stuff
                            session.setAttribute("nuvaerendeBelob", control.getNuvaerendeBelob());
                            
                            
                            session.setAttribute("startsBelob", control.getStartsBelob());
                            
                            
                            session.setAttribute("countPartners", control.countPartners());
                            
                            
                            session.setAttribute("countCampaigns", control.countCampaigns());
                            
                            
                            session.setAttribute("countCountries", control.countCountries());
                            
                            request.getRequestDispatcher("dashboard_admin.jsp").forward(request, response);
                            break;

                        case "partner":      
                            
                            session.setAttribute("PNO", control.getPno(username)); 
                            
                            session.setAttribute("message", control.isPartnerAccepted((Integer)session.getAttribute("PNO")));
                            
                            session.setAttribute("pCam", control.getAllOwnPartnerCampaigns((Integer)session.getAttribute("PNO")));
                            
                            request.getRequestDispatcher("dashboard_partner.jsp").forward(request, response);
                            break;

                        case "Invalid username or password":
                            request.setAttribute("loginErrorMessage", userCheck);
                            
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
