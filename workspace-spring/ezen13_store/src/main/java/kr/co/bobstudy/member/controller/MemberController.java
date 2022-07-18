package kr.co.bobstudy.member.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

import kr.co.bobstudy.member.dto.MemberDTO;

public interface MemberController {

	public ResponseEntity<MemberDTO> addMember(MemberDTO memberDTO, HttpServletRequest request,
			HttpServletResponse response) throws IOException;

	public ResponseEntity<String> duplicateCheck(String id, HttpServletRequest request, HttpServletResponse response)
			throws Exception;
}
