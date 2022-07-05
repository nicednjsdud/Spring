package kr.co.springmybatis.service;

import kr.co.springmybatis.dao.MemberDAO;

public class MemberSeriviceImpl implements MemberService {
	
	private MemberDAO memberDAO;
	
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
}
