<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 폼</title>
</head>
<body>
	<form name="frmLogin" method="post" action="${contextPath }/anno/login5.do">
		<input type="hidden" name="email" value="" />
		<table border="1" width="80%" align="center">
			<tr align="center">
				<td>아이디</td>
				<td>이름</td>
			</tr>
			<tr align="center">
				<!-- @RequestParam에 설정한 userId와 같아야 함 -->
				<td><input type="text" name="userId" size="20" /></td>
				<!-- @RequestParam에 설정한 userName 같아야 함 -->
				<td><input type="text" name="userName" size="20" /></td>
			</tr>
			<tr align="center">
				<td colspan="2">
					<input type="submit" value="로그인" />
					<input type="reset" value="다시입력" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>