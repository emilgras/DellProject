<%-- 
    Document   : partners
    Created on : 04-04-2015, 16:33:40
    Author     : EmilGras
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="bootstrap/css/fileinput.css" media="all" rel="stylesheet" type="text/css" /> 
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="style.css" rel="stylesheet">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js" type="text/javascript"></script>


        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script src="bootstrap/js/fileinput.js" type="text/javascript"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js" type="text/javascript"></script>


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
                        <li><a href=""><img src="img/partners24.png"> All partners</a></li>
                    </ul>
                    <button id="logoutBtn" type="submit" class="btn btn-warning navbar-right">Log out</button>          
                </div>
            </div>
        </div>  

        <div class="container contentContainer marginBottom">
            <h1 class="tileHeader marginBottom">Upload POE</h1>
            <h4>This is the place you upload all of your pictures bla bla bla...</h4>
            <h3 style="color: indianred">${uploadPoeError}</h3>
            <!------------ Upload file content ------------>
            <div class="row">
                <div class="col-md-0 marginTop">

                    <form action="PartnerServlet?action=sendPoe" method="post" enctype="multipart/form-data">
                        <div class="form-group">
                            <input id="file" type="file" class="file" name="files" multiple>
                            <div id="errorBlock" class="help-block"></div>
                        </div>
                    </form>
                </div>
            </div>

            <!------------ Description content ------------>
            <div class="row center marginTop">
                <div class="col-md-4 marginTop">
                    <div><h2>1. upload</h2></div>
                    <div><p>Lorum ipson</p></div>
                </div>
                <div class="col-md-4 marginTop">
                    <div><h2>2. send</h2></div>
                    <div><p>Lorum ipson</p></div>
                </div>
                <div class="col-md-4 marginTop">
                    <div><h2>3. wait</h2></div>
                    <div><p>Lorum ipson</p></div>
                </div>
            </div>

        </div>

        <script>
            $("#file").fileinput({
                allowedFileExtensions: ["zip", "png"],
                elErrorContainer: "#errorBlock"
            });
        </script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    </body>
</html>
