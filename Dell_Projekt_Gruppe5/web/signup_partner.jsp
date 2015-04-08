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
        <title>JSP Page</title>
    </head>
    <!-- Nav bar -->
    <div class="navbar navbar-inverse">
        <div class="container">
            <div class="navbar-header">
                <a href="PartnerServlet?action=index" class="navbar-brand">Dell Business</a>
            </div>
        </div>
    </div>
    <!-- Middle content -->
    <div class="container" id="header">
        <h1 class="tileHeader">Signup</h1>
    </div>
    <div id ="wrapper">
        <div class="form-group">
            <label for="text">Username:</label>
            <input type="text" class="form-control" id="usertxt">
            <input type="hidden" name="signup_action" value="signupUsername">
        </div>
        <div class="form-group">
            <label for="pwd">Password:</label>
            <input type="password" class="form-control" id="pwd">
            <input type="hidden" name="signup_action" value="signupPassword">
        </div> 
        <div class="form-group">
            <label for="conpwd">Confirm Password:</label>
            <input type="password" class="form-control" id="conpwd">
            <input type="hidden" name="signup_action" value="signupConfirmPassword">
        </div>
        <form role="form">
            <div class="form-group">
                <label for="text">Company:</label>
                <input type="text" class="form-control" id="partnerName" placeholder="Enter your company name">
                <input type="hidden" name="signup_action" value="signupCompany">
            </div>
        </form>
        <div class="form-group">
            <label for="cvr">CVR:</label>
            <input type="text" class="form-control" id="cvr" placeholder="Enter your CVR number">
            <input type="hidden" name="signup_action" value="signupCVR">
        </div>
        <button type="submit" id="submitBtn" class="btn btn-default">Submit</button> 
    </div>
    <script src="https://ajax.googleapis.com/…/l…/jquery/1.11.2/jquery.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
</html>