package com.koreait.pjt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewResolver {
	public static void forword(String fileNm, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String jsp = String.format("/WEB-INF/jsp/%s.jsp", fileNm);
		request.getRequestDispatcher(jsp).forward(request, response);
		
	}
	
	//로그인 체크
	public static void forwordLoginChk(String fileNm, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(MyUtils.isLogout(request)) {
			response.sendRedirect("/login"); //로그인이 안되어 있을 때
			return;
		}
		ViewResolver.forword(fileNm, request, response);
	}

}
