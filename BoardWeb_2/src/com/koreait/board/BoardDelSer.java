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


@WebServlet("/boardDel")
public class BoardDelSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
    

  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String strI_board = request.getParameter("i_board");
		int i_board = Utils.parseStrToInt(strI_board, 0);
		
		
		if(i_board == 0) {//잘못된 값을 보냄(문자열 섞여 있었음)
			response.sendRedirect("/boardList");
			return;
		}
		
		int result = BoardDAO.delBoard(i_board);
		
		if(result == 1) {
			response.sendRedirect("/boardList");
		} else {
			response.sendRedirect("/errPage?err=1&target=boardList");
		}
		
		
//		//delBoard 메소드 가져오기
//		BoardVO param3 = new BoardVO();
//		BoardDAO.delBoard(param3);
//		
//		//param3의 getI_board()에 가져와서 파싱한 i_board 값 넣어주기
//		param3.setI_board(i_board);
//		
//		response.sendRedirect("/boardList");
//		
	}

	

}
