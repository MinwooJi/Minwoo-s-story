<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기 페이지</title>
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
			<h3>비밀번호 찾기</h3>
			<br>
			<form method="post" action="<c:url value="/main.do"/>">
				<input type="hidden" name="act" value="findpwd">
				<div>아이디/이름/전화번호를 입력해 주세요</div>
				<div>
					<label>아이디 :</label>
					<input type = "text" name="id">			
				</div>
				<div>
					<label>이름 :</label>
					<input type = "text" name="name">			
				</div>
				<div>
					<label>전화번호 :</label>
					<input type = "text" name="phoneNum">			
				</div>
				<div>
					<button>비밀번호 찾기</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>