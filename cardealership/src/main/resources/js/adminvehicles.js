var rootPath = '@Url.Content("~")';

$(document).ready( function() {
    loadAllVehicles();
});

function loadAllVehicles() {
    $.ajax({
       type: "GET",
       url: rootPath + "admin/vehicles",
       success: function(vehicleArray) {
           var resultsDiv = $("#searchResultsDiv");
           
           $.each(vehicleArray, function(index, vehicle){
               var title = vehicle.CarYear+" "+vehicle.Make+" "+vehicle.Model;
               
               var listingCard = $("<div>").attr({class: "card"});
               
               listingCard.append( $('<div>').attr({class: "card-header"}).append( $("<h5>").val(title) ) );
           });
       }
    });
}