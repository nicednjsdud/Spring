package kr.co.bobstudy.member.dao;

import java.util.Map;

import org.springframework.dao.DataAccessException;

public interface MemberDAO {

	public String selectDuplicateCheck(String id) throws DataAccessException;

	public int insertMember(Map memberMap)throws DataAccessException;
}
