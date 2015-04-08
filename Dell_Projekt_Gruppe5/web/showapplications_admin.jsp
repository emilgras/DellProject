<%-- 
    Document   : showapplications_admin
    Created on : 08-04-2015, 14:52:15
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
                        <li><a href="AdminServlet?action=campaigns"><img src="img/campaigns24.png"> Campaigns</a></li>
                        <li class="active"><a href=""><img src="img/partners24.png"> All partners</a></li>
                    </ul>
                    <button id="logoutBtn" type="submit" class="btn btn-warning navbar-right">Log out</button>          
                </div>
            </div>
        </div>  

        <div class="container">
            <h1 class="tileHeader marginBottom">Applicants</h1>
            <div class="row center">  
                <table class="table table-striped table-bordered">
                    <tr class="active"><th>Number</th><th>CVR</th><th>Name</th><th>Description</th><th>Start est.</th><th>End est.</th><th>Price</th><th>Action</th></tr>
                    <tr><td>Number</td><td>CVR</td><td>Name</td><td>Description</td><td>Beginning est</td><td>End est</td><td>Price</td><td><a href="AdminServlet?action=acceptapplication&id= ..... ">  <!-- send number med til AdminServlet -->   <button type="button" class="btn btn-info">Send acceptance</button></a></td></tr>  
                </table>
            </div>
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
    </body>
</html>

