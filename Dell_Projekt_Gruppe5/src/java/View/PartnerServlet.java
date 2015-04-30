/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Control.Controller;
import Interfaces.PartnerIF;
import Entities.Campaign;
import Entities.CustomFile;
import Entities.Partner;
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        PartnerIF control = (PartnerIF)session.getAttribute("control");
        if (control == null) {
            control = new Controller();
            session.setAttribute("control", control);
        }

        int tableRowSelected = 0;

        String action = request.getParameter("action");

        switch (action) {

            case "dashboard": // Tjek  
                request.setAttribute("message", control.isPartnerAccepted((Integer) session.getAttribute("PNO")));
                request.getRequestDispatcher("dashboard_partner.jsp").forward(request, response);
                break;
            case "newcampaign": // Tjek
                String error = control.isPartnerAccepted((Integer) session.getAttribute("PNO"));
                if (!error.equals("")) {
                    request.setAttribute("message", error);
                    request.getRequestDispatcher("dashboard_partner.jsp").forward(request, response);
                } else {
                    request.setAttribute("campaign", new Campaign("", "", 0, "", 0));
                    request.getRequestDispatcher("newcampaign_partner.jsp").forward(request, response);
                }
                break;
            case "signup": // Tjek
                request.setAttribute("partner", new Partner("", "", "", ""));
                request.setAttribute("signupErrorMessage", "");
                request.setAttribute("dbErrorMessage", "");
                request.getRequestDispatcher("signup.jsp").forward(request, response);
                break;
            case "viewDetail": // Mangler implementation
                tableRowSelected = Integer.parseInt(request.getParameter("id"));
                Campaign ownCampaign = control.getAllOwnPartnerCampaigns((int) session.getAttribute("PNO")).get(tableRowSelected - 1);
                session.setAttribute("campaignDetail", ownCampaign);
                session.setAttribute("poe", control.getPoe(ownCampaign.getKno()));
                request.getRequestDispatcher("detailCampaign_partner.jsp").forward(request, response);
                break;
            case "selectedCampaignForPoeUpload": // Tjek
                tableRowSelected = Integer.parseInt(request.getParameter("id"));
                session.setAttribute("campaignKno", control.getAllOwnPartnerCampaigns((Integer) session.getAttribute("PNO")).get(tableRowSelected - 1).getKno());
                request.getRequestDispatcher("upload.jsp").forward(request, response);
                break;
            case "selectedCampaignForInvoiceUpload":
                tableRowSelected = Integer.parseInt(request.getParameter("id"));
                int kno = control.getAllOwnPartnerCampaigns((Integer) session.getAttribute("PNO")).get(tableRowSelected - 1).getKno();
                session.setAttribute("campaignKno", kno);
                control.updateCampaignWithKno(kno);
                session.setAttribute("pCam", control.getAllOwnPartnerCampaigns((Integer) session.getAttribute("PNO")));
                request.getRequestDispatcher("dashboard_partner.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        PartnerIF control = (PartnerIF)session.getAttribute("control");
        if (control == null) {
            control = new Controller();
            session.setAttribute("control", control);
        }

        String errorMessage = "";

        String action = request.getParameter("action");

        switch (action) {

            case "partnerSignup": // Tjek
                String user = request.getParameter("username");
                String pass = request.getParameter("password");
                String confirmPass = request.getParameter("confirmpassword");
                String name = request.getParameter("company");
                String cvr = request.getParameter("cvr");
                String country = request.getParameter("country");
                Partner partner = new Partner(user, pass, name, cvr, country);

                request.setAttribute("partner", partner);

                if (!(errorMessage = Validate.signupErrorMessage(partner, confirmPass)).equals("")) {
                    // Fejl i signup formular
                    request.setAttribute("signupErrorMessage", errorMessage);
                    request.getRequestDispatcher("signup.jsp").forward(request, response);
                } else {

                    if (!((errorMessage = control.createPartner(partner)).equals(""))) {
                        // Kunne ikke oprettes i DB
                        request.setAttribute("signupErrorMessage", errorMessage);
                        request.getRequestDispatcher("signup.jsp").forward(request, response);
                    } else {
                        // Yay - du er oprettet i DB
                        session.setAttribute("PNO", control.getPno(user));
                        session.setAttribute("message", control.isPartnerAccepted((Integer) session.getAttribute("PNO")));
                        session.setAttribute("pCam", control.getAllOwnPartnerCampaigns((Integer) session.getAttribute("PNO")));
                        request.getRequestDispatcher("dashboard_partner.jsp").forward(request, response);
                    }
                }
                break;

            case "sendcampaign": // Tjek
                float price;
                int pno = (Integer) (request.getSession().getAttribute("PNO"));
                String campaignStart = request.getParameter("campaignstart");
                String campaignEnd = request.getParameter("campaignend");
                String description = request.getParameter("description");
                if (!request.getParameter("price").equals("")) {
                    price = Float.parseFloat(request.getParameter("price"));
                } else {
                    price = 0;
                }

                Campaign campaign = new Campaign(campaignStart, campaignEnd, price, description, pno);

                request.setAttribute("campaign", campaign);

                if (!(errorMessage = Validate.campaignErrorMessage(campaign)).equals("")) {
                    // Fejl i login form
                    request.setAttribute("campaignErrorMessage", errorMessage);
                    request.getRequestDispatcher("newcampaign_partner.jsp").forward(request, response);
                } else {

                    if (!(errorMessage = control.createCampaign(campaign)).equals("")) {
                        // Kunne ikke oprette kampagne i database
                        request.setAttribute("campaignErrorMessage", errorMessage);
                        request.getRequestDispatcher("newcampaign_partner.jsp").forward(request, response);
                    } else {
                        // Success
                        session.setAttribute("pCam", control.getAllOwnPartnerCampaigns((Integer) session.getAttribute("PNO")));
                        request.getRequestDispatcher("dashboard_partner.jsp").forward(request, response);
                    }
                }
                break;

            case "sendPoe": // Tjek
                ArrayList<CustomFile> fileNames = new ArrayList();
                for (Part part : request.getParts()) {
                    String fileName = part.getSubmittedFileName();
                    part.write(fileName);

                    // Splits the file into a name and an extension. ex: test.png --> name="test" extension="png"
                    if (fileName != null && fileName.contains(".")) {
                        String filename = fileName.substring(0, fileName.lastIndexOf('.'));
                        String fileextension = fileName.substring(fileName.lastIndexOf('.') + 1, fileName.length());
                        CustomFile customFile = new CustomFile(filename, fileextension);
                        fileNames.add(customFile);
                    }
                }
                if ((errorMessage = control.uploadPoe((Integer) session.getAttribute("campaignKno"), fileNames)).equals("")) {
                    session.setAttribute("pCam", control.getAllOwnPartnerCampaigns((Integer) session.getAttribute("PNO")));
                    request.getRequestDispatcher("dashboard_partner.jsp").forward(request, response);
                } else {
                    session.setAttribute("uploadErrorMessage", errorMessage);
                    request.getRequestDispatcher("upload.jsp").forward(request, response);
                }
                break;
        }
    }
}
