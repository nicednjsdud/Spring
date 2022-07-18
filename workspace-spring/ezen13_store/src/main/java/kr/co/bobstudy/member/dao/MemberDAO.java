package kr.co.bobstudy.member.dao;

import org.springframework.dao.DataAccessException;

public interface MemberDAO {

	public String selectDuplicateCheck(String id) throws DataAccessException;

}
