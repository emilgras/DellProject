<%-- 
    Document   : adminlogin
    Created on : 06-04-2015, 15:29:24
    Author     : EmilGras
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="style.css" rel="stylesheet">
        <title>JSP Page</title>
    </head>
    <body>  
        
        
        <!---------- Nav bar ---------->
        <div class="navbar navbar-inverse">
            <div class="container">
                <div class="navbar-header">
                    <a href="AdminServlet?action=login" class="navbar-brand">Dell Business Admin</a>
                </div>
            </div>
        </div>
        
        <!---------- Middle content ---------->
        <div class="container contentContainer">
            <div class="row">
                <div class="col-md-4 col-md-offset-4" id="topRow">
                    <img src="img/logo128px.png">
                    <p class="lead">The power to do more</p>     
                    
                    <!---------- Log in ---------->
                    <form class="marginTop" action="AdminServlet" method="POST">
                        <input type="hidden" name="action" value="adminLogin">
                        <div class="input-group-lg ">
                            <input type="text" name="username" class="form-control marginTop" placeholder="Your username" />
                        </div>
                        <div class="input-group-lg ">
                            <input type="password" name="password" class="form-control marginTop" placeholder="Your password" />
                        </div>
                        <div class="input-group-lg ">
                            <input type="submit" value="Log in" class="btn btn-success btn-lg marginTop btn-block" />
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
    </body>
</html>

