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


    <!---------- Nav bar ---------->
    <div class="navbar navbar-inverse">
        <div class="container">
            <div class="navbar-header">
                <a href="PartnerServlet?action=index" class="navbar-brand">Dell Business</a>
            </div>
        </div>
    </div>

    <!---------- Middle content ---------->
    <div id ="wrapper">
    <div class="container contentContainer marginBottom">
        <h1 class="tileHeader">Signup</h1>
        <!--<div class="row center">  
         
        </div> -->
    </div>


    <form role="form">
        <div class="form-group">
            
            <label for="email">Company:</label>
            <input type="email" class="form-control" id="partnerName" placeholder="Enter your company name">
            </div> 
        
          
    </form>
    
   <div class="bfh-datepicker">
       <label for="date" >Campaign Start:</label>
       <input type="date" class="form-control" id="date">
       
</div> 
        
   <button type="submit" id="submitBtn" class="btn btn-default">Submit</button>  
</div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>






</html>

