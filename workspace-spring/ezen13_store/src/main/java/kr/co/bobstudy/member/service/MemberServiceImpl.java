package kr.co.bobstudy.member.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import kr.co.bobstudy.member.dao.MemberDAO;

@Service("memberService")
@Transactional(propagation = Propagation.REQUIRED)
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberDAO memberDAO;

	@Override
	public String duplicateCheck(String id) throws Exception {
		return memberDAO.selectDuplicateCheck(id);
	}

	@Override
	public int addMember(Map memberMap) throws DataAccessException {
		return memberDAO.insertMember(memberMap);
	}
}
