package kr.co.bobstudy.member.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository("memberDAO")
public class MemberDAOImpl implements MemberDAO{
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public String selectDuplicateCheck(String id) throws DataAccessException {
		String result = sqlSession.selectOne("mapper.member.duplicatecheck",id);
		return result;
	}

}
