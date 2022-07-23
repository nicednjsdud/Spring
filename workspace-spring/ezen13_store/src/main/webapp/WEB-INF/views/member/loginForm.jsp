<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
h3 {
	color: skyblue;
	margin-bottom: 5px;
}

.fixed_join {
	background-color: #b9a7fc;
	width: 20%;
	color: blue;
}

.dot_line {
	border-bottom: 1px dotted gray;
}
</style>
</head>
<body>
	<h3>회원 로그인 창</h3>
	
		<form action="${contextPath}/member/login.do" method="post"
			onsubmit="return validateForm(this)">
		<div id="detail_table">
			<table>
				<tbody>
					<tr class="dot_line">
						<td class="fixed_join">아이디</td>
						<td><input type="text" name="member_id" /></td>
					</tr>
					<tr>
						<td class="fixed_join">비밀번호</td>
						<td><input type="text" name="member_pwd" /></td>
					</tr>
				</tbody>
			</table>

			<br>
			<br>
	</div>
	<div class="clear">
		<table align="center">
			<tr colspan="2" align="center">
				<td><input type="submit" value="로그인" /></td>
				<td><input type="reset" value="초기화" /></td>
			</tr>
		</table>

	
	</div>
	</form>
</body>
</html>