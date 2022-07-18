package kr.co.bobstudy.member.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.bobstudy.common.base.BaseController;
import kr.co.bobstudy.member.dto.MemberDTO;
import kr.co.bobstudy.member.service.MemberService;

@Controller("memberController")
@RequestMapping(value = "/member")
public class MemberContollerImpl extends BaseController implements MemberController {
	
	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberDTO memberDTO;
	
	@Override
	@RequestMapping(value = "/addMember.do")
	public ResponseEntity<MemberDTO> addMember(@ModelAttribute("member") MemberDTO _memberDTO,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		return null;
	}

	@Override
	@RequestMapping(value = "/duplicateCheck.do", method = { RequestMethod.POST })
	public ResponseEntity<String> duplicateCheck(@RequestParam("id") String id, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String result = memberService.duplicateCheck(id);
		ResponseEntity<String> responseEntity = new ResponseEntity<>(result,HttpStatus.OK);
		return responseEntity;
	}

}
