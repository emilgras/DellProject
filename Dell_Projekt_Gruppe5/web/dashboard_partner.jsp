<%-- 
    Document   : dashboard_partner
    Created on : 06-04-2015, 19:58:50
    Author     : EmilGras
    Emil & Anders har arbejdet på denne jsp
--%>
<%@page import="Model.DBFacade"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Dell | Dashboard</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="style.css" rel="stylesheet">
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
                        <li class="active"><a href=""><img src="img/home24.png"> Dashboard</a></li>                   
                        <li><a href="PartnerServlet?action=newcampaign"><img src="img/newcampaign24.png"> New campaign</a></li>
                    </ul>
                    <a href="LoginServlet?action=logout">
                        <button id="logoutBtn" type="submit" class="btn btn-warning navbar-right">Log out</button> 
                    </a>
                </div>
            </div>
        </div>  

        <section class="row">
            <div class="container marginBottom">
                <h1 class="tileHeader marginBottom">Dashboard</h1>
                <h4 style="color: #ffa500">${message}</h4>

                <!------------ NEWEST CAMPAIGNS ------------>
                <div class="col-md-0 marginTop">
                    <h3 class="tileHeader"><span class="glyphicon glyphicon-list"></span> My campaigns</h3>
                    <div class="tile">                    
                        <table class="table table-striped table-bordered">
                            <tr class="active"><th>Nr.</th><th>Company</th><th>Price EUR</th><th>Created</th><th>Status</th><th>Detail</th><th>Next step</th></tr>
                                    <c:forEach var="campaign" items="${pCam}">
                                <tr class="tablerow">
                                    <td></td>
                                    <td>${campaign.name}</td>
                                    <td>${campaign.price}</td>
                                    <td>${campaign.created_date}</td>
                                    <td>${campaign.status}</td>
                                    <td><input id="viewDetail" type="button" class="btn btn-info" value="View campaign"></td>
                                    <td><c:if test="${campaign.status == 'Pending'}"><input disabled='disabled' type="button" class="btn btn-info" value="Campaign sent"></c:if>
                                        <c:if test="${campaign.status == 'In-Progress' || campaign.status == 'POE Declined'}" ><input id="uploadPoe" type="button" class="btn btn-info" value="Send poe"></c:if>
                                        <c:if test="${campaign.status == 'POE Pending'}"><input disabled='disabled' type="button" class="btn btn-info" value="Poe sent"></c:if>
                                        <c:if test="${campaign.status == 'POE Accepted'}"><input id="uploadInvoice" type="button" class="btn btn-info" value="Send invoice"></c:if>
                                        <c:if test="${campaign.status == 'Invoice Pending'}"><input disabled='disabled' type="button" class="btn btn-info" value="Invoice sent"></c:if>
                                        <c:if test="${campaign.status == 'Complete'}"><input disabled='disabled' type="button" class="btn btn-info" value="Complete"></c:if>
                                        </td>
                                    </tr>                                       
                            </c:forEach>  
                        </table>  
                    </div>
                </div>
            </div>

        </section>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <script src="scriptPartner.js"></script>

    </body>
</html>

