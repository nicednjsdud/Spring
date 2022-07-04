package kr.co.ezenac.simpleurl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/*
 * 1) http://localhost:8080/ezenac/url/index.do로 요청함
 * 2) DispatcherServlet은 요청에 대해 미리 action-servlet.xml에 
 *    매핑된 SimpleUrlController로 요청함
 * 3) 컨트롤러는 요청에 대해 
 * 
 */

public class SimepleUrlController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return new ModelAndView("index.jsp");
	}

}
