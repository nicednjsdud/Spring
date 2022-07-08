package kr.co.bob.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.co.bob.member.dto.MemberDTO;

public interface MemberController {

	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView form(HttpServletRequest request,HttpServletResponse response) throws Exception;
	public ModelAndView addMember(@ModelAttribute("member") MemberDTO memberDTO,HttpServletRequest request,HttpServletResponse response) throws Exception;
	public ModelAndView removeMember(@RequestParam String id,HttpServletRequest request,HttpServletResponse response) throws Exception;
}
