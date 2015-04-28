package View;

import Control.Controller;
import Control.LoginIF;
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
        
        LoginIF control = new Controller();
        HttpSession session = request.getSession();
        session.setAttribute("control", control);      
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
                            // Register a new admin user

                            // Set arraylist containing all current partners campiagns as attribute
                            //getPendingPartners
                            session.setAttribute("pendingPartners", ((Controller)session.getAttribute("control")).getAllPendingPartners());

                            //getPendingCampaigns
                            session.setAttribute("pendingCampaigns", ((Controller)session.getAttribute("control")).getAllPendingCampaigns());

                            //getNewestPartners
                            session.setAttribute("newestCampaigns", ((Controller)session.getAttribute("control")).getAllNewestCampaigns());
                            
                            // All Dell Partners
                            session.setAttribute("AllPartners", ((Controller)session.getAttribute("control")).getAllPartners());
                            
                            //getAllPrices
                            session.setAttribute("prices", ((Controller)session.getAttribute("control")).getAllPrices());
                            
                            //getBudget stuff
                            session.setAttribute("nuvaerendeBelob", ((Controller)session.getAttribute("control")).getNuvaerendeBelob());
                            
                            session.setAttribute("startsBelob", ((Controller)session.getAttribute("control")).getStartsBelob());
                            
                            
                            
                            request.getRequestDispatcher("dashboard_admin.jsp").forward(request, response);
                            break;

                        case "partner":      
                            
                            session.setAttribute("PNO", ((Controller)session.getAttribute("control")).getPno(username)); 
                            
                            session.setAttribute("message", ((Controller)session.getAttribute("control")).isPartnerAccepted((Integer)session.getAttribute("PNO")));
                            
                            session.setAttribute("pCam", ((Controller)session.getAttribute("control")).getAllOwnPartnerCampaigns((Integer)session.getAttribute("PNO")));
                            
                            request.getRequestDispatcher("dashboard_partner.jsp").forward(request, response);
                            break;

                        case "invalid login":
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
