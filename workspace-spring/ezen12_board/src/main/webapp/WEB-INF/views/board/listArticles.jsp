<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 목록</title>
<style type="text/css">
	.cls1{text-decoration:none;}
</style>
</head>
<body>
	<table align="center" border="1" width="80%">
		<tr height="10" align="conter" bgcolor="lightblue">
			<td>글번호</td>
			<td>작성자</td>
			<td>제목</td>
			<td>작성일</td>
		</tr>
		<c:choose>
			<c:when test="${empty articlesList }">
				<tr height="10">
					<td colspan="4">
						<p>
							<b><span style="font-size: 9px;">등록된 글이 없습니다.</span></b>
						</p>
					</td>
			</c:when>
			
			<c:when test="${!empty articlesList }">
				<c:forEach var="article" items="${articlesList }" varStatus="articleNum">
					<tr align="center">
						<td width="5%">${articleNum.count }</td>
						<td width="10%">${article.id }</td>
						<td width="35%" align="left"><span style="padding-right:30px;">${article.title }</span>
							<c:when test="${article.level>1 }">
								<c:forEach begin="1" end="${article.level }" step="1">
									<span style="padding-left:20px"></span>
								</c:forEach>
								<span style="font-size: 12px">[답변]</span>
								<a class="cls1" href="${contextPath }/board/viewArticle.do?articleNO=${article.articleNO}">${article.title }</a>
							</c:when>
							<c:otherwise>		<!-- 부모글임 -->
								<a class="cls1" href="${contextPath }/board/viewArticle.do?articleNO=${article.articleNO}">${article.title }</a>
							</c:otherwise>
						</td>
						<td width="10%">
							<fmt:formatDate value="${article.writeDate }"/>
						</td>
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>
	</table>
</body>
</html>