<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 목록 페이지</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div style="text-align: right">
		${loginMember.id}(${loginMember.name})님 로그인 되었습니다.
		<a href="<c:url value="/member.do?act=logout"/>">로그아웃</a>
	</div>
	<div style="text-align: center">
		<br>
		<h2>회원 목록</h2>
	</div>
	<div class="container">
		<table class="table table-bordered">
			<c:forEach var="member" items="${list}">
			<tr>
				<td>회원 아이디 </td>
				<td>${member.id}</td>
			</tr>
			</c:forEach>
		</table>
	</div>
	<div style="text-align: center">
		<a href="<c:url value="/main.do"/>">홈화면으로 이동</a>
	</div>
</body>
</html>