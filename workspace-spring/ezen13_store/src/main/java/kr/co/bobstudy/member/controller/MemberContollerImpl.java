package kr.co.bobstudy.member.controller;

import java.io.File;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	@RequestMapping(value = "/duplicateCheck.do", method = { RequestMethod.POST })
	public ResponseEntity<String> duplicateCheck(@RequestParam("id") String id, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String result = memberService.duplicateCheck(id);
		ResponseEntity<String> responseEntity = new ResponseEntity<>(result,HttpStatus.OK);
		return responseEntity;
	}
	
	@Override
	@RequestMapping(value = "/addMember.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity addMember(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		Map memberMap = new HashMap();
		Enumeration enun = request.getParameterNames();
		while (enun.hasMoreElements()) {
			String name = (String) enun.nextElement();
			String value = request.getParameter(name);
			memberMap.put(name, value);
		}
		
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		try {

			int result = 0;
			result = memberService.addMember(memberMap);
			message = "<script>";
			message += "alert('새글을 추가했습니다.');";
			message += "location.href='" +request.getContextPath() + "/main/main.do';";
			message += "</script>";

			// 새 글을 추가한 후 메세지를 전달함
			resEnt = new ResponseEntity<String>(message, responseHeaders, HttpStatus.CREATED);

		} catch (Exception e) {
			
			message = "<script>";
			message += "alert('오류가 발생했습니다. 다시 시도해 주세요.');";
			message += "location.href='" +request.getContextPath() + "/main/main.do';";
			message += "</script>";

			resEnt = new ResponseEntity<String>(message, responseHeaders, HttpStatus.CREATED);
		}

		return resEnt;
		
	}
	@RequestMapping(value = "/member/*Form.do", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView form(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}
}
