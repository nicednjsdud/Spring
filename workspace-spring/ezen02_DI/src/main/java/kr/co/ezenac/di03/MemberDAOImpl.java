package kr.co.ezenac.di03;

public class MemberDAOImpl implements MemberDAO {
	
	@Override
	public void listMembers() {
		System.out.println("memberDAO 호출");		
	}
}
