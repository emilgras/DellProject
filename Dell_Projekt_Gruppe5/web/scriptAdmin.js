/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
// Denne metode giver hver række et unikt id startende med 1
            $('.tablerow').each(function (i) {
                $("td:first", this).html(i + 1);
            });

            // PendingPartnerAccept
            $("table tr #pendingPartners").on('click', function () {
                row = $(this).closest('td').parent()[0].sectionRowIndex;
                location.href = "AdminServlet?action=acceptpartner&id=" + row;
            });
            
            // PendingPartnersDecline
            $("table tr #declinePartner").on('click', function () {
                row = $(this).closest('td').parent()[0].sectionRowIndex;
                location.href = "AdminServlet?action=declinepartner&id=" + row;
            });

            // NewestCampaigns
            $("table tr #newestCampaigns").on('click', function () {
                row = $(this).closest('td').parent()[0].sectionRowIndex;
                location.href = "AdminServlet?action=viewNewestCampaignDetail&id=" + row;
            });

            // PendingCampaignsAccept
            $("table tr #pendingCampaignsAccept").on('click', function () {
                row = $(this).closest('td').parent()[0].sectionRowIndex;
                location.href = "AdminServlet?action=acceptcampaign&id=" + row;
            });
            
            // PendingCampaignsDeclineCampaign
            $("table tr #pendingCampaignsDeclineCampaign").on('click', function () {              
                row = $(this).closest('td').parent()[0].sectionRowIndex;
                location.href = "AdminServlet?action=declinecampaign&id=" + row;
            });
            
            // PendingCampaignsDeclinePoe
            $("table tr #pendingCampaignsDeclinePoe").on('click', function () {
                row = $(this).closest('td').parent()[0].sectionRowIndex;
                location.href = "AdminServlet?action=declinecampaignpoe&id=" + row;
            });
            
            // PendingCampaignsDetail
            $("table tr #pendingCampaignsDetail").on('click', function () {
                row = $(this).closest('td').parent()[0].sectionRowIndex;
                location.href = "AdminServlet?action=viewPendingCampaignsDetail&id=" + row;
            });
        });
            