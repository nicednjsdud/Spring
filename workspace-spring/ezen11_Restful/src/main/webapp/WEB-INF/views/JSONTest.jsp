<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />

<!DOCTYPE html>
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#checkJSON").click(function() {
			const member = { /* 회원 정보를 JSON으로 생성 */
				id : "nicednjsdud",
				pwd : "824",
				name : "정원영",
				email : "nicednjsdud@naver.com"
			}
			$.ajax({
				type : "post",
				url : "${contextPath}/rest/info", /* /rest/info로 요청함 */
				contentType : "application/json",
				data : JSON.stringify(member), /* 회원 정보를 JSON문자열로 변환함 */
				success : function(data, textStatus) {
				},
				error : function(data, textStatus) {
					alert("에러가 발생했습니다.");
				},
				complete : function(data, textStatus) {
				},
			})
		})
	})
</script>
<meta charset="UTF-8">
<title>JSON 데이터 보내기</title>
</head>
<body>
	<input type="button" id="checkJSON" value="JSON 데이터 보내기" />
	<br>
	<br>

</body>
</html>