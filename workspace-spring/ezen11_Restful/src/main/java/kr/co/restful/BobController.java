package kr.co.restful;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/*")
public class BobController {

	static Logger logger = LoggerFactory.getLogger(BobController.class);

	// /rest/hello 요청시 브라우저로 문자열을 전송
	@RequestMapping("/hello")
	public String hello() {
		return "Hello Rainy season!";
	}

	// MemberDTO 객체의 속성 값을 저장한 후 JSON으로 전송함
	@RequestMapping("/member")
	public MemberDTO member() {
		MemberDTO dto = new MemberDTO();
		dto.setId("BOB");
		dto.setPwd("0824");
		dto.setName("정원영");
		dto.setEmail("nicednjsdud@gmail.com");
		return dto;
	}

	@RequestMapping("/memberList")
	public List<MemberDTO> listMembers() {
		// dto 객체를 저장할 컬렉션 객체 생성
		List<MemberDTO> list = new ArrayList();

		for (int i = 0; i < 10; i++) {
			MemberDTO dto = new MemberDTO();
			dto.setId("BOB" + i);
			dto.setPwd("0824" + i);
			dto.setName("정원영" + i);
			dto.setEmail("nicednjsdud" + i + "@gmail.com");
			list.add(dto);
		}

		return list;
	}

	@RequestMapping("/membersMap")
	public Map<Integer, MemberDTO> membersMap() {
		// MemberDTO 객체 저장할 HashMap 객체 생
		Map<Integer, MemberDTO> map = new HashMap<>();
		for (int i = 0; i < 10; i++) {
			MemberDTO dto = new MemberDTO();
			dto.setId("BOB" + i);
			dto.setPwd("0824" + i);
			dto.setName("정원영" + i);
			dto.setEmail("nicednjsdud" + i + "@gmail.com");
			map.put(i, dto);
		}
		return map; // HashMap 객체를 브라우저로 전송함
	}

	// 브라우저에서 요청시 {num} 부분의 값이 @PathVariable로 지정됨
	@RequestMapping(value = "/notice/{num}")
	public int notice(@PathVariable("num") int num) throws Exception {
		// 요청 URL에서 지정된 값이 num에 자동으로 할당 됨
		return num;
	}

	@RequestMapping(value = "/info", method = RequestMethod.POST)
	// JSON 전송된 데이터를 MemberDTO 객체의 속성에 자동으로 설정해 줌
	public void modify(@RequestBody MemberDTO dto) {
		logger.info(dto.toString()); // 콘솔에 JSON으로 전송된 회원 정보가 출력됨
	}

	@RequestMapping("/membersList2")
	public ResponseEntity<List<MemberDTO>> listMembers2() {

		List<MemberDTO> list = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			MemberDTO dto = new MemberDTO();
			dto.setId("BOB" + i);
			dto.setPwd("0824" + i);
			dto.setName("정원영" + i);
			dto.setEmail("nicednjsdud" + i + "@gmail.com");
			list.add(dto);
		}

		// 오류코드 500으로 설정해서 응답함
		return new ResponseEntity<>(list, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@RequestMapping(value = "/res3")
	public ResponseEntity res3() {
		HttpHeaders responHttpHeaders = new HttpHeaders();
		responHttpHeaders.add("Content-Type", "text/html; charset=utf-8");	// 전송할 데이터 종류, 인코딩 설정

		String message = "<script>";										/// 전송할 문자열(자바스크립트 코드)
		message += " alert('새 회원을 등록합니다.');";
		message += " location.href='/restful/rest/membersList2';";
		message += "</script>";

		return new ResponseEntity(message, responHttpHeaders, HttpStatus.CREATED);// html 형식으로 전송
	}
}
