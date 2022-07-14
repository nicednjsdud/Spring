package kr.co.board.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

// 사용자정의 인터셉터는 반드시 HandleriterceptorAdapter를 상속받아야 함
public class ViewNameInterceptor extends HandlerInterceptorAdapter {

	// 컨트롤러 실행 전 호출 함
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String viewName = getViewName(request);
		request.setAttribute("viewName", viewName);
		
		return true;
	}
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
