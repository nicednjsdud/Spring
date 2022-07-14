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
<!-- 로그인 실패시 리다이렉트되면서 로그인 실패 메시지 표시함 -->
<c:choose>
	<c:when test="${param.result == 'loginFailed' }">
		<script type="text/javascript">
			window.onload=function(){
				alert("아이디나 비밀번호가 틀립니다. 다시 로그인 하세요.")
			}
		</script>
	</c:when>
</c:choose>
</head>
<body>
	<form name="frmLogin" method="post" action="${contextPath }/member/login.do">
		<input type="hidden" name="email" value="" />
		<table border="1" width="80%" align="center">
			<tr align="center">
				<td>아이디</td>
				<td>비밀번호</td>
			</tr>
			<tr align="center">
				<!-- @RequestParam에 설정한 userId와 같아야 함 -->
				<td><input type="text" name="id" size="20" /></td>
				<!-- @RequestParam에 설정한 userName 같아야 함 -->
				<td><input type="password" name="pwd" size="20" /></td>
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