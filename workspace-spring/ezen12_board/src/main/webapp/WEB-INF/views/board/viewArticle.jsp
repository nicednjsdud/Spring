<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="article" value="${ articleMap.article}" />
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
	<form action="#" name="frmArticle" method="post" enctype="multipart/form-data">
		<table border="0" align="center">
			<tr>
				<td width="150" align="center" bgcolor="#add3f7">
					글번호
				</td>
				<td>
					<input type="text" value="${article.articleNO }" disabled />
					<%-- 글 수정시 글번호를 컨트롤러에게 전송하기 위해 글번호 저장함 --%>
					<input type="hidden" name="articleNO" value="article.articleNO" />
				</td>
			</tr>
			<tr>
				<td width="150" align="center" bgcolor="#add3f7">
					작성자 아이디
				</td>
				<td>
					<input type="text" value="${article.id }" name="id" disabled />
				</td>
			</tr>
			<tr>
				<td width="150" align="center" bgcolor="#add3f7">
					제목
				</td>
				<td>
					<input type="text" value="${article.title }" name="title" id="i_title" disabled />
				</td>
			</tr>
			<tr>
				<td width="150" align="center" bgcolor="#add3f7">
					내용
				</td>
				<td>
					<textarea rows="20" cols="60" name="content" id="i_content" disabled >${article.content}</textarea>
				</td>
			</tr>
			
			<!-- 이미지 출력 -->
		</table>
	</form>
</body>
</html>