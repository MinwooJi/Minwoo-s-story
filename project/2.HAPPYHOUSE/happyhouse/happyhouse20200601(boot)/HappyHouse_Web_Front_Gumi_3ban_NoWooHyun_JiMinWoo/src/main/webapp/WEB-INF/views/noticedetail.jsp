<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>거래정보 - 상세정보</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<%@ include file="/WEB-INF/views/00.bs4_header.jsp" %>
		<div>
			<div class="container">
				<div class="mt-2 mb-2">
				
				
				</div>
			</div>
		</div>
		<br>
		<h3>공지사항 보기</h3>
		<br>
		<table class="table">
			<tr>
				<th>제목</th>
				<td>${notice.subject}</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${notice.adminid}</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>${notice.content}</td>
			</tr>
		</table>
		
		<div>
			<a href="noticelist">확인</a>
		</div>
	</div>
</body>
</html>