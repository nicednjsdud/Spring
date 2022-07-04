<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 출력</title>
</head>
<body>
	<table border="1" align="center" width=100%>
		<tr>
			<td><b>아이디</b></td>
			<td><b>비밀번호</b></td>
			<td><b>이름</b></td>
			<td><b>이메일</b></td>
		</tr>
		<tr>
			<td><b>${id }</b></td>
			<td><b>${pwd }</b></td>
			<td><b>${name }</b></td>
			<td><b>${email }</b></td>
		</tr>
	</table>
</body>
</html>