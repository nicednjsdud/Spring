<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
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
<script type="text/javascript">
	function readURL(input, index) {
		if (input.files && input.files[0]) {
			const reader = new FileReader();
			reader.onload = function(e) {
				$('#preview0').attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
	function backToList(obj) {
		obj.action = "${contextPath }/board/listArticles.do"
		obj.submit();
	}
	let cnt = 1;
	function fn_addFile() {
		cnt++;
		let innerHtml = "";

		innerHtml += '<tr width=100% align=center>';

		innerHtml += '<td>' + "<input type=file name='file" + cnt
				+ "' onchange='readURL(this, " + cnt + ")' />" + '</td>';
		innerHtml += '<td>' + "<img id='preview"+cnt+"' width=440 height=280/>"
				+ '</td>' + '</tr>';
		$("#tb_newImage").append(innerHtml);
	}
</script>
</head>
<body>
	<h1 style="text-align: center;">답글 쓰기</h1>
	<!-- 파일 업로드 가능 -->
	<form name="articleForm" method="post" enctype="multipart/form-data"
		action="${contextPath }/board/addReplyArticle.do">
		<table border="0" align="center">
			<tr>
				<td align="right">작성자 :</td>
				<td colspan="2" align="center">${member.name }</td>
			</tr>
			<tr>
				<td align="right">제목 :</td>
				<td colspan="2" align="center"><input type="text" size="67"
					maxlength="500" name="title" placeholder="글제목" /></td>
			</tr>
			<tr>
				<td align="right" valign="top"><br>글내용 :</td>
				<td colspan="2"><textarea name="content" rows="20" cols="100"
						maxlength="4000" placeholder="이곳에 글을 입력하세요." style="width: 600px;"></textarea></td>
			</tr>
			<tr>
				<td align="right"><br>이미지파일 첨부 :</td>
				<td><input type="file" name="imageFileName"
					onchange="readURL(this, 0)" /></td>
				<td><img id="preview0" alt="이미지" src="#" width="440px"
					height="280px" /></td>
				<!-- 첨부이미지 미리보기 -->
			</tr>
			<tr>
				<td colspan="3">
					<table width="100%" border="0" id="tb_newImage"></table>
				</td>
			</tr>
			<tr height="200px">
				<td colspan="3"></td>
			</tr>
			<tr>
				<td align="right"><br>이미지파일 첨부 :</td>
				<td align="left" colspan="2"><input type="button"
					value="새 이미지 파일 추가하기" onclick="fn_addFile()" /></td>
			</tr>
			<tr>
				<td colspan="4">
					<div id="d_file"></div>
				</td>
			</tr>

			<tr>
				<td align="right"></td>
				<td colspan="2"><input type="submit" value="글쓰기" /> <input
					type="button" value="목록보기" onclick="backToList(this.form)" /></td>
			</tr>
		</table>
	</form>
</body>
</html>