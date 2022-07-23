package kr.co.ezenac.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import kr.co.ezenac.member.dao.MemberDAO;
import kr.co.ezenac.member.dto.MemberDTO;

// MemberSeriveceImpl 클래스를 이용해 id가 memberService인 빈을 자동 생성함
@Service("memberService")
@Transactional(propagation=Propagation.REQUIRED)
public class MemberServiceImpl implements MemberService {
	
	// id가 memberDAO인 빈을 자동 주입함
	@Autowired
	private MemberDAO memberDAO;
	
	@Override
	public List<MemberDTO> listMembers() throws DataAccessException {
		List<MemberDTO> memberList = memberDAO.selectAllMemberList();
		return memberList;
	}
	@Override
	public int addMember(MemberDTO memberDTO) throws DataAccessException {		
		return memberDAO.insertMember(memberDTO);
	}
	@Override
	public int removeMember(String id) throws DataAccessException {
		return memberDAO.removeMember(id);
	}
	@Override
	public MemberDTO login(MemberDTO memberDTO) throws DataAccessException {
		return memberDAO.loginById(memberDTO);
	}
	
}
