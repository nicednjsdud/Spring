package kr.co.transaction.account;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/*
 * 	서비스 계층
 * 		- 애플리케이션 비즈니스 로직과 비즈니스와 관련된 도메인 적합성 검증
 * 		- 트랜잭션 처리
 * 		- 프리젠테이션 계층과 데이터 엑세스 계층 사이를 연결하는 역할
 * 		  두 계층이 직접적으로 통신하지 않게 함.
 */

// @Transcational 이용해 AccountSerivice 클래스의 모든 메서드를에 트랜잭션을 적용함
// 웹 어플리케이션의 트랜잭션은 대부분 서비스 클래스에 적용함
@Transactional(propagation = Propagation.REQUIRED)
public class AccountService {

	private AccountDAO accDAO;
	public void setAccDAO(AccountDAO accDAO) {
		this.accDAO = accDAO;
	}
	//sendMoney 호출 시 두개의 쿼리문 실행함
	public void sendMoney() {
		accDAO.updateBalance1();
		accDAO.updateBalance2();
	}
	
}
