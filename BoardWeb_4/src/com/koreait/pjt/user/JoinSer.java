package com.koreait.pjt.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.pjt.MyUtils;
import com.koreait.pjt.ViewResolver;
import com.koreait.pjt.db.UserDAO;
import com.koreait.pjt.vo.UserVO;


@WebServlet("/join")
public class JoinSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get은 화면 띄울 때(select만 get에서 처리)
		
		ViewResolver.forword("user/join", request, response); //user폴더안에 join jsp파일
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//post는 화면 처리
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		String encrypt_pw = MyUtils.encryptString(user_pw);
		String user_name = request.getParameter("user_name");
		String user_email = request.getParameter("user_email");
		
		UserVO param = new UserVO();
		param.setUser_id(user_id);
		param.setUser_pw(encrypt_pw);
		param.setUser_name(user_name);
		param.setUser_email(user_email);
		
		int result = UserDAO.insUser(param);
		System.out.println("result : " + result);
		
		if(result != 1) {
			request.setAttribute("msg", "에러가 발생했습니다.");
			request.setAttribute("data", param);
			
			doGet(request, response);
			return;
		}
		response.sendRedirect("/login");
	} 

}
