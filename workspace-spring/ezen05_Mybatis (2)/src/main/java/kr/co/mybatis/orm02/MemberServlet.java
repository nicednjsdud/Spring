package kr.co.mybatis.orm02;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.mybatis.orm01.MemberDTO;

@WebServlet("/mem2.do")
public class MemberServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		MemberDAO dao = new MemberDAO();
		//String name = dao.selectName();
		String pwd = dao.selectPwd();

		PrintWriter pw = response.getWriter();
		pw.write("<script>");
		//pw.write("alert('이름 : "+name+" ')");
		pw.write("alert('패스워드 : "+pwd+" ')");
		pw.write("</script>");
		
	}
}















