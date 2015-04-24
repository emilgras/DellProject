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

@MultipartConfig(location = "C:\\Users\\Frederik\\Documents\\NetBeansProjects\\Dell_Projekt_Gruppe5\\Dell_Projekt_Gruppe5\\web\\uploads", fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(name = "PartnerServlet", urlPatterns = {"/PartnerServlet"})

public class PartnerServlet extends HttpServlet {

    HttpSession session;
    // Flyt session object fra globalt
    PartnerIF control = new Controller();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        session = request.getSession();

        String action = request.getParameter("action");

        switch (action) {

            case "dashboard":
                if (control.isPartnerAccepted((Integer) session.getAttribute("PNO"))) {
                    request.setAttribute("partnerNotAccepted", "");
                    request.getRequestDispatcher("dashboard_partner.jsp").forward(request, response);
                } else {
                    request.setAttribute("partnerNotAccepted", "You are not able to create any campaigns before Dell has accpeted your partnership");
                    request.getRequestDispatcher("dashboard_partner.jsp").forward(request, response);
                }           
                break;
            case "newcampaign":
                if (control.isPartnerAccepted((Integer) session.getAttribute("PNO"))) {
                    request.setAttribute("campaignstart", "");
                    request.setAttribute("campaignend", "");
                    request.setAttribute("price", "");
                    request.setAttribute("description", "");
                    request.setAttribute("validationErrorMessage", "");
                    request.setAttribute("dbErrorMessage", "");
                    request.getRequestDispatcher("newcampaign_partner.jsp").forward(request, response);
                } else {
                    request.setAttribute("partnerNotAccepted", "Your partnership is not yet accepted by Dell");
                    request.getRequestDispatcher("dashboard_partner.jsp").forward(request, response);
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

        String validationErrorMessage;
        String dbErrorMessage;

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
                        updateSessions();
                        request.getRequestDispatcher("dashboard_partner.jsp").forward(request, response);
                    }
                }
                break;

            case "sendcampaign":
                int pno = (Integer) (request.getSession().getAttribute("PNO"));
                System.out.println("PNO i PARTNERSERVLET: " + pno);
                String campaignStart = request.getParameter("campaignstart");   /// Lav streng om til sql date
                String campaignEnd = request.getParameter("campaignend");       /// Lav streng om til sql date
                String priceString = request.getParameter("price");
                float price = Float.parseFloat(priceString);
                String description = request.getParameter("description");

                Campaign campaign = new Campaign(campaignStart, campaignEnd, price, description, pno);

                request.setAttribute("campaignstart", campaignStart);
                request.setAttribute("campaignend", campaignEnd);
                request.setAttribute("price", price);
                request.setAttribute("description", description);
                // sæt campaign som attribut i stedet

                if (!(validationErrorMessage = Validate.campaignErrorMessage(campaign)).equals("")) {
                    // Fejl i login form
                    System.out.println("Message: " + validationErrorMessage);
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
