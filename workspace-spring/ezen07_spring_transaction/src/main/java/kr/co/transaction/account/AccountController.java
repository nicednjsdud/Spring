package kr.co.transaction.account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public class AccountController {
	private AccountService accService;
	
	public void setAccService(AccountService accService) {
		this.accService = accService;
	}
	public ModelAndView sendMoney(HttpServletRequest request,HttpServletResponse response) throws Exception{
		ModelAndView mav = new ModelAndView();
		
		
		return mav;
	}
}
