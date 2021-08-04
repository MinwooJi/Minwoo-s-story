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
		<div>
			<br>
			<h3>아파트 거래 목록</h3>
			<br>
			<form method="post" action="<c:url value="/list"/>">
				<select name="searchType">
					<option value="all">전체</option>
					<option value="dong">동 이름</option>
					<option value="aptName">아파트 이름</option>
				</select>
				<input type="text" name="searchWord" />
				<button>검색</button>
			</form>
		</div>
		<table class="table">
			<tr>
				<th>식별번호</th>
				<th>법정동</th>
				<th>아파트 이름</th>
				<th>거래 금액</th>
			</tr>
			<c:forEach var="houseDeal" items="${list}">
				<tr>
					<td>${houseDeal.no}</td>
					<td>${houseDeal.dong}</td>
					<td><a href="<c:url value="/detail?no=${houseDeal.no}"/>">${houseDeal.aptName}</a></td>
					<td>${houseDeal.dealAmount}</td>
				</tr>
			</c:forEach>
		</table>
		<c:choose>
					<c:when test="${bean.searchType == 'dong' }">
						<a href="<c:url value="/list?pageNo=1&searchWord=${bean.dong}&searchType=${bean.searchType}"/>">처음페이지</a>
					</c:when>
					<c:when test="${bean.searchType == 'aptName' }">
						<a href="<c:url value="/list?pageNo=1&searchWord=${bean.aptname}&searchType=${bean.searchType}"/>">처음페이지</a>
					</c:when>
					<c:otherwise>
						<a href="<c:url value="/list?pageNo=1"/>">처음페이지</a>
					</c:otherwise>
				</c:choose>
		<c:if test="${bean.pageNo >= 10}">
			<c:choose>
					<c:when test="${bean.searchType == 'dong' }">
						<a href="<c:url value="/list?pageNo=${prevIdx}&searchWord=${bean.dong}&searchType=${bean.searchType}"/>">이전</a>
					</c:when>
					<c:when test="${bean.searchType == 'aptName' }">
						<a href="<c:url value="/list?pageNo=${prevIdx}&searchWord=${bean.aptname}&searchType=${bean.searchType}"/>">이전</a>
					</c:when>
					<c:otherwise>
						<a href="<c:url value="/list?pageNo=${prevIdx}"/>">이전</a>
					</c:otherwise>
				</c:choose>
		</c:if>
		<c:forEach var="idx" items="${idxlist}">
				<c:choose>
					<c:when test="${bean.searchType == 'dong' }">
						<a href="<c:url value="/list?pageNo=${idx}&searchWord=${bean.dong}&searchType=${bean.searchType}"/>">${idx}</a>
					</c:when>
					<c:when test="${bean.searchType == 'aptName' }">
						<a href="<c:url value="/list?pageNo=${idx}&searchWord=${bean.aptname}&searchType=${bean.searchType}"/>">${idx}</a>
					</c:when>
					<c:otherwise>
						<a href="<c:url value="/list?pageNo=${idx}"/>">${idx}</a>
					</c:otherwise>
				</c:choose>
		</c:forEach>
		<c:if test="${bean.pageNo < lastPage}">
			<c:choose>
					<c:when test="${bean.searchType == 'dong' }">
						<a href="<c:url value="/list?pageNo=${nextIdx}&searchWord=${bean.dong}&searchType=${bean.searchType}"/>">다음</a>
					</c:when>
					<c:when test="${bean.searchType == 'aptName' }">
						<a href="<c:url value="/list?pageNo=${nextIdx}&searchWord=${bean.aptname}&searchType=${bean.searchType}"/>">다음</a>
					</c:when>
					<c:otherwise>
						<a href="<c:url value="/list?pageNo=${nextIdx}"/>">다음</a>
					</c:otherwise>
			</c:choose>
		</c:if>
		<c:choose>
					<c:when test="${bean.searchType == 'dong' }">
						<a href="<c:url value="/list?pageNo=${lastIdx}&searchWord=${bean.dong}&searchType=${bean.searchType}"/>">마지막</a>
					</c:when>
					<c:when test="${bean.searchType == 'aptName' }">
						<a href="<c:url value="/list?pageNo=${lastIdx}&searchWord=${bean.aptname}&searchType=${bean.searchType}"/>">마지막</a>
					</c:when>
					<c:otherwise>
						<a href="<c:url value="/list?pageNo=${lastIdx}"/>">마지막</a>
					</c:otherwise>
		</c:choose>
	</div>	
</body>
</html>