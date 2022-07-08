package kr.co.transaction.account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class AccountController extends MultiActionController {

	private AccountService accService;
	
	public void setAccService(AccountService accService) {
		this.accService = accService;
	}
	
	public ModelAndView sendMoney(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("here");
		ModelAndView mav = new ModelAndView();
		accService.sendMoney();		// 금액 이체함.
		mav.setViewName("result");
		return mav;
	}
}
