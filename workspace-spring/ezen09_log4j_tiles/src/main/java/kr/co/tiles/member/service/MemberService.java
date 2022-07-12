package kr.co.tiles.member.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import kr.co.tiles.member.dto.MemberDTO;



public interface MemberService {
	public List<MemberDTO> listMembers() throws DataAccessException;
	public int addMember(MemberDTO memberDTO) throws DataAccessException;
	public int removeMember(String id) throws DataAccessException;
	public MemberDTO login(MemberDTO memberDTO) throws DataAccessException;
}
