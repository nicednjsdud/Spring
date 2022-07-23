<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
h3{
	color:skyblue;
	margin-bottom:5px;
}
.fixed_join{
	background-color:#b9a7fc;
	width: 20%;
	color:blue;
}
.member_gender{
	display:inline;
	margin-right: 110px;
}
.dot_line_2{
	border-bottom:2px dotted gray;
}
.dot_line{
	border-bottom:1px dotted gray;
}
.dashed_line{
	border-bottom:2px dashed gray;
}
.messsage_yn{
	margin-top:20px;
}
#smssts_yn{
	margin-right:5px;
}
#emailsts_yn{
	margin-right:5px;
}

</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	$(document).ready(
	    function () {
	        for (var i = 2021; i > 1920; i--) {
	            $('#member_birth_y').append('<option value="' + i + '">' + i + '</option>');
	        }
	        for (var i = 1; i < 13; i++) {
	            $('#member_birth_m').append('<option value="' + i + '">' + i + '</option>');
	        }
	        for (var i = 1; i < 32; i++) {
	            $('#member_birth_d').append('<option value="' + i + '">' + i + '</option>');
	        }
	    }
	);
	
	if($('input:checkbox[id="smssts_yn"]').is(":checked") == true){
		$("#smssts_yn").val() = "Y";
	}
	if($('input:checkbox[id="emailsts_yn"]').is(":checked") == true){
		$("#emailsts_yn").val() = "Y";
	}

	function fn_duplicateCheck() {
		const _id = $("#_member_id").val();
		if (_id == '') {
			alert("ID를 입력하세요.");
			return;
		}
		$.ajax({
			type : "post",
			url : "${contextPath}/member/duplicateCheck.do",
			dataType : "text",
			data : {
				id : _id
			},
			success : function(data, textStatus) {
				if (data == 'false') {
					alert("사용할 수 있는 ID입니다.");
					$('#btnDuplicateCheck').prop("disabled", true);
					$('#_member_id').prop("disabled", true);
					$('#member_id').val(_id);
				} else {
					alert("사용할 수 없는 ID입니다.");
				}
			},
			error : function(data, textStatus) {
				alert("에러가 발생했습니다.");
			}
		})

	}
	function select_email(ele){
	    const selectEle = $(ele);
	    const email2 = $('input[name=email2]');

	    // '1'인 경우 직접입력
	    if(selectEle.val() == "1"){
	        email2.attr('readonly', false);
	        email2.val('');
	    } else {
	        email2.attr('readonly', true);
	        email2.val(selectEle.val());
	    }
	}
	function sample4_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var roadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 참고 항목 변수

                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample4_postcode').value = data.zonecode;
                document.getElementById("sample4_roadAddress").value = roadAddr;
                document.getElementById("sample4_jibunAddress").value = data.jibunAddress;
                
              

                var guideTextBox = document.getElementById("guide");
                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
                if(data.autoRoadAddress) {
                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                    guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                    guideTextBox.style.display = 'block';

                } else if(data.autoJibunAddress) {
                    var expJibunAddr = data.autoJibunAddress;
                    guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                    guideTextBox.style.display = 'block';
                } else {
                    guideTextBox.innerHTML = '';
                    guideTextBox.style.display = 'none';
                }
            }
        }).open();
    }
	function validateForm(form){
		if(form._member_id.value==""){
			alert("ID를 입력하세요.");
			form._member_id.focus();
			return false;
		}
	}
	
