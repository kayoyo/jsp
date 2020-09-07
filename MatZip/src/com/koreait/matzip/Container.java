package com.koreait.matzip;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/") // /res/* 제외한 /는 여기서 사용됨
public class Container extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private HandlerMapper mapper;
	
	public Container() { //기본생성자, tomcat에서 Container실행
		mapper = new HandlerMapper(); 
	}
	
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		proc(request, response);	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		proc(request, response);	
	}
	
	private void proc (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String temp = mapper.nav(request);
		
		if(temp.indexOf("/") >= 0 && "redirect:".equals(temp.substring(temp.indexOf("/")))) {			
			response.sendRedirect(temp.substring(temp.indexOf("/")));
			return;
		}
		
//		if(temp.indexOf("/") >= 0) {
//			String isRedirect = temp.substring(0, temp.indexOf("/"));
//			System.out.println("isRedirect : " + isRedirect);
//			
//			if("redirect:".equals(isRedirect)) {
//				response.sendRedirect(temp.substring(temp.indexOf("/")));
//				return;
//			
//			}
//		}
		
		switch(temp) {
		case "405" :
			temp = "/WEB-INF/view/error.jsp";
			break;
		case "404" :
			temp = "/WEB-INF/view/notFound.jsp";
			break;
		}
		//temp는 default.jsp 리턴(TEMP_DEFAULT: /WEB-INF/view/template/default.jsp)
		request.getRequestDispatcher(temp).forward(request, response);
	}
	

}
