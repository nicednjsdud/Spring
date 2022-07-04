package kr.co.mybatis.orm04;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import kr.co.mybatis.orm01.MemberDTO;

public class MemberDAO {

	public static SqlSessionFactory sqlMapper = null;

	public static SqlSessionFactory getInstance() {
		if (sqlMapper == null) {
			String resource = "mybatis/sqlMapConfig.xml"; // 설정정보를 읽어 후 DB 연동 준비

			try {
				Reader reader = Resources.getResourceAsReader(resource);

				// 마이바티스를 이용하는 SqlSessionFactory 객체를 가져옴
				sqlMapper = new SqlSessionFactoryBuilder().build(reader);
				reader.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return sqlMapper;
	}

	// select
	public List<MemberDTO> selectAllMemberList() {
		sqlMapper = getInstance();

		// member.xml의 SQL문을 호출하는 데 사용되는 SqlSession 객체 가져옴
		SqlSession session = sqlMapper.openSession();

		// 여러 개 레코드를 조회하므로 selectList() 실행
		List<MemberDTO> memList = session.selectList("mapper.member.selectAllMemberList");
		return memList;
	}

	// select name
	public String selectName() {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		String name = session.selectOne("mapper.member.selectName");
		return name;
	}

	// select pwd
	public String selectPwd() {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		String pwd = session.selectOne("mapper.member.selectPwd");
		return pwd;
	}

	// select id
	public MemberDTO selectMemberById(String id) {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		MemberDTO memberDTO = session.selectOne("mapper.member.selectMemberById", id);
		return memberDTO;
	}

	// select pwd
	public List<MemberDTO> selectMemberByPwd(int pwd) {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		List<MemberDTO> membersList = session.selectList("mapper.member.selectMemberByPwd", pwd);
		return membersList;
	}

	// insert
	public int insertMember(MemberDTO memberDTO) {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		int result = session.insert("mapper.member.insertMember", memberDTO); // memberDTO의 값을 전달하여 회원정보 테이블에 추가함.
		session.commit(); // 수동 커밋이므로 반드시 commit() 메서드 호출
		return result;
	}

	public int insertMember2(Map<String, String> memberMap) {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		int result = session.insert("mapper.member.insertMember2", memberMap); // 메서드로 전달된 HashMap을 다시 SQL문으로 반환
		session.commit(); // 수동 커밋이므로 반드시 commit() 메서드 호출
		return result;
	}

	// update
	public int updateMember(MemberDTO memberDTO) {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		// SqlSession의 update() 메서드 이용
		int result = session.update("mapper.member.updateMember", memberDTO);
		session.commit();
		return result;
	}
	
	// delete
	public int deleteMember(String id) {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		
		int result = session.delete("mapper.member.deleteMember",id);
		session.commit();
		return result;
	}
}
















