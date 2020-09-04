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


@WebServlet("/changePW")
public class ChangePwSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			ViewResolver.forwordLoginChk("user/changePW", request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type"); //changePW에서 name값 type와 value값 1 
		String passWord = request.getParameter("passWord");
		String encryptPw = MyUtils.encryptString(passWord);
		
		
		UserVO loginUser = MyUtils.getLoginUser(request);
		UserVO param = new UserVO();
		
		switch(type) {
		case "1" : //현재 비밀번호 확인
			param.setUser_id(loginUser.getUser_id());
			param.setUser_pw(encryptPw);
		
			int result = UserDAO.login(param); 
			
			if(result == 1) {
				request.setAttribute("isAuth", true);
			} else {
				request.setAttribute("msg", "비밀번호를 확인 해 주세요");
			}
			
			doGet(request, response);
			break;
			
		case "2" : //비밀번호 변경
			
			String rePassWordCon = request.getParameter("rePassWordCon");
			String encryptPw2 = MyUtils.encryptString(rePassWordCon);
			
			param.setI_user(loginUser.getI_user());
			param.setUser_pw(encryptPw2);
			
			UserDAO.upUser(param); 
			
			response.sendRedirect("/profile?proc=1");
			break;
			
			}
			
		}
}
	

