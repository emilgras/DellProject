<%-- 
    Document   : campaigns
    Created on : 04-04-2015, 16:33:25
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
                        <!-- <li><a href="AdminServlet?action=statistics"><img src="img/money24.png"> Statistics</a></li> -->
                        <li><a href="AdminServlet?action=partners"><img src="img/partners24.png"> All partners</a></li>
                    </ul>
                    <a href="AdminServlet?action=logout">
                        <button id="logoutBtn" type="submit" class="btn btn-warning navbar-right">Log out</button>
                    </a>
                </div>
            </div>
        </div>  
        
        <div class="container contentContainer marginBottom">
            <h1 class="tileHeader">Campaigns</h1>
            <div class="row center">  
               
            </div>
        </div>
       
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
    </body>
</html>