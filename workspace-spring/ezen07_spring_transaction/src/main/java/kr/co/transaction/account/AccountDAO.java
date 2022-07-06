package kr.co.transaction.account;

import org.apache.ibatis.session.SqlSession;

public class AccountDAO {
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
}
