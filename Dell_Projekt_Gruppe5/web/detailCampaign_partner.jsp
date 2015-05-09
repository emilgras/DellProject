<%-- 
    Document   : campaigns
    Created on : 04-04-2015, 16:33:25
    Author     : EmilGras
--%>

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

        <div class="container"> 

            <!-------- Details --------> 
            <div class="row marginBottom">
                <div><a href="PartnerServlet?action=dashboard"><< Tilbage</a></div>
                <div><h1 class="tileHeader">Campaign detail</h1></div>     
                <div class="col-md-0 marginTop">           
                    <div class="col-md-8">
                        <h3>Company: ${campaignDetail.navn}</h3>
                        <h3>cvr: ${campaignDetail.cvr}</h3>
                    </div>
                    <div class="trackbox">
                        <div class="center">
                            <h2>Tracking</h2>
                        </div>
                        <h4>Status: ${campaignDetail.status}</h4>
                    </div>
                    <div class="fleft col-md-8">
                        <h3>Describtion: ${campaignDetail.beskrivelse}</h3>
                        <h3>Pris: ${campaignDetail.pris}</h3>
                        <h3>Expected start: ${campaignDetail.start_dato}</h3>
                        <h3>Expected end: ${campaignDetail.slut_dato}</h3>
                        <h3>Created: ${campaignDetail.oprettelse_dato}</h3>
                        <h3>Id: ${campaignDetail.kno}</h3>
                    </div>


                </div>
            </div>

            <!-------- POE -------->        
            <div class="row marginTop marginBottom">
                <c:if test="${campaignDetail.status == 'POE Pending' || campaignDetail.status == 'POE Accepted' || campaignDetail.status == 'Invoice Pending' || campaignDetail.status == 'Complete'}">
                    <div class="col-lg-12">
                        <div class="col-lg-12">
                            <h3 class="tileHeader">Campaign Pictures</h3>
                        </div>

                        <ul>
                            <c:forEach var="img" items="${poe.imageFiles}">
                                <li class="col-lg-3 col-md-4 col-xs-6 thumb thumbnail" id="list">                  
                                    <img class="img-responsive thumbnail" id="image" src="uploads/${img.fileName}" alt="">                            
                                </li> 
                            </c:forEach>
                        </ul>
                    </c:if>                                                               
                </div>
            </div>
        </div>

        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">         
                    <div class="modal-body">                
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <script>
            $("table tr #campaign").on('click', function () {
                row = $(this).closest('td').parent()[0].sectionRowIndex;
                location.href = "AdminServlet?action=viewcampaign&id=" + row;
            });

            $(document).ready(function () {
                $('li img').on('click', function () {
                    var src = $(this).attr('src');
                    var img = '<img src="' + src + '" class="img-responsive"/>';

                    //start of new code new code
                    var index = $(this).parent('li').index();

                    var html = '';
                    html += img;
                    html += '<div style="height:25px;clear:both;display:block;">';
                    html += '<a class="controls next" href="' + (index + 2) + '">next &raquo;</a>';
                    html += '<a class="controls previous" href="' + (index) + '">&laquo; prev</a>';
                    html += '</div>';

                    $('#myModal').modal();
                    $('#myModal').on('shown.bs.modal', function () {
                        $('#myModal .modal-body').html(html);
                        //new code
                        $('a.controls').trigger('click');
                    })
                    $('#myModal').on('hidden.bs.modal', function () {
                        $('#myModal .modal-body').html('');
                    });




                });
            })




            $(document).on('click', 'a.controls', function () {
                var index = $(this).attr('href');
                var src = $('ul.row li:nth-child(' + index + ') img').attr('src');

                $('.modal-body img').attr('src', src);

                var newPrevIndex = parseInt(index) - 1;
                var newNextIndex = parseInt(newPrevIndex) + 2;

                if ($(this).hasClass('previous')) {
                    $(this).attr('href', newPrevIndex);
                    $('a.next').attr('href', newNextIndex);
                } else {
                    $(this).attr('href', newNextIndex);
                    $('a.previous').attr('href', newPrevIndex);
                }

                var total = $('ul.row li').length + 1;
                //hide next button
                if (total === newNextIndex) {
                    $('a.next').hide();
                } else {
                    $('a.next').show()
                }
                //hide previous button
                if (newPrevIndex === 0) {
                    $('a.previous').hide();
                } else {
                    $('a.previous').show()
                }


                return false;
            });
        </script>
    </body>
</html>