package kr.co.mybatis.orm01;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;

public class MemberDAO {
	
	public static SqlSessionFactory sqlMapper = null;

	public static SqlSessionFactory getInstance() {
		if (sqlMapper == null) {
			// 설정정보를 읽어들인 후 DB 연동 준비
			String resource = "/sqlMapConfig.xml";

			try {
				Reader reader = Resources.getResourceAsReader(resource);
				
				// 마이바티스를 이용하는 SqlSessionFactory 객체를 가져옴
				sqlMapper = new SqlSessionFactoryBuilder().build(reader);
				reader.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sqlMapper;
	}
	
	public List<MemberDTO> selectAllMemberList(){
		sqlMapper = getInstance();
		
		// member.xml의 SQL문을 호출하는 데 사용되는 SqlSession 객체 가져옴 
		SqlSession session = sqlMapper.openSession();
		
		// 여러개 레코드를 조회하므로 selectList() 실행
		List<MemberDTO> memList = session.selectList("mapper.member.selectAllMemberList");
		return memList;
	}
}
