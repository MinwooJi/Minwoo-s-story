<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 수정 페이지</title>
</head>
<body>
	<div>
		<h2>내정보 수정</h2>
	</div>
	<div>
		<form method="post" action="<c:url value="/member.do"/>">
		<input type="hidden" name ="act" value="update">
		<div>
			<table>
			<tr>
				<td>
					<label>아이디 : ${loginMember.id}</label>
				</td>
			</tr>
			<tr>
				<td>
					<label>비밀번호</label>
					<input type="text" name="password"/>
				</td>
			</tr>
			<tr>
				<td>
					<label>이름  </label>
					<input type="text" name="name"/>
				</td>
			</tr>
			<tr>
				<td>
					<label>전화번호</label>
					<input type="text" name="phoneNum"/>
				</td>
			</tr>
			<tr>
				<td>
					<label>우편번호</label>
					<input type="text" name="postNum"/>
				</td>
			</tr>
			<tr>
				<td>
					<label>주소  </label>
					<input type="text" name="address_default"/>
				</td>
			</tr>
			<tr>
				<td>
					<label>상세주소</label>
					<input type="text" name="address_detail"/>
				</td>
			</tr>
			<tr>
				<td>
					<label>집 전화</label>
					<input type="text" name="homeNum"/>
				</td>
			</tr>
		</table>
		</div>
		<button>등록</button>
	</form>
	</div>
</body>
</html>