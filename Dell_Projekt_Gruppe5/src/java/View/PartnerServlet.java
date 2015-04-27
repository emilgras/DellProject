/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Control.Controller;
import Control.PartnerIF;
import Model.Campaign;
import Model.CustomFile;
import Model.Partner;
import Utils.Validate;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;


@MultipartConfig(location = "/Users/EmilGras/Desktop/Dell_Projekt_Gruppe5/Dell_Projekt_Gruppe5/web/uploads", 
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(name = "PartnerServlet", urlPatterns = {"/PartnerServlet"})
public class PartnerServlet extends HttpServlet {

    PartnerIF control = new Controller();
    HttpSession session;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        session = request.getSession();
        session.setAttribute("control", control);

        int tableRowSelected = 0;
        
        String action = request.getParameter("action");

        switch (action) {

            case "dashboard":   
                request.setAttribute("message", control.isPartnerAccepted((Integer)session.getAttribute("PNO")));
                request.getRequestDispatcher("dashboard_partner.jsp").forward(request, response);        
                break;
            case "newcampaign":
                String error = control.isPartnerAccepted((Integer) session.getAttribute("PNO"));
                if (!error.equals("")) {
                    request.setAttribute("message", error);                 
                    request.getRequestDispatcher("dashboard_partner.jsp").forward(request, response);
                } else {
                    request.setAttribute("campaign", new Campaign("", "", 0, "", 0));
                    request.getRequestDispatcher("newcampaign_partner.jsp").forward(request, response);
                }
                break;
            case "signup":
                request.setAttribute("username", "");
                request.setAttribute("company", "");
                request.setAttribute("cvr", "");
                request.setAttribute("country", "");
                request.setAttribute("signupErrorMessage", "");
                request.setAttribute("dbErrorMessage", "");
                request.getRequestDispatcher("signup_partner.jsp").forward(request, response);
                break;

            case "upload":
                String stringId = request.getParameter("id");
                int intId = Integer.parseInt(stringId);
                control.updateCampaign(intId - 1);
                updateSessions();
                request.getRequestDispatcher("dashboard_partner.jsp").forward(request, response);
                break;

            case "viewDetail":
                tableRowSelected = Integer.parseInt(request.getParameter("id"));
                Campaign ownCampaign = control.getAllOwnPartnerCampaigns((int)session.getAttribute("PNO")).get(tableRowSelected - 1);
                session.setAttribute("campaignDetail", ownCampaign);
                session.setAttribute("poe", control.getPoe(ownCampaign.getKno()));
                request.getRequestDispatcher("detailCampaign_admin.jsp").forward(request, response);
                break;


            case "selectedCampaignForPoeUpload":
                updateSessions();
                String num = request.getParameter("id");
                int numInt = Integer.parseInt(num) - 1;
                int kno = control.getKnoForCampaign(numInt);
                session.setAttribute("partnersCampaignsID", numInt);
                session.setAttribute("campaignKno", kno);
                request.getRequestDispatcher("upload.jsp").forward(request, response);
                break;

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        session = request.getSession();

        String validationErrorMessage = "";
        String dbErrorMessage = "";

        String action = request.getParameter("action");

        switch (action) {

            case "partnerSignup":
                String user = request.getParameter("username");
                String pass = request.getParameter("password");
                String confirmPass = request.getParameter("confirmpassword");
                String name = request.getParameter("company");
                String cvr = request.getParameter("cvr");
                String country = request.getParameter("country");

                Partner partner = new Partner(user, pass, name, cvr, null, country);

                request.setAttribute("username", user);
                request.setAttribute("company", name);
                request.setAttribute("cvr", cvr);
                request.setAttribute("country", country);

                if (!(validationErrorMessage = Validate.signupErrorMessage(partner, confirmPass)).equals("")) {
                    // Fejl i signup formular
                    request.setAttribute("signupErrorMessage", validationErrorMessage);
                    request.getRequestDispatcher("signup.jsp").forward(request, response);
                } else {

                    if (!(dbErrorMessage = control.createPartner(partner)).equals("")) {
                        // Kunne ikke oprettes i DB
                        request.setAttribute("signupErrorMessage", dbErrorMessage);
                        request.getRequestDispatcher("signup_partner.jsp").forward(request, response);
                    } else {
                        // Yay - du er oprettet i DB
                        session.setAttribute("PNO", control.getPno(user));                       
                        session.setAttribute("message", control.isPartnerAccepted((Integer)session.getAttribute("PNO")));
                        updateSessions();
                        request.getRequestDispatcher("dashboard_partner.jsp").forward(request, response);
                    }
                }
                break;

            case "sendcampaign":
                int pno = (Integer) (request.getSession().getAttribute("PNO"));
                String campaignStart = request.getParameter("campaignstart");
                String campaignEnd = request.getParameter("campaignend"); 
                float price = Float.parseFloat(request.getParameter("price"));
                String description = request.getParameter("description");

                Campaign campaign = new Campaign(campaignStart, campaignEnd, price, description, pno);

                request.setAttribute("campaign", campaign);
                

                if (!(validationErrorMessage = Validate.campaignErrorMessage(campaign)).equals("")) {
                    // Fejl i login form
                    request.setAttribute("campaignErrorMessage", validationErrorMessage);
                    request.getRequestDispatcher("newcampaign.jsp").forward(request, response);
                } else {

                    if (!control.createCampaign(campaign)) {
                        // Kunne ikke oprette kampagne i database
                        dbErrorMessage = "Due to technical problems, we cannot create your campaign right now. Please try again later or contact our support for further help.";
                        request.setAttribute("campaignErrorMessage", dbErrorMessage);
                        request.getRequestDispatcher("newcampaign_partner.jsp").forward(request, response);
                    } else {
                        // Success!

                        updateSessions();
                        request.getRequestDispatcher("dashboard_partner.jsp").forward(request, response);

                    }
                }
                break;

            case "sendPoe":
                updateSessions();
                //String savePath = request.getServletContext().getRealPath("");
                //System.out.println("PATH: " + savePath);
                ArrayList<CustomFile> fileNames = new ArrayList();

                // Checks if upload folder excists. If will create one.
                File file = new File("");

                if (!file.exists()) {
                    file.mkdirs();
                }

                for (Part part : request.getParts()) {
                    String fileName = part.getSubmittedFileName();
                    // Save the file on the server in the specified folder
                    part.write(fileName);

                    // Splits the file into a name and an extension. ex: test.png --> name="test" extension="png"
                    if (fileName != null && fileName.contains(".")) {
                        String filename = fileName.substring(0, fileName.lastIndexOf('.'));
                        String fileextension = fileName.substring(fileName.lastIndexOf('.') + 1, fileName.length());
                        CustomFile customFile = new CustomFile(filename, fileextension);
                        fileNames.add(customFile);
                    }
                }

                if (control.uploadPoe((Integer) session.getAttribute("campaignKno"), (Integer) session.getAttribute("partnersCampaignsID"), fileNames)) {
                    updateSessions();
                    request.getRequestDispatcher("dashboard_partner.jsp").forward(request, response);
                } else {
                    request.setAttribute("uploadPoeError", "Sorry, not able to upload files. Please, try again");
                    request.getRequestDispatcher("upload.jsp").forward(request, response);
                }
                //con.updateCampaignWithKno((Integer)session.getAttribute("campaignKno"), (Integer)session.getAttribute("partnersCampaignsID"));

                break;
        }

    }

    private void updateSessions() {
        session.setAttribute("pCam", control.getAllOwnPartnerCampaigns((Integer) session.getAttribute("PNO")));
    }
}
