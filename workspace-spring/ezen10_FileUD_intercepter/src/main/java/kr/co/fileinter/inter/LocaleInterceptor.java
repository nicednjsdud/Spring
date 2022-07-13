package kr.co.fileinter.inter;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

// 사용자정의 인터셉터는 반드시 HandleriterceptorAdapter를 상속받아야 함
public class LocaleInterceptor extends HandlerInterceptorAdapter {
	
	//컨트롤러 실행 전 호출 함
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 브라우저에서 전달한 locale 정보 가져옴
		String locale =  request.getParameter("locale");
		// 최초 요청시 locale을 한국어로 설정함
		if(locale==null)
			locale ="ko";
		
		HttpSession session = request.getSession();
		
		// LOCALE 속성값을 세션에 저장해 SessionLovaleResolver가 사용할수 있게함.
		session.setAttribute("org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE", new Locale(locale));
		return true;
	}
	
	// 컨트롤러 실행 후 호출됨
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}
	
	// 뷰(jsp)를 수행한 후 호출됨
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}
}
