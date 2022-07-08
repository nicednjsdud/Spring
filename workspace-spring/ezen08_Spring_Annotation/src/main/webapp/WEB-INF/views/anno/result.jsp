<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결과창</title>
</head>
<body>
	<!-- 일반적인 방법으로 했을 때  -->
	<h1>아이디: $userId }</h1>
	<h1>이름 : ${userName }</h1>
	
	<!-- Map을 이용하여 했을 때 -->
	<h1>아이디: ${info.userId }</h1>
	<h1>이름 : ${info.userName }</h1>
</body>
</html>