package com.koreait.board;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.board.db.BoardDAO;
import com.koreait.board.db.Dbconn;
import com.koreait.board.vo.BoardVO;

@WebServlet("/boardList") //주소값 랩핑(이 주소로 들어오면 doGet or doPost 방식으로 작동시킨다는 의미 ) << 이 곳에 기재하지 않으면 xml에 적어줘야 함
public class BoardlistSer extends HttpServlet { //BoardlistSer에서 HttpServlet를 사용할 수 있다. (상속)
	private static final long serialVersionUID = 1L; 
       
    
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		//데이터베이스에서 나온 결과물을 request에 담아서 jsp파일로 이동
		//RequestDispatcher은 jsp파일로 옮길 때 사용, 주소값을 바꾸지 않고 이동(servlet에서 jsp파일)
		//sendRedirect는 페이지를 이동할 때 데이터를 가지고 가지 않음(request, response는 소멸되고 새로운 url형성), 무조건 주소값을 get방식으로 전송 
		List<BoardVO> list = BoardDAO.selBoardList();
		request.setAttribute("date", list); //request.getAttribute()의 리턴 타입은 object
		
		
		RequestDispatcher rd= request.getRequestDispatcher("/WEB-INF/view/BoardList.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		doGet(request, response); 
	}

}
