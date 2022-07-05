package kr.co.springmybatis.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import kr.co.springmybatis.service.MemberService;

public class MemberControllerImpl extends MultiActionController implements MemberController{
	
	private MemberService memberService;
	
	// memberService 빈을 주입하기 위해 setter() 구현
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	@Override
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String viewName = getViewName(request);
		
		return null;
	}

	private String getViewName(HttpServletRequest request) {
		
		return null;
	}
	
	
}
