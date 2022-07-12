<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>결과</title>
</head>
<body>
	<h1>업로드가 완료되었습니다.</h1>
	<label>아이디 : </label>
	<input type="text" name="id" value="${map.id }" readonly /><br/>	<!-- map으로 넘어온 매개변수 값을 표시 -->
	<label>이름 : </label>
	<input type="text" name="name" value="${map.name }" readonly /><br/>	<!-- map으로 넘어온 매개변수 값을 표시 -->	
	
	<div>
		<!-- 업로드한 파일들 forEach문 이용해 img 태그에 표시함 -->
		<c:forEach var="imageFileName" items="${map.fileList }">
			<img alt="이미지" src="${contextPath}/download?imageFileName=${imageFileName}">
			<br/><br/><br/>
		</c:forEach>
	</div>
</body>
</html>