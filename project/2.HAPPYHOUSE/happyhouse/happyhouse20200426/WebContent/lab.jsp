<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ssafy.happyhouse.model.dto.HouseDeal"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>실험실</h1>
	<h2>아파트 거래 목록 - 거래 금액 최댓값 최솟값</h2>
	<div>
		<c:if test="${loginMember != null}">
			${loginMember.id}(${loginMember.name})님 로그인 되었습니다.
			<div>
				<a href="<c:url value="/main.do?act=logout"/>">로그아웃</a>
			</div>
		</c:if>
	</div>
	<div>
		<a href="<c:url value="/main.do"/>">메인 페이지</a>
	</div>
	<table>
		<tr>
			<th></th>
			<th>식별번호</th>
			<th>법정동</th>
			<th>아파트 이름</th>
			<th>거래 금액</th>
		</tr>
		<tr>
			<th>최소 거래 금액</th>
			<td>${list[0].no}</td>
			<td>${list[0].dong}</td>
			<td><a
				href="<c:url value="/main.do?act=detail&no=${list[0].no}"/>">${list[0].aptName}</a></td>
			<td>${list[0].dealAmountInt}</td>
		</tr>
		<tr>
			<th>최대 거래 금액</th>
			<td>${list[1].no}</td>
			<td>${list[1].dong}</td>
			<td><a
				href="<c:url value="/main.do?act=detail&no=${list[1].no}"/>">${list[1].aptName}</a></td>
			<td>${list[1].dealAmountInt}</td>
		</tr>
	</table>
</body>
</html>