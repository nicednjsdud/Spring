package kr.co.bobstudy.member.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import kr.co.bobstudy.member.dto.MemberDTO;

public interface MemberController {

	public ResponseEntity addMember(HttpServletRequest request,
			HttpServletResponse response) throws Exception;

	public ResponseEntity<String> duplicateCheck(String id, HttpServletRequest request, HttpServletResponse response)
			throws Exception;
}
