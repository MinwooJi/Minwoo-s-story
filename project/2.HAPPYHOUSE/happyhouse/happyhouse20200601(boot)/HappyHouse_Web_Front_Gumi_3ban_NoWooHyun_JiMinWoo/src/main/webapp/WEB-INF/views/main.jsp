<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>HappyHouse</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<%-- 	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/00.bs4.css" /> --%>
</head>
<body>
	<div class="container">
		<%@ include file="/WEB-INF/views/00.bs4_header.jsp" %>
		<section>
		<div>
			<div class="container">
				<div class="mt-2 mb-2">
				
				
				</div>
			</div>
		</div>
		</section>
		<section id="index_section">
			<div class="card col-sm-12 mt-1" style="min-height: 850px;">
				<div class="card-body">

<script>
let colorArr = ['table-primary','table-success','table-danger'];
var locations;
$(document).ready(function(){
	$.get("sido"
		,function(data, status){
			$.each(data, function(index, vo) {
				$("#sido").append("<option value='"+vo.sido_code+"'>"+vo.sido_name+"</option>");
			});//each
		}//function
		, "json"
	);//get
});//ready
$(document).ready(function(){
	$("#sido").change(function() {
		$.get("gugun"
				, {sido:$("#sido").val()}
				,function(data, status){
					$("#gugun").empty();
					$("#gugun").append('<option value="0">선택</option>');
					$.each(data, function(index, vo) {
						$("#gugun").append("<option value='"+vo.gugun_code+"'>"+vo.gugun_name+"</option>");
					});//each
				}//function
				, "json"
		);//get
	});//change
	$("#gugun").change(function() {
		$.get("dong"
				, {gugun:$("#gugun").val()}
				,function(data, status){
					$("#dong").empty();
					$("#dong").append('<option value="0">선택</option>');
					$.each(data, function(index, vo) {
						$("#dong").append("<option value='"+vo.dong+"'>"+vo.dong+"</option>");
					});//each
				}//function
				, "json"
		);//get
	});//change
	$("#dong").change(function() {
		$.get("apt"
				, {dong:$("#dong").val()}
				,function(data, status){
					$("#searchResult").empty();
					$("#idx>tbody").empty();
					clearMarkers();
					$.each(data, function(index, vo) {
						let str = "<tr class="+colorArr[index%3]+">"
						+ "<td>" + vo.no + "</td>"
						+ "<td>" + vo.dong + "</td>"
						+ "<td>" + vo.aptName + "</td>"
						+ "<td>" + vo.jibun + "</td>"
						+ "<td>" + vo.code
						+ "</td><td id='lat_"+index+"'></td><td id='lng_"+index+"'></td></tr>";
						$("#idx>tbody").append(str);
						$("#searchResult").append(vo.dong+" "+vo.AptName+" "+vo.jibun+"<br>");
					});//each
					console.dir(data);
					geocode(data);
				}//function
				, "json"
		);//get
	});//change
});//ready
function geocode(jsonData) {
	let idx = 0;
	let tmpLat;
	let tmpLng;
	$.each(jsonData, function(index, vo) {
		$.get("https://maps.googleapis.com/maps/api/geocode/json"
				,{	key:'AIzaSyCm_hAb-P76X7Qkeh9y2-FOujkq_Z6JDDs'
					, address:vo.dong+"+"+vo.AptName+"+"+vo.jibun
				}
				, function(data, status) {
					console.dir(data);
// 					alert(data.results[0].geometry.location.lat);
					tmpLat = data.results[0].geometry.location.lat;
					tmpLng = data.results[0].geometry.location.lng;
					if(idx === 0){
						map.setCenter({lat:tmpLat, lng:tmpLng});
					}
					$("#lat_"+index).text(tmpLat);
					$("#lng_"+index).text(tmpLng);
					addMarker(tmpLat, tmpLng, vo.aptName);
					idx++;
				}
				, "json"
		);//get
	});//each
}
</script>
시도 : <select id="sido">
	<option value="0">선택</option>
</select>
구군 : <select id="gugun">
	<option value="0">선택</option>
</select>
읍면동 : <select id="dong">
	<option value="0">선택</option>
</select>
<table id="idx"class="table mt-2">
	<thead>
		<tr>
			<th>번호</th>
			<th>법정동</th>
			<th>아파트이름</th>
			<th>지번</th>
			<th>지역코드</th>
			<th>위도</th>
			<th>경도</th>
		</tr>
	</thead>
	<tbody>
	</tbody>
</table>
<!-- here end -->
<!-- map start -->
<div id="map" style="width: 100%; height: 500px; margin: auto;"></div>
<script src="https://unpkg.com/@google/markerclustererplus@4.0.1/dist/markerclustererplus.min.js"></script>
<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCm_hAb-P76X7Qkeh9y2-FOujkq_Z6JDDs&callback=initMap"></script>
<script>
	var seoul = {lat: 37.551256, lng: 126.988262};
	var map;
	var markers = [];
	function initMap() {
		console.dir()
		map = new google.maps.Map(document.getElementById('map'), {
			center: seoul, zoom: 14
		});
	}
	function addMarker(tmpLat, tmpLng, aptName) {
		var marker = new google.maps.Marker({
			position: new google.maps.LatLng(parseFloat(tmpLat),parseFloat(tmpLng)),
			label: aptName,
			title: aptName
		});
		marker.addListener('click', function() {
			map.setZoom(20);
			map.setCenter(marker.getPosition());
			callHouseDealInfo(aptName);
		});
		marker.setMap(map);
		markers.push(marker);
	}
	function callHouseDealInfo(AptName) {
		console.dir(AptName);
		console.dir("확인");
		$.ajax({
   			url:"selectByName",
   			dataType:"json",
   			data:{AptName:AptName},
   			success:function(deal){
   				let str = "<thead><tr><th>번호</th><th>법정동</th><th>아파트이름</th><th>코드</th><th>거래가격</th><th>준공년도</th><th>면적</th><th>층</th></tr></thead>"
						+ "<tbody>";
   				deal.forEach(function(vo){
   						str +=
   						"<tr class='table-primary'>"
						+ "<td>"+vo.no+"</td>"
						+ "<td>"+vo.dong+"</td>"
						+ "<td>"+vo.aptName+"</td>"
		   				+ "<td>"+vo.code+"</td>"
		   				+ "<td>"+vo.dealAmount+"</td>"
		   				+ "<td>"+vo.buildYear+"</td>"
		   				+ "<td>"+vo.area+"</td>"
		   				+ "<td>"+vo.floor+"</td>"
		   				+ "</tr>";
   					});
   				str += "</tbody>";
		   		console.dir(str);
   				$("#housedeal").html(str);
			}
   		});
	}
	function clearMarkers(){
		for (var i = 0; i < markers.length; i++) {
			markers[i].setMap(null);
		}
		markers = [];
	}
</script>
<!-- map end -->
<div>
	<h3>관심 아파트 상세 정보</h3>
</div>
<table class="table mt-2" id="housedeal">
	<thead>
		<tr>
			<th>번호</th><th>법정동</th><th>아파트이름</th><th>코드</th><th>거래가격</th><th>준공년도</th><th>면적</th><th>층</th>
		</tr>
	</thead>
</table>

				</div>
			</div>
		</section>
		<!-- section end -->
<!-- 		<footer class="col-ms-12 bg-dark rounded mt-1"></footer> -->
	</div>
</body>
</html>
