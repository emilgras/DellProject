/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * Anders og Emil har arbejdet med dette Script
 */
$(document).ready(function () {
    // Denne metode giver hver række et unikt id startende med 1
    


    $('.tablerow').each(function (i) {
        $("td:first", this).html(i + 1);
    });

    $("table tr #viewDetail").on('click', function () {
        row = $(this).closest('td').parent()[0].sectionRowIndex;
        location.href = "PartnerServlet?action=viewDetail&id=" + row;
    });

    $("table tr #uploadPoe").on('click', function () {
        row = $(this).closest('td').parent()[0].sectionRowIndex;
        location.href = "PartnerServlet?action=selectedCampaignForPoeUpload&id=" + row;
    });

    $("table tr #uploadInvoice").on('click', function () {
        row = $(this).closest('td').parent()[0].sectionRowIndex;
        location.href = "PartnerServlet?action=selectedCampaignForInvoiceUpload&id=" + row;
    });





});



