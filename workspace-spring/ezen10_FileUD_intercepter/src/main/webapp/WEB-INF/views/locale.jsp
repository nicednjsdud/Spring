<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- spring :message 태그 이용할 수 있도록 설정 -->
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="site.title" text="Member Info" /></title>
</head>
<body>
	<a href="${contextPath }/inter/locale.do?locale=ko">한국</a>		<!-- 한국어를 요청 -->
	<a href="${contextPath }/inter/locale.do?locale=en">ENGLISH</a>	<!-- 영어를 요청 -->
	<h1><spring:message code="site.title" text="Member Info" /></h1>
	
	<p>
		<spring:message code="site.name" text="name" />
		<spring:message code="name" text="name" />
	</p>
	<p>
		<spring:message code="site.job" text="job" />
		<spring:message code="job" text="job" />
	</p>
	
	<input type="button" value="<spring:message code='btn.send' />" />
	<input type="button" value="<spring:message code='btn.cancel' />" />
	<input type="button" value="<spring:message code='btn.finish' />" />
</body>
</html>