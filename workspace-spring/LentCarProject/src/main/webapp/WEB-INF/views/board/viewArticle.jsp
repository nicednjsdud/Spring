<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="article" value="${articleMap.article }" />
<c:set var="imageFileList" value="${articleMap.imageFileList }" />
<%
	request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style type="text/css">
		.tr_modEable {
			visibility: hidden;
		}
		#tr_btn_modify {
			display: none;
		}
	</style>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script type="text/javascript">
		function backToList(obj) {
			obj.action = "${contextPath}/board/listArticles.do"
			obj.submit()
		}
		
		//수정하기 클릭시 텍스트 박스를 활성화시킴
		function fn_enable() {
			document.getElementById("i_title").disabled=false
			document.getElementById("i_content").disabled=false
			
			document.getElementById("tr_btn_modify").style.display="block"
			document.getElementById("tr_btn").style.display = "none"
			$(".tr_modEable").css("visibility", "visible")
		}
		
		function readURL(input,index) {
			if (input.files && input.files[0]) {
				let reader = new FileReader()
				reader.onload = function(e) {
					$('#preview'+index).attr('src', e.target.result)
				}
				reader.readAsDataURL(input.files[0])
			}
		}	
		
		//수정등록 버튼 클릭시 컨트롤러에게 수정 데이터를 전송 
		function fn_modify_article(obj) {
			obj.action = "${contextPath}/board/modArticle.do"
			obj.submit()
		}
		
		let pre_img_num = 0;			//기존 이미지 갯수 (수정 이전의 이미지 갯수)
		let img_index = 0;				//새로 추가된 이미지 갯수 (수정 후 이미지 갯수)
		
		//(0721일...)
		let isFirstAddImage = true
		function fn_addModImage(_img_index) {
			console.log("here!")
			if (isFirstAddImage == true) {
				pre_img_num = _img_index
				img_index = ++_img_index
				isFirstAddImage = false;
			}
			else {
				++img_index;
			}
			
			let innerHtml = "";
			
			innerHtml += '<tr width=200px align=center>'
			
			innerHtml += '<td>' +
								"<input type=file name='imageFileName"+img_index+"' onchange='readURL(this, "+img_index+")' />" +
						 '</td>'
			innerHtml += '<td>' +		
								"<img id='preview"+img_index+"' width=640 height=480 />" +
						 '</td>'

			innerHtml += '</tr>'
			$("#td_addImage").append(innerHtml)		
			$("#added_img_num").val(img_index);		//추가된 이미지수를 hidden 속성의 태그에 저장해서 컨트롤러에 보냄
		}
				
	</script>
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
					<%-- 글수정시 글번호를 컨트롤러에게 전송하기 위해 글번호 저장함 --%>
					<input type="hidden" name="articleNO" value="${article.articleNO }">
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
					<textarea rows="20" cols="60" name="content" id="i_content" disabled>${article.content }</textarea>
				</td>
			</tr>
			
			<!-- 이미지(들) 출력 -->
			<!-- 다수 이미지 표시 -->
			<c:set var="img_index" />
			<c:choose>
				<c:when test="${not empty imageFileList && imageFileList != 'null' }">
					<c:forEach var="item" items="${imageFileList }" varStatus="status">
						<tr id="tr_${status.count }">
							<td width="150" align="center" bgcolor="#add3f7">
								이미지${status.count }
							</td>
							<td>
								<!-- 이미지 수정시 미리 원래 이미지 파일이름을 저장함 -->
								<input type="hidden" name="oldFileName" value="${item.imageFileName }" />
								<input type="hidden" name="imageFileNO" value="${item.imageFileNO }" />
								<img alt="이미지" src="${contextPath}/download.do?imageFileName=${item.imageFileName}&articleNO=${item.articleNO}"
									id="preview${status.index }" width="450px" height="450px"><br />
							</td>
						</tr>
						<tr class="tr_modEable" id="tr_sub${status.count }">
							<td></td>
							<td>
								<input type="file" name="imageFileName${status.index }" id="i_imageFileName${status.index }" onchange="readURL(this, ${status.index})">
								<input type="button" value="이미지 삭제하기" 
										onclick="">
							</td>
						</tr>
						
						<c:if test="${status.last eq true}">
							<c:set var="img_index" value="${status.count }" />
							<input type="hidden" name="pre_img_num"  value="${status.count }"/>	<!-- 기존의 이미지수 -->
							<input type="hidden" name="added_img_num"  id="added_img_num"  value="${status.count }"/>	<!-- 수정시 새로 추가된 이미지수 -->
						</c:if>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<c:set var="img_index" value="${0 }" />
					<input type="hidden" name="pre_img_num"  value="${0 }"/>	<!-- 기존의 이미지수 -->
					<input type="hidden" name="added_img_num" id="added_img_num"  value="${0 }"/>	<!-- 수정시 새로 추가된 이미지수 -->					
				</c:otherwise>
			</c:choose>	
			
			<tr>
				<td colspan="2">
					<table id="td_addImage" align="center">
					</table>
				</td>
			</tr>
			
			<tr class="tr_modEable">
				<td colspan="2">
					<input type="button" value="이미지 추가" onclick="fn_addModImage(${img_index})" />
				</td>
			</tr>
			
			<tr>
				<td width="150" align="center" bgcolor="#add3f7">
					등록일자
				</td>
				<td>
					<input type="text" value='<fmt:formatDate value="${article.writeDate }"/>' disabled />
				</td>
			</tr>
			<tr id="tr_btn_modify">
				<td colspan="2" align="center">
					<input type="button" value="수정등록" onclick="fn_modify_article(frmArticle)" />
					<input type="button" value="취소" onclick="backToList(frmArticle)" />
				</td>
			</tr>
			
			
			<tr id="tr_btn">
				<td colspan="2" align="center">
					<c:if test="${member.id == article.id }">	<!-- 로그인 ID가 작성자 ID와 같은 경우 -->
						<input type="button" value="수정하기" onclick="fn_enable()" />
						<input type="button" value="삭제하기" onclick="" />
					</c:if>
					<input type="button" value="목록보기" onclick="backToList(frmArticle)" />
					<input type="button" value="답글쓰기" onclick="" />
				</td>
			</tr>						
		</table>
	</form>
</body>
</html>



















