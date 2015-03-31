<%-- 
    Document   : dashboard_admin
    Created on : 31-03-2015, 23:25:41
    Author     : EmilGras
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="style.css" rel="stylesheet">
    </head>
    <body>
        <!------------------- NAV ------------------->
        <div class="navbar navbar-default">
            <div class="container">
                <div class="navbar-header">
                    <a href="" class="navbar-brand">Admin Website</a>
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>        
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="">Dashboard</a></li>
                        <li><a href="">Page 2</a></li>
                        <!-- <li><a href="">Page 3</a></li> -->
                    </ul>
                    <button type="submit" class="btn btn-warning btn-lg navbar-right">Log out</button>          
                </div>
            </div>
        </div>  

        <div class="container contentContainer marginBottom" id="topContainer">
            <div class="row center">  
                
                <div class="col-md-6 marginTop">
                    <div><h3 class="tileHeader"><span class="glyphicon glyphicon-off"></span> Create partner</h3></div>
                    <div class="tile tileImage1">

                    </div>
                </div>
                
                <div class="col-md-6 marginTop">
                    <div><h3 class="tileHeader"><span class="glyphicon glyphicon-off"></span> Track projects</h3></div>
                    <div class="tile tileImage2">
                        
                    </div>
                </div>
                
                <div class="col-md-6 marginTop">
                    <div><h3 class="tileHeader"><span class="glyphicon glyphicon-off"></span> Partners</h3></div>
                    <div class="tile tileImage2">
                        
                    </div>
                </div>
                <div class="col-md-6 marginTop">
                    <div><h3 class="tileHeader"><span class="glyphicon glyphicon-off"></span> Campaigns</h3></div>
                    <div class="tile tileImage1">
                        
                    </div>
                </div>
            </div>
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>

