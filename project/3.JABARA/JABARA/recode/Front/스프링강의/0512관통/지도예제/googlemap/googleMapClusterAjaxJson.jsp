<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no">
<meta charset="utf-8">
<title>Marker Clustering</title>
<style>
/* Always set the map height explicitly to define the size of the div
       * element that contains the map. */
#map {
	height: 100%;
}
/* Optional: Makes the sample page fill the window. */
html, body {
	height: 100%;
	margin: 0;
	padding: 0;
}
</style>
	
</head>
<body>
    <div id="map"></div>
    <script src="https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/markerclusterer.js"></script>
    <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA8U0aWeg8HDnas_vK5OX_9piAMAwH_yMU&callback=getMapData"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script>

    function initMap() {

		var map = new google.maps.Map(
			document.getElementById('map'), 
			{
        		zoom: 10,
        		center: {
        			lat: parseFloat(locations[0].lat), lng: parseFloat(locations[0].lng)
        		}
      		}
		);

		// Create an array of alphabetical characters used to label the markers.
		var labels = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';

		// Add some markers to the map.
		// Note: The code uses the JavaScript Array.prototype.map() method to
		// create an array of markers based on a given "locations" array.
		// The map() method here has nothing to do with the Google Maps API.
		var markers = locations.map(

			function(location, i) {
        		return new google.maps.Marker(
        				{
          					position: { lat: parseFloat(location.lat), lng: parseFloat(location.lng) },	// should be float, not string
          					label: labels[i % labels.length]
        				}
        		);
     		 }
		);

		//Add a marker clusterer to manage the markers.
		var markerCluster = new MarkerClusterer(
			map,
			markers,
			{
				imagePath: 'https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/m'
			}
		);
    }
    
	var locations;

  
	function getMapData(){
		
		$.ajax(
		{
	        type : 'get',
	        url : '<%= contextPath%>/GoogleMapServlet',
	        dataType : 'json',
	        success : function(data, status, xhr) {
	        	locations = data;
	        	
	        	console.log(locations);
	        	
	        	initMap();
	        }, 
	        error: function(jqXHR, textStatus, errorThrown) 
	        { 
	        	alertify.notify(
           			'Opps!! 글 Map data를 받는 과정에 문제가 생겼습니다.', 
           			'error', //'error','warning','message'
           			3, //-1
           			function(){
           				console.log(jqXHR.responseText); 
           			}
           		);
	        }
	    });
	}
    </script>
  </body>
</html>