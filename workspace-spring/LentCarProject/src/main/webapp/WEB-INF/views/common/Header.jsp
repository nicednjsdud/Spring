<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel ="stylesheet" href="${contextPath }/resources/MainStyle.css?after" type="text/css"/>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="https://use.fontawesome.com/releases/v6.1.1/js/all.js"></script>
	<title>헤더</title>
	<script>
		function showMenu(){
			if($(".myMenu").css("display")=="none"){
				$(".myMenu").css("display","block")
			}else{
				$(".myMenu").css("display","none")
			}
		}
	</script>
</head>
<body>
	<div class="HeaderContainer">
		<div class="HeaderLogo">
			<img src="${contextPath }/resources/img/hugo_white_repair.jpeg" style="width:100%; height:100%;">
		</div>
		<div class="search">
			<input type="text" class="searchbox"/>
			<button class="searchBtn"><i class="fa-solid fa-magnifying-glass"></i></button>
		</div>
		<div class="headerMenu">
			<a>이벤트/쿠폰</a>&nbsp;&nbsp;|&nbsp;&nbsp;
			<a>게시판</a>&nbsp;&nbsp;|&nbsp;&nbsp;
			<a href="${contextPath }/member/loginForm.do">로그인</a>&nbsp;&nbsp;|&nbsp;&nbsp;
			<a href="${contextPath }/member/memberForm.do">회원가입</a>&nbsp;&nbsp;|&nbsp;&nbsp;
			<a onclick="return showMenu()"><i class="fa-solid fa-chalkboard-user" class="user_info"></i></a>
			<div class="myMenu">
				<ul>	
					<li>ezen님</li>
					<li>&nbsp;</li>
					<li><a>회원정보</a></li>
					<li><a>쿠폰함</a></li>
					<li><a>찜리스트</a></li>
			</ul>
			</div>
		</div>
		<div class="serchDTL">
			<button class="searchDTLBTN">세부검색</button>
		</div>
	</div>
</body>
</html>