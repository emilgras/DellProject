


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
        <section>
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
                        <a href="LoginServlet?action=logout">
                            <button id="logoutBtn" type="submit" class="btn btn-warning navbar-right">Log out</button>
                        </a>
                    </div>
                </div>
            </div> 
        </section>


        <section class="row">
            <div class="container  marginBottom"> 
                <h1 class="tileHeader">Dashboard</h1>

                <!------------ PENDING PARTNERS ------------>
                <div class="col-md-6 marginTop">
                    <h3 class="tileHeader"><span class="glyphicon glyphicon-off"></span> Pending applicants</h3>
                    <div class="tile">
                        <div>
                            <table class="table table-striped table-bordered">
                                <tr class="active"><th>Company</th><th>CVR</th><th>Country</th><th>Accept</th><th>Decline</th></tr>
                                        <c:forEach var="partner" items="${pendingPartners}">
                                    <tr><td>${partner.name} </td><td>${partner.cvr} </td><td>${partner.country}</td><td><button type="button"  id="pendingPartners" class="btn btn-success btn-sm"><span class="glyphicon glyphicon-ok"></span></button></td><td><button type="button" class="btn btn-danger btn-danger"><span class="glyphicon glyphicon-remove"></span></button></td></tr>
                                            </c:forEach>
                            </table> 
                        </div>
                    </div>
                </div>

                <!------------ NEWEST CAMPAIGNS ------------>
                <div class="col-md-6 marginTop">
                    <a href="AdminServlet?action=campaigns" id="textDecorationNone"><h3 class="tileHeader"><span class="glyphicon glyphicon-export"></span> Newest campaigns</h3> </a>
                    <div class="tile">
                        <div>
                            <table class="table table-striped table-bordered">
                                <tr class="active"><th>Nr.</th><th>Company</th><th>Price DKK</th><th>Created</th><th>Status</th><th>Detail</th></tr>
                                        <c:forEach var="campaign" items="${newestCampaigns}" begin="0" end="4">
                                    <tr class="tablerow"><td></td><td>${campaign.navn}</td><td>${campaign.pris}</td><td>${campaign.oprettelse_dato}</td><td>${campaign.status}</td><td><input id="newestCampaigns" type="button" class="btn btn-info" value="View campaign"></td></tr>
                                            
                                        </c:forEach>
                            </table> 
                            
                            
                        </div>
                    </div>
                </div>

                <!------------ PENDING CAMPAIGNS ------------>
                <div class="col-md-6 marginTop">
                    <h3 class="tileHeader"><span class="glyphicon glyphicon-off"></span> Pending campaigns</h3>
                    <div class="tile">
                        <div>                     
                            <table class="table table-striped table-bordered">
                                <tr class="active"><th>Company</th><th>CVR</th><th>Status</th><th>Accept</th><th>Decline</th></tr>
                                        <c:forEach var="campaign" items="${pendingCampaigns}">
                                    
                                    
                                    <tr><td>${campaign.navn} </td><td>${campaign.cvr} </td><td>${campaign.status} </td><td><button type="button"  id="pendingCampaigns" class="btn btn-success btn-sm"><span class="glyphicon glyphicon-ok"></span></button></td><td><button type="button" class="btn btn-danger btn-danger"><span class="glyphicon glyphicon-remove"></span></button></td></tr>
                                            </c:forEach>
                            </table> 

                        </div>
                    </div>
                </div>
            </div>
        </section>


        <section class="row statsSection">
            <div class="container marginBottom">
                <div class="col-md-12 stats">
                    <h3 class="tileHeader"><span class="glyphicon glyphicon-download"></span> Statistics</h3>

                    <div class="col-md-3 tile center">
                        <h3 class="whiteText">Total budget:</h3>
                        <h1 class="whiteText">455.879 dkk</h1>
                    </div>
                    <div class="col-md-3 tile center">
                        <h3 class="whiteText">Total campaigns:</h3>
                        <h1 class="whiteText">121.110 dkk</h1>
                    </div>
                    <div class="col-md-3 tile center">
                        <h3 class="whiteText">Total partners:</h3>
                        <h1 class="whiteText">32</h1>
                    </div>
                    <div class="col-md-3 tile center">
                        <h3 class="whiteText">Operating countries:</h3>
                        <h1 class="whiteText">6</h1>
                    </div>
                </div>
            </div>
        </section>


        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <script>
            // Denne metode giver hver række et unikt id startende med 1
            $('.tablerow').each(function (i) {
                $("td:first", this).html(i + 1);
            });

            // PendingPartners
            $("table tr #pendingPartners").on('click', function () {
                row = $(this).closest('td').parent()[0].sectionRowIndex;
                alert(row);
                location.href = "AdminServlet?action=acceptpartner&id=" + row;
            });

            // NewestCampaigns
            $("table tr #newestCampaigns").on('click', function () {
                row = $(this).closest('td').parent()[0].sectionRowIndex;
                location.href = "AdminServlet?action=viewCampaign&id=" + row;
            });

            // PendingCampaigns
            $("table tr #pendingCampaigns").on('click', function () {
                row = $(this).closest('td').parent()[0].sectionRowIndex;
                location.href = "AdminServlet?action=acceptcampaign&id=" + row;
            });



        </script>
    </body>
</html>

