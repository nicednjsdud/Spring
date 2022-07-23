package kr.co.bobstudy.member.service;

import java.util.Map;

import org.springframework.dao.DataAccessException;

public interface MemberService {

	public String duplicateCheck(String id) throws Exception;
	public int addMember(Map memberMap) throws DataAccessException;
}
