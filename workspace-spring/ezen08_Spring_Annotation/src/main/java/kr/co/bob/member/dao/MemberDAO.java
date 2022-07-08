package kr.co.bob.member.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import kr.co.bob.member.dto.MemberDTO;

public interface MemberDAO {
	public List<MemberDTO> selectAllMemberList() throws DataAccessException;

	public int insertMember(MemberDTO memberDTO) throws DataAccessException;

	public int removeMember(String id) throws DataAccessException;
}
