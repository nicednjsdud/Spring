package kr.co.tiles.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import kr.co.tiles.member.dto.MemberDTO;

// id가 memberDAO인 빈을 자동 주입함
@Repository("memberDAO")
public class MemberDAOImpl implements MemberDAO {
	
	// id가 sqlSession인 빈을 자동 주입함
	@Autowired
	private SqlSession sqlSession;


	@Override
	public List<MemberDTO> selectAllMemberList() throws DataAccessException {
		List<MemberDTO> membersList = sqlSession.selectList("mapper.member.selectAllMemberList");
		return membersList;
	}

	@Override
	public int insertMember(MemberDTO memberDTO) throws DataAccessException {
		int result = 0;
		result = sqlSession.insert("mapper.member.insertMember2", memberDTO);
		return result;
	}

	@Override
	public int removeMember(String id) throws DataAccessException {
		// 주입된 sqlSession 빈으로 delete()문 호출하면서.
		int result = 0;
		result = sqlSession.delete("mapper.member.deleteMember", id);

		return result;
	}
}
