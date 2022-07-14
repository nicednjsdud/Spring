<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.no_underline{text-decoration:none;}
</style>
</head>
<body>
	<h1>
		<a href="${contextPath}/member/listMembers.do" class="no_underline">멤버 리스트</a><br><br>
		<a href="${contextPath}/board/listArticles.do" class="no_underline">게시판 리스트</a><br><br>
		<a href="#"class="no_underline">상품 리스트</a><br><br>
	</h1>
</body>
</html>