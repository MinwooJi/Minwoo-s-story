<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<%@ include file="/00.bs4_header.jsp" %>
		<div>
			<div class="container">
				<div class="mt-2 mb-2">
				
				
				</div>
			</div>
		</div>
		<div>
			<br>
			<h3>회원 정보 확인</h3>
			<br>
			<table class="table">
					<tr>
						<th>아이디*</th>
						<td>${loginMember.id}</td>
					</tr>
					<tr>
						<th>비밀번호*</th>
						<td>${loginMember.password}</td>
					</tr>
					<tr>
						<th>이름*</th>
						<td>${loginMember.name}</td>
					</tr>
					<tr>
						<th>주소*</th>
						<td>${loginMember.address}</td>
					</tr>
					<tr>
						<th>전화번호*</th>
						<td>${loginMember.phone}</td>
					</tr>
					<tr>
						<th></th>
						<td>
							<button type="button" onclick="location.href='<c:url value="/main.do"/>'">확인</button>
							<button type="button" onclick="location.href='<c:url value="/main.do?act=memberupdateform" />'">회원정보 수정</button>
							<button type="button" onclick="location.href='<c:url value="/main.do?act=deletemember" />'">회원 탈퇴</button>
						</td>
					</tr>
				</table>
		</div>
	</div>
</body>
</html>