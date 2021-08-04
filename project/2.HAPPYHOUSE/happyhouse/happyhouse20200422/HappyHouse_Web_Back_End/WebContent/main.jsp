<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HappyHouse</title>
<style type="text/css">
img{
	width: 300px;
}
#member{
	text-align: right;
}
#main{
	text-align: center;
}
</style>
</head>
<body>
<div>
	<c:choose>
		<c:when test="${empty loginMember}">
			<div id="member">
				<a href="<c:url value="/member.do?act=signupform"/>">Sing Up</a>
				<a href="<c:url value="/member.do?act=loginform"/>">Login</a>
			</div>
			<div>
				<h2>${errMsg}</h2>
			</div>
		</c:when>
		<c:otherwise>
			<div id="member">
				${loginMember.id}(${loginMember.name})님 로그인 되었습니다.
				<a href="<c:url value="/member.do?act=logout"/>">로그아웃</a>
			</div>
			<div id="member">
				<label>내정보</label>
				<a href="<c:url value="/member.do?act=updateform"/>">내정보 수정</a>
				<a href="<c:url value="/member.do?act=delete"/>">회원 탈퇴</a>
			</div>
			<div id="main">
				<select onchange="window.open(value,'_self');">
					<option value="">사이트맵</option>
					<option value="${root}/member.do?act=selectall">회원정보 검색</option>
					<option value="${root}/apt.do?act=list&pg=1">아파트 실거래가 검색</option>
				</select>
			</div>
		</c:otherwise>
	</c:choose>
	<div id="member">
		<a>다운로드</a>
		<a href="<c:url value="/notice.do?act=mvwrite"/>">공지사항관리</a>
		<a>오늘의 뉴스</a>
	</div>
	<div id="main">
		<h1>HAPPY HOUSE</h1>
		<h2>행복한 우리집</h2>
		<img src="<c:url value="/img/logo.jpg" />"/>
	</div>
</div>
	
</body>
</html>