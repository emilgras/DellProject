<%-- 
    Document   : signup
    Created on : 06-04-2015, 15:17:56
    Author     : EmilGras
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="style.css" rel="stylesheet">
        <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker.min.css" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        
        <title>JSP Page</title>
    </head>
    <!-- Nav bar -->
    <div class="navbar navbar-inverse navbar-static-top">
        <div class="container">
            <div class="navbar-header">
                <a href="LoginServlet?action=loginPage" class="navbar-brand">Dell Business</a>
            </div>
        </div>
    </div>
    <!-- Middle content -->
    <div class="container" id="header">
        <h1 class="tileHeader">Signup</h1>
        <h3 style="color: indianred">${signupErrorMessage}</h3>
    </div>
    <div id ="wrapper">
        <form role="form" action="PartnerServlet" method="post">
            <div class="form-group">
                <label for="text">Username:</label>
                <input type="text" name="username" value="${partner.username}" class="form-control" id="usertxt">
            </div>

            <div class="form-group">
                <label for="pwd">Password:</label>
                <input type="password" name="password" class="form-control" id="pwd">
            </div> 

            <div class="form-group">
                <label for="conpwd">Confirm Password:</label>
                <input type="password" name="confirmpassword" class="form-control" id="conpwd">
            </div>

            <div class="form-group">
                <label for="text">Company:</label>
                <input type="text" name="company" value="${partner.name}" class="form-control" id="partnerName" placeholder="Enter your company name">
            </div>

            <div class="form-group">
                <label for="cvr">CVR:</label>
                <input type="text" name="cvr" value="${partner.cvr}" class="form-control" id="cvr" placeholder="Enter your CVR number">
            </div>

            <div class="form-group">
                <label for="country">Country:</label>
                <select class="selectpicker" name="country">
                    <option>Denmark</option>
                    <option>Norway</option>
                    <option>Sweden</option>
                    <option>Finland</option>
                    <option>Germany</option>
                    <option>England</option>
                </select>
            </div>

            <div class="form-group">
                <input type="hidden" name="action" value="partnerSignup">
                <input type="submit" id="submitBtn" class="btn btn-default">
            </div>
        </form>
    </div>

    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
    <script src="http://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.5.4/bootstrap-select.js"/>
</html>