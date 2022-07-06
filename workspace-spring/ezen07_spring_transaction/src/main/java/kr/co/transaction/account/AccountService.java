package kr.co.transaction.account;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED)
public class AccountService {
	
	private AccountDAO accDAO;
	public void setAccDAO(AccountDAO accDAO) {
		this.accDAO = accDAO;
	}
}
