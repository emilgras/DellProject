<%-- 
    Document   : dashboard_partner
    Created on : 06-04-2015, 19:58:50
    Author     : EmilGras
--%>
<%@page import="Model.DBFacade"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                    <a href="PartnerServlet?action=dashboard" class="navbar-brand">User Website</a>
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>        
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href=""><img src="img/home24.png"> Dashboard</a></li>                   
                        <li><a href="PartnerServlet?action=newcampaign"><img src="img/newcampaign24.png"> New campaign</a></li>
                    </ul>
                    <a href="LoginServlet?action=logout">
                        <button id="logoutBtn" type="submit" class="btn btn-warning navbar-right">Log out</button> 
                    </a>
                </div>
            </div>
        </div>  

        <div class="container contentContainer marginBottom">
            <h1 class="tileHeader">Dashboard</h1>
            <div class="row">  
                
                <h3 class="tileHeader"><span class="glyphicon glyphicon-off"></span> Start new campaign</h3>
                <div class="col-md-6 marginTop center">
                    <!-- <a href="PartnerServlet?action=newcampaign" id="textDecorationNone"> -->
                        <div class="tile">
                            <div>
                                <img src="img/addpartner.png">
                            </div>
                        </div>
                    <!-- </a> -->
                </div>


                <div class="col-md-6 marginTop center">
                    <h3 class="tileHeader"><span class="glyphicon glyphicon-export"></span> My campaigns</h3>
                    <div class="tile">
                        <!------------ NEWEST CAMPAIGNS ------------>
                        <div class="col-md-6 marginTop">
                            <div class="tile">                    
                                <table class="table table-striped table-bordered">
                                    <tr class="active"><th>Nr.</th><th>Company</th><th>Price DKK</th><th>Created</th><th>Status</th><th>Detail</th><th>Upload POE</th></tr>
                                            <c:forEach var="campaign" items="${newestCampaigns}" begin="0" end="4">
                                                <c:choose>
                                                    <c:when test="${campaign.status == 'POE pending'}">     
                                                <tr class="tablerow"><td></td><td>${campaign.navn}</td><td>${campaign.pris}</td><td>${campaign.oprettelse_dato}</td><td>${campaign.status}</td><td><input id="newestCampaigns" type="button" class="btn btn-info" value="View campaign"></td><td><input disabled="disabled" id="poe" type="button" class="btn btn-info" value="Upload picture"></td></tr>
                                                    </c:when> 
                                                    <c:otherwise>
                                                <tr class="tablerow"><td></td><td>${campaign.navn}</td><td>${campaign.pris}</td><td>${campaign.oprettelse_dato}</td><td>${campaign.status}</td><td><input id="newestCampaigns" type="button" class="btn btn-info" value="View campaign"></td><td><input id="poe" type="button" class="btn btn-info" value="Upload picture"></td></tr>
                                                    </c:otherwise>   
                                                </c:choose>
                                            </c:forEach>  
                                </table>  
                            </div>
                        </div>
                    </div>
                </div>
            </div>



            <!------------ PENDING CAMPAIGNS ------------>
            <!-- <div class="col-md-6 marginTop">
                <h3 class="tileHeader"><span class="glyphicon glyphicon-off"></span> Test</h3>
                <div class="tile">
                    <div>                     
                        <table class="table table-striped table-bordered">
                            <tr class="active"><th>Campaign start</th><th>Campaign end</th><th>Price estimate</th><th>Description:</th>
            <c:forEach var="campaign" items="${pendingCampaigns}">
        <tr><td>${campaign.start_dato} </td><td>${campaign.slut_dato} </td><td>${campaign.pris} </td><td>${campaign.beskrivelse} </td></tr>
            </c:forEach>
        </table> 
    </div>
</div>
</div> -->


        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <script type='text/javascript' src='scriptPartner.js'></script>
    </body>
</html>

