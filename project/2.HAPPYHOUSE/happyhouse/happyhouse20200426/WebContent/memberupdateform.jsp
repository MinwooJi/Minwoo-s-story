<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정</title>
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
			<h3>회원 정보 수정</h3>
			<br>
			<form method="post" action="<c:url value="/main.do"/>">
				<input type="hidden" name="act" value="updatemember" />
				<table class="table">
					<tr>
						<th>아이디*</th>
						<td><input type="text" name="id" value="${loginMember.id}" readonly/></td>
					</tr>
					<tr>
						<th>비밀번호*</th>
						<td><input type="password" name="password" value="${loginMember.password}"/></td>
					</tr>
					<tr>
						<th>이름*</th>
						<td><input type="text" name="name" value="${loginMember.name}"/></td>
					</tr>
					<tr>
						<th>주소*</th>
						<td><input type="text" name="address1" value="${loginMember.address}"/></td>
					</tr>
					<tr>
						<th>전화번호*</th>
						<td><input type="text" name="phone" value="${loginMember.phone}"/></td>
					</tr>
					<tr>
						<th></th>
						<td><button type="submit">수정</button></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>