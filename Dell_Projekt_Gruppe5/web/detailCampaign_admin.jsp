<%-- 
    Document   : campaigns
    Created on : 04-04-2015, 16:33:25
    Author     : EmilGras
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="style.css" rel="stylesheet">
    </head>
    <body>
        <!------------------- NAV ------------------ -->
        <div class="navbar navbar-inverse">
            <div class="container">
                <div class="navbar-header">
                    <a href="AdminServlet?action=dashboard" class="navbar-brand">Admin Website</a>
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>        
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li><a href="AdminServlet?action=dashboard"><img src="img/home24.png"> Dashboard</a></li>
                        <li><a href="AdminServlet?action=campaigns"><img src="img/campaigns24.png"> Campaigns</a></li>
                        <li><a href="AdminServlet?action=partners"><img src="img/partners24.png"> All partners</a></li>
                    </ul>
                    <a href="AdminServlet?action=logout">
                        <button id="logoutBtn" type="submit" class="btn btn-warning navbar-right">Log out</button>
                    </a>
                </div>
            </div>
        </div>  

        <div class="container"> 
            <h1 class="tileHeader">Campaign detail</h1>
            <div class="col-md-0 marginTop">
                <a href="AdminServlet?action=dashboard">Tilbage</a>
                <h3>Id: ${campaignDetail.kno}</h3>
                <h3>Pris: ${campaignDetail.pris}</h3>
                <h3>Pris: ${campaignDetail.beskrivelse}</h3>
                <h3>Forventet start: ${campaignDetail.start_dato}</h3>
                <h3>Forventet slut ${campaignDetail.slut_dato}</h3>
                <h3>Firma: ${campaignDetail.navn}</h3>
                <h3>Firma: ${campaignDetail.cvr}</h3>
                <h3>Oprettet: ${campaignDetail.oprettelse_dato}</h3>
                <h3>Status: ${campaignDetail.status}</h3>
                
                <!-- kno,beskrivelse,status,oprettelse_dato,start_dato,slut_dato,pris,kampagne.pno,navn,cvr -->
                
            </div>
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <script>
            $("table tr #campaign").on('click', function () {
                row = $(this).closest('td').parent()[0].sectionRowIndex;
                location.href = "AdminServlet?action=viewcampaign&id=" + row;
            });
        </script>
    </body>
</html>