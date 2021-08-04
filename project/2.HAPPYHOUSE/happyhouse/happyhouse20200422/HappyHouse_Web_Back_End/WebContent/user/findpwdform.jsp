<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기 페이지</title>
</head>
<body>
	<div>
		<h2>비밀번호 찾기</h2>
		<form method="post" action="<c:url value="/member.do"/>">
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
</body>
</html>