<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 변경 페이지</title>
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
			<div>${errMsg}</div>
			<br>
			<form method="post" action="<c:url value="/main.do"/>">
			<input type="hidden" name="act" value="setnewpwd">
					<div>
						<label>새로운 비밀번호</label>
						<input type="password" name="pw1"/>
					</div>
					<div>
						<label>비밀번호 확인</label>
						<input type="password" name="pw2"/>
					</div>
					<div>
						<button>비밀번호 설정</button>
					</div>
			</form>
		</div>
	</div>
</body>
</html>