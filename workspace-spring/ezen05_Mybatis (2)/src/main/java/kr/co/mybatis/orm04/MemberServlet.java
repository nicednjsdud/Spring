package kr.co.mybatis.orm04;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.mybatis.orm01.MemberDTO;

@WebServlet("/mem4.do")
public class MemberServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");

		MemberDAO dao = new MemberDAO();
		MemberDTO memberDTO = new MemberDTO();

		String action = request.getParameter("action");
		String forwardPage = "";

		// selctMember all
		if (action == null || action.equals("listmembers")) {
			List<MemberDTO> membersList = dao.selectAllMemberList();
			request.setAttribute("membersList", membersList);
			forwardPage = "orm03/listMembers.jsp";
		}
		// selectMemberById
		else if (action.equals("selectMemberById")) {
			String id = request.getParameter("value");
			memberDTO = dao.selectMemberById(id);
			request.setAttribute("member", memberDTO);
			forwardPage = "orm02/memberInfo.jsp";
		}
		// selectMemberByPwd
		else if (action.equals("selectMemberByPwd")) {
			int pwd = Integer.parseInt(request.getParameter("value"));
			List<MemberDTO> membersList = dao.selectMemberByPwd(pwd);
			request.setAttribute("membersList", membersList);
			forwardPage = "orm03/listMembers.jsp";
		}
		// insertMember
		else if (action.equals("insertMember")) {
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");

			memberDTO.setId(id);
			memberDTO.setPwd(pwd);
			memberDTO.setName(name);
			memberDTO.setEmail(email);

			// 회원 가입창에 전송된 회원정보를 memberDTO에 설정한 후 insertMember()메서드로 전달함
			dao.insertMember(memberDTO);
			forwardPage = "/mem4.do?action=listmembers";
		}
		// insertMember2
		else if (action.equals("insertMember2")) {
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");

			// 회원 가입창에서 전송된 회원 정보를 HashMap에
			// key/value로 저장한 후 MemberDAO의 insertMember2()의 인자로 전달함.
			Map<String, String> memberMap = new HashMap<>();
			memberMap.put("id", id);
			memberMap.put("pwd", pwd);
			memberMap.put("name", name);
			memberMap.put("email", email);
			dao.insertMember2(memberMap);
			forwardPage = "mem4.do?action=listmembers";
		}
		// updateMember
		else if (action.equals("updateMember")) {
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");

			memberDTO.setId(id);
			memberDTO.setPwd(pwd);
			memberDTO.setName(name);
			memberDTO.setEmail(email);
			dao.updateMember(memberDTO);
			forwardPage = "mem4.do?action=listmembers";
		}
		// deleteMember
		else if (action.equals("deleteMember")) {
			String id = request.getParameter("id");
			dao.deleteMember(id);
			forwardPage = "mem4.do?action=listmembers";
		}
		request.getRequestDispatcher(forwardPage).forward(request, response);

	}
}
