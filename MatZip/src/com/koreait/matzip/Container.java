package com.koreait.matzip;

import java.io.IOException;
import java.io.PrintWriter;

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
		
		String temp = mapper.nav(request); //템플릿 파일명이 담김
		
		//"/"가 없으면 index of -1 : if문 들어가지 않음 > switch문 실행 : 404/405 에러의 경우 > 둘다 아니라면 템플릿의 파일명 
		if(temp.indexOf(":") >= 0 ) {
			String prefix = temp.substring(0, temp.indexOf(":"));
			String value = temp.substring(temp.indexOf(":") + 1);
			
			//System.out.println("prefix: " + prefix);
			//System.out.println("value: " + value);
			
			if("redirect".equals(prefix)) {
				//System.out.println("sub : " + temp.substring(0, temp.indexOf("/")));
				response.sendRedirect(value);
				return;
				
			} else if("ajax".equals(prefix)) {
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/json");
				PrintWriter out = response.getWriter();
				
				//System.out.println("value : " + value);
				out.print(value);
				return;
		}
	}
		switch(temp) {
		case "405" :
			temp = "/WEB-INF/view/error.jsp";
			break;
		case "404" :
			temp = "/WEB-INF/view/notFound.jsp";
			break;
		}
		//temp는 if, switch가 완료 된 후의 값
		request.getRequestDispatcher(temp).forward(request, response);
	}
	

}
