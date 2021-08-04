<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>g_map3</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/live7/00.bs4.css" />
</head>
<body>
	<div class="container">
		<%@ include file="/live7/00.bs4_header.jsp" %>
		<!-- ======= Breadcrumbs ======= -->
		<section>
		<div>
			<div class="container">
				<div class="mt-2 mb-2">
					<h2>g_map3</h2>
				</div>
			</div>
		</div>
		</section>
		<!-- End Breadcrumbs -->
		<!-- section start -->
		<section id="index_section">
			<div class="card col-sm-12 mt-1" style="min-height: 450px;">
				<div class="card-body">

<!-- here start -->
<script>
	$(document).ready(function() {
		$.get("https://maps.googleapis.com/maps/api/geocode/json"
				,{	key:'AIzaSyC3Jh6Rt72qHXe5GomCfP_4LAuHjs_sr0U'
					, address:'명륜1가+송림아마레스아파트'
				}
				, function(data, status) {//alert(data.results[0].geometry.location.lat);
					for (let i = 0; i < data.results.length; i++) {
						$("#idCheckResult").append(data.results[i].geometry.location.lat+"<br>");
						$("#idCheckResult").append(data.results[i].geometry.location.lng+"<br>");
					}
				}
				, "json"
		);//get
	});//ready
</script>
<p id="idCheckResult"></p>
<!-- here end -->

				</div>
			</div>
		</section>
		<!-- section end -->
		<footer class="col-ms-12 bg-dark rounded mt-1"></footer>
	</div>
</body>
</html>
