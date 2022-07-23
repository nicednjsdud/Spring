<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width,initial-scale=1.0">
	<link rel ="stylesheet" href="${contextPath }/resources/MainStyle.css?after" type="text/css"/>
	<title>Footer</title>
	</head>
<body>										
   <div class="footer">
   	 
   	 <div class="select" >
   	 	<img src="../img/hugo_white.png" width="70" height="70">&nbsp;&nbsp;
		<a href=''>회사소개</a> |
		<a href=''>개인정보처리방침</a> |
		<a href=''>서비스약관</a>
	 </div>
	 <div class="info">
	 	<p>**회사</p>
	 	<p>서울특별시 서초구 서초대로77길 54 (서초더블유타워 13,14층)</p>
	 	<p>대표자:***</p>
	 	<p>사업자등록번호:1234567890</p>
	 	<p>T. 02 - 1234 - 5678</p>
	 
	 </div>
   </div>
  

</body>
</html>