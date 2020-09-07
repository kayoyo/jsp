package com.koreait.matzip.user;

import javax.servlet.http.HttpServletRequest;

import com.koreait.matzip.Const;
import com.koreait.matzip.ViewRef;

public class UserController {
	
	//    / user / login : 0 / 1 / 2
	public String login(HttpServletRequest request) {
		//화면노출용
		request.setAttribute(Const.TITLE, "로그인"); //TITLE : jsp title 값
		request.setAttribute(Const.VIEW, "user/login"); //VIEW : 어떤 파일을 불러 올 것 인지
		return ViewRef.TEMP_DEFAULT; // "/WEB-INF/view/template/default.jsp" 
	}
	
	public String join(HttpServletRequest request) {
		request.setAttribute(Const.TITLE, "회원가입"); 
		request.setAttribute(Const.VIEW, "user/join");
		return ViewRef.TEMP_DEFAULT;
	}
	
	public String joinProc(HttpServletRequest request) {
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		String nm = request.getParameter("nm");
		
		return "redirect:/user/login";
		
	}

}
