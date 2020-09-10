package com.koreait.matzip.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.koreait.matzip.CommonUtils;
import com.koreait.matzip.Const;
import com.koreait.matzip.ViewRef;
import com.koreait.matzip.vo.UserVO;

public class UserController {
	
	private UserService service;
	
	public UserController() {
		service = new UserService();
	}
	
	//    / user / login : 0 / 1 / 2
	public String login(HttpServletRequest request) {
		//화면노출용
		String error = request.getParameter("error");
		if(error != null) {
		switch(error) {
		case "2" : 
			request.setAttribute("msg", "아이디를 확인 해 주세요");
			break;
		case "3" : 
			request.setAttribute("msg", "비밀번호를 확인 해 주세요");
			break;
		}
		}
		
		request.setAttribute(Const.TITLE, "로그인"); //TITLE : jsp title 값
		request.setAttribute(Const.VIEW, "user/login"); //VIEW : 어떤 파일을 불러 올 것 인지
		return ViewRef.TEMP_DEFAULT; // "/WEB-INF/view/template/default.jsp" 
	
		}
	
	//로그인 처리
	public String loginProc(HttpServletRequest request) {
		
		String user_id = request.getParameter("user_id"); //key값
		String user_pw = request.getParameter("user_pw"); //key값
		
		UserVO param = new UserVO(); //가장 신뢰적인 방법(UserVO의 주소값만 들어가기 때문)
		param.setUser_id(user_id);
		param.setUser_pw(user_pw);
		
		int result = service.login(param); //오류숫자값을 받음(UserVO param)
		
		if(result == 1) { //로그인이 됨
			//로그인이 성공되면 세션에 남겨줘야 로그인 상태가 계속 유지됨
			HttpSession hs = request.getSession();
			hs.setAttribute(Const.LOGIN_USER, param);
			return "redirect:/restaurant/restMap";
			
		} else { //2 ~ 3이면 error 메세지 출력 > login 창으로 이동
			return "redirect:/user/login?user_id=" + user_id + "&error=" + result;
		}	
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
		
		UserVO param = new UserVO();
		param.setUser_id(user_id);
		param.setUser_pw(user_pw);
		param.setNm(nm);
		
		int result = service.join(param);
		
		return "redirect:/user/login";	
	}
	
	public String ajaxIdChk(HttpServletRequest request) {
		String user_id = request.getParameter("user_id");
		UserVO param = new UserVO();
		param.setUser_id(user_id);
		param.setUser_pw("");
		
		int result = service.login(param);
				
		return String.format("ajax:{\"result\":%d}", result); 
	}
	
	public String logOut(HttpServletRequest request) {
		HttpSession hs = request.getSession();
		hs.invalidate();
		return "redirect:/user/login";
		
	}
	}

	
	

