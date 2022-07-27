<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="articlesList" value="${articlesMap.articleList }" />
<c:set var="totArticles" value="${articlesMap.total_count }" />
<c:set var="section" value="${articlesMap.section }" />
<c:set var="pageNum" value="${articlesMap.pageNum }" />
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 목록</title>
<style type="text/css">
.cls1 {
	text-decoration: none;
}

.cls2 {
	text--align: center;
	font-size: 20px;
}

.no_uline {
	text-decoration: none;
}

.sel_page {
	text-decoration: none;
	color: red;
}
</style>
<script type="text/javascript">
	function fn_articleForm(isLogOn, articleForm, loginForm) {
		if (isLogOn != '' && isLogOn != 'false') {
			location.href = articleForm;
		} else {
			alert("로그인 후 글쓰기가 가능합니다.");
			location.href = loginForm + '?action=/board/articleForm.do';
		}
	}
</script>
</head>
<body>
	<table align="center" border="1" width="80%">
		<tr height="10" align="center" bgcolor="lightblue">
			<td>글번호</td>
			<td>작성자</td>
			<td>제목</td>
			<td>작성일</td>
			<td>조회수</td>
		</tr>
		<c:choose>
			<c:when test="${empty articlesList }">
				<tr height="10">
					<td colspan="4">
						<p>
							<b><span style="font-size: 9px;">등록된 글이 없습니다.</span></b>
						</p>
					</td>
				</tr>
			</c:when>

			<c:when test="${!empty articlesList }">
				<c:forEach var="article" items="${articlesList }"
					varStatus="articleNum">
					<tr align="center">
						<td width="5%">${articleNum.count }</td>
						<!-- varStatus의 속성 count을 이용해 글번호 1부터 자동표시 -->
						<td width="10%">${article.id }</td>
						<td width="35%" align="left"><span
							style="padding-right: 30px;"></span> <c:choose>
								<c:when test="${article.level>1 }">
									<c:forEach begin="1" end="${article.level }" step="1">
										<!-- 부모글 기준 왼쪽 여백을 level값만큼 채워 들여쓰기 -->
										<span style="padding-left: 20px"></span>
										<!-- 왼쪽으로 20px만큼 여백을 줌 -->
									</c:forEach>
									<span style="font-size: 12px">[답변]</span>
									<a class="cls1"
										href="${contextPath }/board/viewArticle.do?articleNO=${article.articleNO}">${article.title }</a>
								</c:when>
								<c:otherwise>
									<!-- 부모글임 -->
									<a class="cls1"
										href="${contextPath }/board/viewArticle.do?articleNO=${article.articleNO}">${article.title }</a>
								</c:otherwise>
							</c:choose></td>
						<td width="10%"><fmt:formatDate value="${article.writeDate }" />
						</td>
						<td width="10%">
							${article.viewCounts}
						</td>
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>
	</table>

	<div class="cls2">
		<c:if test="${totArticles !=null }">
			<%-- 전체 글수에 따라 페이지 표시를 다르게 함 --%>
			<c:choose>
				<c:when test="${totArticles > 100} ">
					<%--전체 개수가 100보다 클때 --%>
					
					<c:forEach var="page" begin="1" end="10" step="1">
						<c:if test="${section > 1 && page==1}">
							<a class="no_uline"
								href="${contextPath }/board/listArticles.do?section=${section-1}&pageNum=${section-1}*10+1">&nbsp;
								이전</a>
						</c:if>
							<a class="sel_page"
								href="${contextPath }/board/listArticles.do?section=${section}&pageNum=${page}">&nbsp;
								${page}</a>
						<c:if test="${page == 10 }">
							<a class="no_uline"
								href="${contextPath }/board/listArticles.do?section=${section-1}&pageNum=${section*10+1}">&nbsp;
								이전</a>
						</c:if>
					</c:forEach>
				</c:when>

				<c:when test="${totArticles == 100 }">
					<%-- 등록된 개수가 100개인 경우 --%>
					
					<c:forEach var="page" begin="1" end="10" step="1">
						<c:choose>
							<%-- 페이지번호와 pageNum이 같은 경우 페이지 번호를 빨간색으로 표시 --%>
							<c:when test="${page == pageNum}">
								<a class="sel_page"
									href="${contextPath }/board/listArticles.do?section=${section}&pageNum=${page}">${page }</a>
							</c:when>
							<c:otherwise>
								<a class="no_unline"
									href="${contextPath }/board/listArticles.do?section=${section}&pageNum=${page}">${page }</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>

				</c:when>
				<c:when test="${totArticles < 100 }">
					<%--  등록된 개수가 100개 미만인경우 --%>
					<%--  글수가 100개 되지 않으므로 표시되는 페이지는 10개가 되지 않음 --%>
					<%-- 전체 글 수를 10으로 나누어 구한 몫에 1을 더한 페이지까지 표시 --%>
					<c:forEach var="page" begin="1" end="${totArticles/10 +1 }">
						<c:choose>
							<%-- 페이지번호와 pageNum이 같은 경우 페이지 번호를 빨간색으로 표시 --%>
							<c:when test="${page == pageNum}">
								<a class="sel_page"
									href="${contextPath }/board/listArticles.do?section=${section}&pageNum=${page}">${page }</a>
							</c:when>
							<c:otherwise>
								<a class="no_unline"
									href="${contextPath }/board/listArticles.do?section=${section}&pageNum=${page}">${page }</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</c:when>
			</c:choose>
		</c:if>
	</div>

	<a class="cls1"
		href="javascript:fn_articleForm('${isLogOn }','${contextPath }/board/articleForm.do','${contextPath }/member/loginForm.do')"><p
			class="cls2">글쓰기</p></a>
</body>
</html>