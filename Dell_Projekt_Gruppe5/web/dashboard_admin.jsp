<%-- 
    Document   : dashboard_admin
    Created on : 31-03-2015, 23:25:41
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
                        <li class="active"><a href=""><img src="img/home24.png"> Dashboard</a></li>                   
                        <li><a href="AdminServlet?action=campaigns"><img src="img/campaigns24.png"> Campaigns</a></li>
                        <li><a href="AdminServlet?action=partners"><img src="img/partners24.png"> All partners</a></li>     
                    </ul>
                    <a href="AdminServlet?action=logout">
                        <button id="logoutBtn" type="submit" class="btn btn-warning navbar-right">Log out</button>
                    </a>
                </div>
            </div>
        </div>  

        <div class="container marginBottom">
            <h1 class="tileHeader">Dashboard</h1>
            <div class="row center">  
                <div class="col-md-6 marginTop">

                    <div class="tile">
                        <div>
                            <img src="img/addpartner.png">
                            <h3 class="tileHeader"><span class="glyphicon glyphicon-off"></span> New Applicants</h3>
                        </div>
                    </div>
                </div>

                <div class="col-md-6 marginTop">
                    <a href="AdminServlet?action=campaigns" id="textDecorationNone">
                        <div class="tile">
                            <div>
                                <img src="img/money.png">
                                <h3 class="tileHeader"><span class="glyphicon glyphicon-export"></span> Newest campaigns</h3>
                                <!-- <table class="table table-striped table-bordered">
                                    <tr class="active"><th>Name</th><th>Age</th><th>Image</th><th>Progress</th><th>Status</th><th>View case</th></tr>
                                    <tr><td>Emil</td><td>23</td><td>image.png</td><td>88%</td><td class="danger">Not Complete</td><td><button type="button" class="btn btn-default">Show Info</button></td></tr>  
                                    <tr><td>Hellen</td><td>27</td><td>image.png</td><td>100%</td><td class="success">Complete</td><td><button type="button" class="btn btn-default">Show Info</button></td></tr>
                                    <tr><td>Rob</td><td>35</td><td>image.png</td><td>12%</td><td class="danger">Not Complete</td><td><button type="button" class="btn btn-default">Show Info</button></td></tr>
                                </table> -->
                            </div>
                        </div>
                    </a>
                </div>

                <div class="col-md-12 marginTop">
                    <div id="tileImage2" class="tile">
                        <div>
                            <img src="img/partners.png">
                            <h3 class="tileHeader"><span class="glyphicon glyphicon-download"></span> Statistics</h3>
                        </div>
                    </div>
                </div>

                <!-- <div class="col-md- marginTop">
                    <div id="tileImage1" class="tile">
                        <div>
                            <img src="img/campains.png">
                            <h3 class="tileHeader"><span class="glyphicon glyphicon-earphone"></span> View campaigns</h3>
                        </div>
                    </div>
                </div> -->
            </div>
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
    </body>
</html>

