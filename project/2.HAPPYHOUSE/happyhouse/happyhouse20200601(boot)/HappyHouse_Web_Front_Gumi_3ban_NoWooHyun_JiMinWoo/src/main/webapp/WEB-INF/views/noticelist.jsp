<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
		<h3>공지사항</h3>
		<br>
		<table class="table">
			<tr>
				<th>글번호</th>
				<th>제목</th>
				<th></th>
			</tr>
			<c:forEach var="notice" items="${noticeList}">
				<tr>
					<td>${notice.noticeno}</td>
					<td>${notice.subject}</td>
					<td><a href="<c:url value="noticedetail?no=${notice.noticeno}"/>">조회</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>