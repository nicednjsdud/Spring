package kr.co.tiles.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import kr.co.tiles.member.dto.MemberDTO;
import kr.co.tiles.member.service.MemberService;

/*
 *  @Controller를 이용해 MemberControllerImpl 클래스에 대해 id가 memberController인 빈을 자동 생성
 */
@Controller("memberController")
@EnableAspectJAutoProxy		// aop 기능 활성화 
public class MemberControllerImpl extends MultiActionController implements MemberController {
	
//	private static final Logger logger = LoggerFactory.getLogger("MemberControllerImpl.class");
	
	// @Autowired를 이용해 id가 memberService인 빈을 자동 주입함
	@Autowired
	private MemberService memberService;

	// @Autowired를 이용해 id가 memberDTO인 빈을 자동 주입함
	@Autowired
	private MemberDTO memberDTO;

	// 두 단계로 요청 시 바로 해당 메서드를 호출하도록 매핑함
	@Override
	@RequestMapping(value = "/member/listMembers.do", method = RequestMethod.GET)
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		/* 브라우저에서의 요청명에서 확장명.do를 제외한 뷰이름을 가져옴 */
		String viewName=getViewName(request);
//		logger.debug("debug 레벨 : viewName = " + viewName);
		List<MemberDTO> membersList = memberService.listMembers();
		ModelAndView mav = new ModelAndView(viewName);

		// 조회한 회원 정보를 ModelAndView의 addObject() 이용해 바인딩함.
		mav.addObject("membersList", membersList);

		return mav;
	}

	// 요청명이 Form.do로 끝나면 form() 메서드 호출
	@Override
	@RequestMapping(value = "/member/*Form.do", method = RequestMethod.GET)
	public ModelAndView form(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName=getViewName(request);
		ModelAndView mav = new ModelAndView(viewName);
		
		return mav;
	}

	// 회원 가입창에서 전송된 회원 정보를 바로 MemberDT 객체에 설정함
	@Override
	@RequestMapping(value = "/member/addMember.do", method = RequestMethod.POST)
	public ModelAndView addMember(@ModelAttribute("member") MemberDTO memberDTO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");

		// 설정된 MemberDTO 객체를 SQL문으로 전달해 회원등록함.
		int result = 0;
		result = memberService.addMember(memberDTO);
		if (result == 1) {
			System.out.println("회원 가입 성공");
		} else {
			System.out.println("회원 가입 실패");
		}
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");

		return mav;
	}
	
	// 전송된 id를 변수 id에 설정함
	@Override
	@RequestMapping(value="/member/removeMember.do",method = RequestMethod.GET)
	public ModelAndView removeMember(@RequestParam("id") String id,HttpServletRequest request, HttpServletResponse response) throws Exception {
		int result = 0;
		
		result = memberService.removeMember(id);
		if(result ==1) {
			System.out.println("회원 삭제 성공");
		}
		else {
			System.out.println("회원 삭제 실패");
		}
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}

	// http://localhost:8080/bob/member/listMembers.do
	// request 객체에서 URL 요청명을 가져와 .do를 제외한 요청을 구함
	private String getViewName(HttpServletRequest request) {

		String contextPath = request.getContextPath();	// member/*.do
		int begin = 0;
		if (!((contextPath == null) || ("".equals(contextPath)))) {
			begin = contextPath.length();		// 전체 요명의 길이

		}
		int end;
		
		// 주소창의 현재 uri받아오기
		String uri = (String) request.getAttribute("javax.servlet.include.request_uri");
		if (uri == null || uri.trim().equals("")) {
			uri = request.getRequestURI();
		}
	
		
		if (uri.indexOf(";") != -1) {
			end = uri.indexOf(";");
		} else if (uri.indexOf("?") != -1) {
			end = uri.indexOf("?");
		} else {
			end = uri.length();
		}
		String filename = uri.substring(begin, end);	//	 /member/listMembers.do
		
		if (filename.indexOf(".") != -1) {
			filename = filename.substring(0, filename.lastIndexOf("."));	// 	/member/listMembers
		}
		
		if (filename.indexOf("/") != -1) {
			// /member/listMembers.do 요청 할 경우 member/listMembers를 파일이름으로 가져옴
			filename = filename.substring(filename.lastIndexOf("/",1), filename.length());	//   member/listMembers
		}
		return filename;
	}

}