</script>
</head>
<body>
	<h3>가입입력사항</h3>
	<form action="${contextPath}/member/addMember.do" method="post" onsubmit="return validateForm(this)">
		<div id="detail_table">
			<table align="center">
				<tbody>
					<tr class="dot_line_2">
						<td class="fixed_join">아이디</td>
						<td>
							<input type="text" name="_member_id" id="_member_id" size="20" /> 
							<input type="hidden" name="member_id" id="member_id" /> 
							<input type="button" id="btnDuplicatecheck" value="중복체크" onclick="fn_duplicateCheck()" />
						</td>
					</tr>
					<tr class="dot_line">
						<td class="fixed_join">비밀번호</td>
						<td>
							<input type="text" name="member_pw" id="member_pw" size="20" />
						</td>
					</tr>
					<tr class="dot_line">
						<td class="fixed_join">이름</td>
						<td>
							<input type="text" name="member_name" id="member_name" size="20" /> 
						</td>
					</tr>
					<tr class="dashed_line">
						<td class="fixed_join">성별</td>
						<td>
						<div class="member_gender">
							<input type="radio" name="member_gender"  value="102" />여성
						</div>
						<div class="member_gender">
							<input type="radio" name="member_gender" value="101" />남성
						</div>
						</td>
					</tr>
					<tr class="dot_line">
						<td class="fixed_join">생년월일</td>
						<td class="check_birth_gn">
							<select id="member_birth_y" name="member_birth_y">
								<option value="2000">2000</option>
							</select>년
							<select id="member_birth_m" name="member_birth_m">
								<option value="5">5</option>
							</select>월
							<select id="member_birth_d" name="member_birth_d">
								<option value="10">10</option>
							</select>일
							<input type="radio" name="MEMBER_birth_gn" value="2" />양력
							<input type="radio" name="MEMBER_birth_gn" value="1" />음력
						</td>
					</tr>
					<tr class="dashed_line">
						<td class="fixed_join">전화번호</td>
						<td>
							<select id="home_hp1" name="home_hp1">
								<option selected="selected">없음</option>
								<option value="031">031</option> 
								<option value="032">032</option> 
								<option value="033">033</option> 
								<option value="041">041</option> 
								<option value="02">02</option> 
								<option value="064">064</option> 
							</select>-
							<input type="text" name="home_hp2" id="home_hp2" size="20" />-
							<input type="text" name="home_hp3" id="home_hp3" size="20" /> 
						</td>
					</tr>
					<tr class="dot_line">
						<td class="fixed_join">휴대폰번호</td>
						<td>
							<select id="hp1" name="hp1">
								<option value="010" selected="selected">010</option>
								<option value="011">011</option>
								<option value="016">016</option>
								<option value="017">017</option>
								<option value="018">018</option>
								<option value="019">019</option>
							</select>-
							<input type="text" name="hp2" id="hp2" size="20" />-
							<input type="text" name="hp3" id="hp3" size="20" />
							<div class="messsage_yn">
								<input type="checkbox" id="smssts_yn" name="smssts_yn" value="N"/>BeyondTrust에서 발송하는 SMS 소식을 수신합니다.
							</div>
						</td>
					</tr>
					<tr class="dot_line_2">
						<td class="fixed_join">이메일<br>(e-mail)</td>
						<td>
							<input type="text" name="email1" id="email1" size="20" />@
							<input type="text" name="email2" id="email2" size="20">
							<select id="selectEmail" name="selectEmail" onChange="select_email(this)">
								<option value="1">직접입력</option>
								<option value="hanmail.net">hanmail.net</option>
								<option value="naver.com">naver.com</option>
								<option value="yahoo.co.kr">yahoo.co.kr</option>
								<option value="hotmail.com">hotmail.com</option>
								<option value="paran.com">paran.com</option>
								<option value="nate.com">nate.com</option>
								<option value="google.com">google.com</option>
								<option value="gmail.com">gmail.com</option>
								<option value="empal.com">empal.com</option>
								<option value="korea.com">korea.com</option>
								<option value="freechal.com">freechal.com</option>
							</select>
							<div class="messsage_yn">
								<input type="checkbox" id="emailsts_yn" name="emailsts_yn" value="N"/>BeyondTrust에서 발송하는 e-mail 소식을 수신합니다.
							</div>
						</td>
					</tr>
					<tr>
						<td class="fixed_join">주소</td>
						<td>
							<input type="text" id="sample4_postcode" name="zipcode" size="10">
							<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호검색"/>
							<br>지번 주소<br>
							<input type="text" id="sample4_jibunAddress" name="jibunAddress" size="45"><br><br>
							도로명 주소<br>
							<input type="text" id="sample4_roadAddress" name="roadAddress" size="45"><br><br>
							<span id="guide" style="color:#999;display:none"></span>
							나머지 주소<br>
							<input type="text" id="sample4_detailAddress" name="namujiaddress" size="45">
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="clear">
			<br />
			<table align="center">
				<tr colspan="2" align="center">
					<td><input type="submit" value="회원가입"  /></td>
					<td><input type="reset" value="다시입력" /></td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>