package kr.co.ezenac.di03;

public class MemberServiceImpl implements MemberService {
	
	// 주입되는 빈을 저장할 MemberDAO 타입의 속성 선언
	private MemberDAO memberDAO;
	
	// 설정파일에서 memberDAO빈을 생성한 후 setter로 속성 memberDAO에 주입함.
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	
	@Override
	public void listMember() {
		// TODO Auto-generated method stub
		memberDAO.listMembers();			// 주입된 빈을 이용해 listMembers() 호출
	}

}
