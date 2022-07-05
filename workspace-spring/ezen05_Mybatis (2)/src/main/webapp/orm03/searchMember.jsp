<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원 검색</h1>
	<form action="${contextPath }/mem4.do">
		<!-- hidden 태그 이용해 서블릿으로 action값을 전달함  -->
		<input type="hidden" name="action" value="searchMember" />
		이름 : <input type="text" name="name" /><br>
		이메일 : <input type="text" name="email" /><br>
		<input type="submit" value="검색" />
	</form>
</body>
</html>