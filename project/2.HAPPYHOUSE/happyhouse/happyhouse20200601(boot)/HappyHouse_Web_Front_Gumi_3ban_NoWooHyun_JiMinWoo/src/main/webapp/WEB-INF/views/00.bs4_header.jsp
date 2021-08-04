<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<style>
		.mp {
		  	 list-style:none;
		}
		.mp li {
			padding: 5px;
		}
	</style>

		<nav id="index_nav_login">
			<c:choose>
				<c:when test="${empty loginMember}">
					<div id="usercontroller">
					
					</div>
				</c:when>
				<c:otherwise>				
						<ul class="mp">
							<li class="float-left"><a>${loginMember.id}(${loginMember.name})님 로그인 되었습니다.</a></li>
							<li class="float-right"><a href="logout">로그아웃</a></li>
							<li class="float-right"><a href="memberinfo">회원정보</a></li>
						</ul>
				</c:otherwise>
			</c:choose>
		</nav>
		<header id="index_header" class="jumbotron text-center mb-1">
			<h4>행복한 우리 집</h4> 
		</header>
		<!-- nav start -->
		<nav class="navbar navbar-expand-sm bg-dark navbar-dark rounded">
			<ul class="navbar-nav">
				<li class="nav-item">
					<a class="nav-link" href="<c:url value="/main"/>">Home</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="<c:url value="/list"/>">List</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="<c:url value="/noticelist"/>">Notice</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="<c:url value="/qnaindex"/>">QnA</a>
				</li>
			</ul>
		</nav>
		<script>
		function init(){
			$("#usercontroller").html(`
				<ul class="mp">
					<li class="float-right"><a class="p-1" href="signupform">SignUp</a></li>
					<li class="float-right"><a class="p-1" href="javascript:login()">Login</a></li>
				</ul>	
			`);
		}
		function login(){
			$("#usercontroller").append(`
				<div>
					<form method="post" action="login">
						<div>
							<c:out value="${errMsg}" />
							<div>
								<label>아이디</label> <input type="text" name="id" />
							</div>
							<div>
								<label>암호</label> <input type="password" name="password" />
							</div>
							<div>
								<button>로그인</button>
								<input type="button" value="비밀번호 찾기" onClick="location.href='findpwdform'" class="btn btn-primary">
							</div>
						</div>
					</form>
				</div>
			`);
		}
		init();
		</script>
		<!-- nav end -->
