<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<%
request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입 창</title>
<script type="text/javascript">
	function joinform_check(form){
		const id = document.getElementById("id");
		const pwd = document.getElementById("pwd");
		const name = document.getElementById("name");
		const email = document.getElementById("email");
		
		if(id.value ==""){
			alert("아이디를 입력하세요.");
			id.focus();
			return false;
		}
		if(pwd.value ==""){
			alert("아이디를 입력하세요.");
			pwd.focus();
			return false;
		}
		if(name.value ==""){
			alert("아이디를 입력하세요.");
			name.focus();
			return false;
		}
		if(email.value ==""){
			alert("아이디를 입력하세요.");
			email.focus();
			return false;
		}
		
	}
</script>
</head>
<body>
	<form action="${contextPath}/member/addMember.do" method="post" onSubmit="joinform_check(this);">
		<h1 style="text-align: center;">회원 가입창</h1>
		<table align="center">
			<tr>
				<td width="200">
					<p align="right">아이디</p>
				</td>
				<td width="400"><input type="text" name="id" /></td>
			</tr>
			<tr>
				<td width="200">
					<p align="right">비밀번호</p>
				</td>
				<td width="400"><input type="password" name="pwd" /></td>
			</tr>
			<tr>
				<td width="200">
					<p align="right">이름</p>
				</td>
				<td width="400"><input type="text" name="name" /></td>
			</tr>
			<tr>
				<td width="200">
					<p align="right">이메일</p>
				</td>
				<td width="400"><input type="text" name="email" /></td>
			</tr>
			<tr>
				<td width="200">
					<p align="right">&nbsp;</p>
				</td>
				<td width="400">
					<input type="submit" value="가입하기"/> 
					<input type="reset" value="다시입력" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>