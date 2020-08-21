package com.koreait.project;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/")
//서블릿 하나로 사용 : 관리가 편함
public class MainSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		proc(request, response, RequestMethod.GET);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		proc(request, response, RequestMethod.POST);
	}
	
	private void proc(HttpServletRequest request, HttpServletResponse response, int method) throws ServletException, IOException{
		
		System.out.println("url : " + request.getRequestURI() + ", method=" + method);
	}

}
