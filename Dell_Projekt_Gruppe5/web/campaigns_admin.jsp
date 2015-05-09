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
        <div class="navbar navbar-inverse navbar-static-top">
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
                        <li class="active"><a href=""><img src="img/campaigns24.png"> Campaigns</a></li>                        
                        <li><a href="AdminServlet?action=partners"><img src="img/partners24.png"> All partners</a></li>
                    </ul>
                    <a href="LoginServlet?action=logout">
                        <button id="logoutBtn" type="submit" class="btn btn-warning navbar-right">Log out</button>
                    </a>
                </div>
            </div>
        </div>  

        <div class="container">
            <h1 class="tileHeader">All campaigns</h1>  
            <div class="col-md-0 marginTop">
                <table class="table table-striped table-bordered" id="campaigns">
                    <tr class="active"><th>Nr.</th><th>Company</th><th>Price EUR</th><th>Created</th><th>Status</th><th>Detail</th></tr>
                            <c:forEach var="campaign" items="${newestCampaigns}">
                        <tr class="tablerow">
                            <td></td>
                            <td>${campaign.name}</td>
                            <td>${campaign.price}</td>
                            <td>${campaign.created_date}</td>
                            <td>${campaign.status}</td>
                            <td><input id="campaignDetail" type="button" class="btn btn-info" value="View campaign"></td>
                        </tr>
                            </c:forEach>
                </table> 

            </div>
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <script>
            // Denne metode giver hver række et unikt id startende med 1
            $('.tablerow').each(function (i) {
                $("td:first", this).html(i + 1);
            });

            // PendingPartners
            $("table tr #campaignDetail").on('click', function () {
                row = $(this).closest('td').parent()[0].sectionRowIndex;
                location.href = "AdminServlet?action=viewAllCampaignsDetail&id=" + row;
            });
        </script>
    </body>
</html>