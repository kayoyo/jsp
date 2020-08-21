package com.koreait.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.board.db.BoardDAO;
import com.koreait.board.param.Utils;
import com.koreait.board.vo.BoardVO;


@WebServlet("/boardWrite")
public class BoardRegmodSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//화면 노출
		//1. jsp 파일 불러오기
		String jsp = "/WEB-INF/view/boardRegmod.jsp";
		request.getRequestDispatcher(jsp).forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//화면 작업 처리
		//1. 폼에 입력되는 값을 가져옴(key값)
		String title = request.getParameter("title"); 
		String ctnt = request.getParameter("ctnt");
		String strI_board = request.getParameter("i_board");
		String strI_student = request.getParameter("i_student");
		
//		System.out.println("title" + title);
//		System.out.println("ctnt" + ctnt);
//		System.out.println("i_board" + strI_board);
//		System.out.println("i_student" + strI_student );
//		 
		
		//2. BoardVO param2 객체에 그 값을 넣기
		BoardVO param2 = new BoardVO();
		
		param2.setI_board(Utils.parseStrToInt(strI_board, 0));
		param2.setTitle(title);
		param2.setCtnt(ctnt);
		param2.setI_student(Utils.parseStrToInt(strI_student,0));
		//Utils.parseStrToInt(strI_student,0) 먼저 실행되고 결과물이 setI_student에 담기게 된다.
		
		//3. param2의 값을 result에 넣기 : 1이 넘어오면 글이 작성이 된 것 
		int result = BoardDAO.insBoard(param2);
		
		if(result == 1) {//정상적으로 글이 등록됨	
			response.sendRedirect("/boardList");
		} else {
			request.setAttribute("msg", "에러가 발생하였습니다.");
			doGet(request, response);
		}
		
	}

}
