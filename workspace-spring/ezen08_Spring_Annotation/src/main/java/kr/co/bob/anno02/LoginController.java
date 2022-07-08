package kr.co.bob.anno02;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller("loginController")		// 컨트롤러 빈을 자동으로 생성
public class LoginController {
	
	// /anno/loginForm.do 와 /anno/loginForm2.do로 요청시 loginForm()을 호출
	@RequestMapping(value = {"/anno/loginForm.do","/anno/loginForm2.do"},method= {RequestMethod.GET})
	public ModelAndView loginForm(HttpServletRequest request,HttpServletResponse response) throws Exception{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("loginForm");
			
		return mav;
	}
	// GET 방식과 POST방식 요청시 모두 처리
	@RequestMapping(value="/anno/login.do", method = {RequestMethod.GET ,RequestMethod.POST})
	public ModelAndView login(HttpServletRequest request,HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("result");
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		mav.addObject("userId",userId);
		mav.addObject("userName",userName);
		return mav;
	}
	/*
	 *  @RequestParam을 이용해 매개변수가 userId이면 그값을 변수 userId에 자동으로 설정
	 *  @RequestParam을 이용해 매개변수가 userName이면 그값을 변수 userName에 자동으로 설정
	 */
	@RequestMapping(value= "/anno/login2.do", method = {RequestMethod.GET ,RequestMethod.POST})
	public ModelAndView login2(@RequestParam("userId") String userId,@RequestParam("userName") String userName,
				HttpServletRequest request,HttpServletResponse response)throws IOException{
		request.setCharacterEncoding("UTF-8");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("result");
		mav.addObject("userId",userId);
		mav.addObject("userName",userName);
		return mav;
	}
	@RequestMapping(value= "/anno/login2_.do", method = {RequestMethod.GET ,RequestMethod.POST})
	public ModelAndView login2_(@RequestParam("userId") String userId,
							    @RequestParam(value="userName",required=true)String userName,
								@RequestParam(value="userEmail",required=true)
				HttpServletRequest request,HttpServletResponse response)throws IOException{
		request.setCharacterEncoding("UTF-8");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("result");
		mav.addObject("userId",userId);
		mav.addObject("userName",userName);
		return mav;
	}
	/*
	 *  @RequestParam 이용해 Map에 전송된 매개변수 이름을 key, 값이 value로 저장
	 */
	@RequestMapping(value= "/anno/login3.do", method = {RequestMethod.GET ,RequestMethod.POST})
	public ModelAndView login3(@RequestParam Map<String,String> info,
			HttpServletRequest request,HttpServletResponse response)throws IOException{
		
		request.setCharacterEncoding("UTF-8");
		ModelAndView mav = new ModelAndView();
		
//		// Map에 저장된 매개변수 이름으로 전달된 값을 가져옴
//		String userId = info.get("userId");
//		String userName = info.get("userName");
		
		// @RequestParam에서 설정한 Map 이름으로 바인딩함.
		mav.addObject("info",info);
		mav.setViewName("result");
		
		return mav;
	}
	
}
