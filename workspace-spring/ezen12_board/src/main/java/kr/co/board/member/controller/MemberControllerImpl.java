package kr.co.board.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.board.member.dto.MemberDTO;
import kr.co.board.member.service.MemberService;

/*
 *  @Controller를 이용해 MemberControllerImpl 클래스에 대해 id가 memberController인 빈을 자동 생성
 */
@Controller("memberController")
@EnableAspectJAutoProxy // aop 기능 활성화
public class MemberControllerImpl extends MultiActionController implements MemberController {

//	private static final Logger logger = LoggerFactory.getLogger("MemberControllerImpl.class");

	// @Autowired를 이용해 id가 memberService인 빈을 자동 주입함
	@Autowired
	private MemberService memberService;

	// @Autowired를 이용해 id가 memberDTO인 빈을 자동 주입함
	@Autowired
	private MemberDTO memberDTO;
	
	@RequestMapping(value="/main.do",method=RequestMethod.GET)
	public ModelAndView main(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String viewName = (String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
		
	}

	// 두 단계로 요청 시 바로 해당 메서드를 호출하도록 매핑함
	@Override
	@RequestMapping(value = "/member/listMembers.do", method = RequestMethod.GET)
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");

		ModelAndView mav = new ModelAndView(viewName);

		List<MemberDTO> membersList = memberService.listMembers();

		// 조회한 회원 정보를 ModelAndView의 addObject() 이용해 바인딩함.
		mav.addObject("membersList", membersList);

		return mav;
	}

	// 요청명이 Form.do로 끝나면 form() 메서드 호출
	@Override
	@RequestMapping(value = "/member/*Form.do", method = RequestMethod.GET)
	public ModelAndView form(@RequestParam(value="result",required=false)String result,HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName); // viewName이 definition태그에 설정한 뷰이름과 일치함
		return mav;
	}
	/*
	 * 로그인창 요청시 매개변수 result가 전송되면 변수 result에 값을 저장함. 매개변수 result가 전송되지 않으면 무시함.
	 * 
	 */
//	@Override
//	@RequestMapping(value = "/member/*Form.do", method = RequestMethod.GET)
//	public ModelAndView form(@RequestParam(value="result",required=false)String result,HttpServletRequest request, HttpServletResponse response) throws Exception {
//		String viewName=getViewName(request);
//		ModelAndView mav = new ModelAndView(viewName);
//		
//		return mav;
//	}

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
	@RequestMapping(value = "/member/removeMember.do", method = RequestMethod.GET)
	public ModelAndView removeMember(@RequestParam("id") String id, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int result = 0;

		result = memberService.removeMember(id);
		if (result == 1) {
			System.out.println("회원 삭제 성공");
		} else {
			System.out.println("회원 삭제 실패");
		}
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}

	@Override
	@RequestMapping(value = "/member/login.do", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("member") MemberDTO memberDTO, RedirectAttributes rAttributes,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView mav = new ModelAndView();
		memberDTO = memberService.login(memberDTO);
		if (memberDTO != null) {
			HttpSession session = request.getSession();
			session.setAttribute("member", memberDTO); // 세션에 회원정보 저장
			session.setAttribute("isLogOn", true); // 세션에 로그인 상태를 true로 설정함
			mav.setViewName("redirect:/member/listMembers.do"); // memberDTO로 반환된 값이 있으면 세션을 이용해 로그인상태 true로 함
		} else {
			rAttributes.addAttribute("result", "loginFailed"); // 로그인 실패시 실패 메세지를 로그인창 전달
			mav.setViewName("redirect:/member/loginForm.do"); // 로그인 실패시 다시로그인창으로 리다이렉트
		}
		return mav;
	}
	// http://localhost:8080/bob/member/listMembers.do
	// request 객체에서 URL 요청명을 가져와 .do를 제외한 요청을 구함

	@Override
	@RequestMapping(value = "/member/logout.do", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		// 로그아웃 요청시 세션에 저장된 로그인 정보와 회원 정보 삭제함
		session.removeAttribute("member");
		session.removeAttribute("isLogOn");

		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/member/loginForm.do");
		return mav;
	}

}
