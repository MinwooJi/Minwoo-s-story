<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<c:if test="${userinfo == null}">
	<c:redirect url="/notice.do"/>
</c:if>
<c:if test="${userinfo != null}">
<!DOCTYPE html>
<html lang="ko">
<head>
  <title>Happy House 공지사항 성공</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container" align="center">
	<div class="col-lg-6" align="right">
	<strong>${userinfo.username}</strong>님 환영합니다.
	<a href="${root}/notice.do?act=logout">로그아웃</a>
	</div>
	<div class="col-lg-6">
	  <div class="jumbotron">
	    <h1>공지사항 작성..완료</h1>      
	  </div>  
	  <p><a href="${root}/notice.do?act=list&key=&word=">글목록 가기</a></p>
	</div>
</div>
</body>
</html>
</c:if>