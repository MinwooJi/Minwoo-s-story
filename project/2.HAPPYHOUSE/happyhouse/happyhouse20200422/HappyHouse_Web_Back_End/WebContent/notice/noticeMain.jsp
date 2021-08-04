<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>공지 페이지</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
</head>
<body>
<div align="center">
<div>
<h2>관리자 모드</h2>
</div>
<div>
</div>
<c:if test="${userinfo == null}">
<h3>${msg}</h3>
<%@ include file="/notice/login.jsp" %>
</c:if>
<c:if test="${userinfo != null}">
<div>
<strong>${userinfo.username}</strong> 관리자님 환영합니다.
</div>
<a href="${root}/notice.do?act=logout">로그아웃</a><br>
<a href="${root}/notice.do?act=mvwrite">공지사항 쓰기</a><br>
<a href="${root}/notice.do?act=list&key=&word=">공지사항 목록(페이징 알고리즘 추가)</a>
<a href="${root}/notice.do?act=listNoticeByAlgo&key=&word=">공지사항 목록(KMP/합병정렬 algo (속도느림))</a>
</c:if>
</div>
</body>
</html>