<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- titles_member.xml의 definition의 하위 태그 put-attribute 태그의 name이 title인 값표시함 -->
<title><tiles:insertAttribute name="title" /></title>
<style>
#container {
	width: 100%;
	margin: 0px auto;
	text-align: center;
	border: 0px solid #ebedf0;
}

#header {
	padding: 5px;
	margin-bottom: 5px;
	border: 0px solid #ebedf0;
	background-color: blue;
}

#content {
	width: 75%;
	padding: 4px;
	margin-right: 5px;
	float: left;
	border: 0px solid #bcbcbc;
}

#sidebar-left {
	width: 15%;
	height: 700px;
	padding: 5px;
	margin: 0 5px 5px 0;
	float: left;
	background-color: #f5f7fa;
	border: 0px solid #bdbdbc;
	font-size: 10px;
}
#footer{
	clear: both;
	padding: 5px;
	border: 0px solid #bcbcbc;
	background-color: #f5f7fa;
}
</style>
</head>
<body>
	<div id="container">
		<div id="header">
			<!-- titles_member.xml의 definition의 하위 태그 put-attribute 태그의 name이 header인 JSP를 표시함 -->
			<tiles:insertAttribute name="header" />
		</div>
		<div id="sidebar-left">
			<!-- titles_member.xml의 definition의 하위 태그 put-attribute 태그의 name이 side인 JSP를 표시함 -->
			<tiles:insertAttribute name="side" />
		</div>

		<div id="content">
			<!-- titles_member.xml의 definition의 하위 태그 put-attribute 태그의 name이 header인 JSP를 표시함 -->
			<tiles:insertAttribute name="body" />
		</div>
		<div id="footer">
			<!-- titles_member.xml의 definition의 하위 태그 put-attribute 태그의 name이 footer인 JSP를 표시함 -->
			<tiles:insertAttribute name="footer" />
		</div>
	</div>
</body>
</html>