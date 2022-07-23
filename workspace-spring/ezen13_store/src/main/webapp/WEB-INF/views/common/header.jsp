<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="logo">
		<a href="${contextPath }/main/main.do">
			<img alt="로고" src="${contextPath }/resources/image/logo.jpeg" width="176" height="80" />
		</a>
	</div>
	<div id="head_link">
		<ul>
			<c:choose>
				<c:when test="${isLogOn==true }">
					<li><a>로그아웃</a></li>
					<li><a>마이페이지</a></li>
					<li><a>장바구니</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="${contextPath }/member/loginForm.do">로그인</a></li>
					<li><a href="${contextPath }/member/memberForm.do">회원가입</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
</body>
</html>