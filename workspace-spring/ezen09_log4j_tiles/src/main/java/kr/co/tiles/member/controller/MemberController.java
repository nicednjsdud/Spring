package kr.co.tiles.member.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.tiles.member.dto.MemberDTO;

public interface MemberController {

	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView form(@RequestParam(value="result",required=false)String result,HttpServletRequest request,HttpServletResponse response) throws Exception;
	public ModelAndView addMember(@ModelAttribute("member") MemberDTO memberDTO,HttpServletRequest request,HttpServletResponse response) throws Exception;
	public ModelAndView removeMember(@RequestParam String id,HttpServletRequest request,HttpServletResponse response) throws Exception;
	public ModelAndView login(@ModelAttribute("member")MemberDTO memberDTO,RedirectAttributes rAttributes,HttpServletRequest request,HttpServletResponse response) throws Exception;
	public ModelAndView logout(HttpServletRequest request,HttpServletResponse response) throws IOException;
}
