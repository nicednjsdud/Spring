<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<%
	request.setCharacterEncoding("UTF-8");
%>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>파일 업로드 하기</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script type="text/javascript">
		let cnt = 1
		//파일추가 클릭하면 동적으로 파일 업로드를 추가함
		//name 속성값으로 file+cnt를 설정함으로서 값을 다르게 해줌
		function fnAddFile() {
			$("#d_file").append("<br />" + "<input type='file' name='file" +cnt+ "'/>")
			cnt++
		}
	</script>
</head>
<body>
	<h1>파일 업로드 하기</h1>
	<form action="${contextPath}/upload" method="post" enctype="multipart/form-data">
		<label>아이디 : </label>
		<input type="text" name="id" /><br/>
		<label>이름 : </label>
		<input type="text" name="name" /><br/>	
		<input type="button" value="파일 추가" onclick="fnAddFile()" /><br/>
		
		<!-- 파일 추가 클릭하면 동적으로 파일 업로드를 추가함 -->
		<div id="d_file">
		</div>
		
		<input type="submit" value="업로드" />
	</form>
</body>
</html>













