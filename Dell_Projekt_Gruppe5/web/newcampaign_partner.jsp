<%-- 
    Document   : campaigns_partner
    Created on : 06-04-2015, 20:00:13
    Author     : EmilGras
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                    <a href="PartnerServlet?action=dashboard" class="navbar-brand">User Website</a>
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>        
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li><a href="PartnerServlet?action=dashboard"><img src="img/home24.png"> Dashboard</a></li>                   
                        <li class="active"><a href="PartnerServlet?action=newcampaign"><img src="img/newcampaign24.png"> New Campaign</a></li>   
                    </ul>
                    <button id="logoutBtn" type="submit" class="btn btn-warning navbar-right">Log out</button>          
                </div>
            </div>
        </div>  

        <div class="container contentContainer marginBottom">
            <h1 class="tileHeader">New campaign</h1>
            <div class="row center">  

            </div>
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>   
    </body>
</html>
