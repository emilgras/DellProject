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
        <div class="navbar navbar-inverse navbar-static-top">
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
                        <li><a href="PartnerServlet?action=newcampaign"><img src="img/newcampaign24.png"> New campaign</a></li>
                    </ul>
                    <a href="LoginServlet?action=logout">
                        <button id="logoutBtn" type="submit" class="btn btn-warning navbar-right">Log out</button> 
                    </a>
                </div>
            </div>
        </div>  

        <div class="container contentContainer marginBottom">
            <h1 class="tileHeader marginBottom">Upload POE</h1>
            <h4>Create your Proof of Execution</h4>
            <h3 style="color: indianred">${uploadErrorMessage}</h3>
            <!------------ Upload file content ------------>
            <div class="row">
                <div class="col-md-0 marginTop">

                    <form action="PartnerServlet?action=sendPoe" method="post" enctype="multipart/form-data">
                        <div class="form-group">
                            <input id="file" type="file" class="file" name="files" multiple>
                            <div id="errorBlock" class="help-block"></div>
                        </div>
                    </form>
                    <h3 style="color: #ffa500">OBS: Once you press upload, you can't add or remove files to the Poe.</h3>
                </div>
            </div>

            <!------------ Description content ------------>
            <div class="row center marginTop">
                <div class="col-md-4 marginTop">
                    <div><h2>1. Upload</h2></div>
                    <div><p>Choose the relevant campaign proof of execution files from your computer.</p></div>
                </div>
                <div class="col-md-4 marginTop">
                    <div><h2>2. Send</h2></div>
                    <div><p>When all your files are chosen, press the Upload button to send them to Dell</p></div>
                </div>
                <div class="col-md-4 marginTop">
                    <div><h2>3. Wait</h2></div>
                    <div><p>Within 72 hours Dell will review your work and send back a response to your POE</p></div>
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
