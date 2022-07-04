package kr.co.mybatis.orm03;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.mybatis.orm01.MemberDTO;

@WebServlet("/mem3.do")
public class MemberServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		MemberDAO dao = new MemberDAO();
		MemberDTO memberDTO = new MemberDTO();
		
		String action = request.getParameter("action");
		String forwardPage = "";
		
		if (action == null || action.equals("listmembers")) {
			List<MemberDTO> membersList = dao.selectAllMemberList();
			request.setAttribute("membersList", membersList);
			forwardPage = "orm02/listMembers.jsp";
		}
		else if (action.equals("selectMemberById")) {
			String id = request.getParameter("value");
			memberDTO = dao.selectMemberById(id);
			request.setAttribute("member", memberDTO);
			forwardPage = "orm02/memberInfo.jsp";
		}
		else if (action.equals("selectMemberByPwd")) {
			 int pwd = Integer.parseInt(request.getParameter("value"));
			 List<MemberDTO> membersList = dao.selectMemberByPwd(pwd);
			 request.setAttribute("membersList", membersList);
			 forwardPage = "orm02/listMembers.jsp";
		}
		
		request.getRequestDispatcher(forwardPage).forward(request, response);
		
	}
}















