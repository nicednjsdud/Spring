package kr.co.transaction.account;

import org.apache.ibatis.session.SqlSession;
import org.springframework.dao.DataAccessException;

public class AccountDAO {

	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	// 첫번째 update문 실행 (정원영 계좌에서 5000000원을 차감함)
	public void updateBalance1() throws DataAccessException{
		sqlSession.update("mapper.account.updateBalance1");
	}
	// 두번쨰 update문 실행 (BOB 계좌에서 5000000원을 증액함) 
	public void updateBalance2() throws DataAccessException{
		sqlSession.update("mapper.account.updateBalance2");
	}
	
}
