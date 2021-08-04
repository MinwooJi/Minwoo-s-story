<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
		<nav id="index_nav_login">
			<ul class="m-0 p-0">
				<li class="float-right"><a class="p-1" href="#">SignUp</a></li>
				<li class="float-right"><a class="p-1" href="#">Login</a></li>
			</ul>
		</nav>
		<header id="index_header" class="jumbotron text-center mb-1">
			<h4>행복한 우리 집</h4> 
		</header>
		<!-- nav start -->
		<nav class="navbar navbar-expand-sm bg-dark navbar-dark rounded">
			<ul class="navbar-nav">
				<li class="nav-item">
					<a class="nav-link" href="#">Home</a>
				</li>
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
						동네 정보
					</a>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="#">APT 매매</a>
						<a class="dropdown-item" href="#">APT 전월세</a>
						<a class="dropdown-item" href="#">주택 매매</a>
						<a class="dropdown-item" href="#">주택 전월세</a>
						<a class="dropdown-item" href="#">상권 정보</a>
						<a class="dropdown-item" href="#">환경 정보</a>
					</div>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="#">Notice</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="#">News</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="#">Contact</a>
				</li>
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
						AJAX
					</a>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="${pageContext.request.contextPath}/live7/g_map1.jsp">MAP1</a>
						<a class="dropdown-item" href="${pageContext.request.contextPath}/live7/g_map2.jsp">MAP2</a>
						<a class="dropdown-item" href="${pageContext.request.contextPath}/live7/g_map3.jsp">MAP3</a>
						<a class="dropdown-item" href="${pageContext.request.contextPath}/live7/g_map4.jsp">MAP4</a>
						<a class="dropdown-item" href="${pageContext.request.contextPath}/live7/g_map5.jsp">MAP5</a>
						<a class="dropdown-item" href="${pageContext.request.contextPath}/live7/g_map6.jsp">MAP6</a>
					</div>
				</li>
			</ul>
		</nav>
		<!-- nav end -->
