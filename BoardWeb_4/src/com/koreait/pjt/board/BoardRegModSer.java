package com.koreait.pjt.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.pjt.Const;
import com.koreait.pjt.ViewResolver;
import com.koreait.pjt.db.BoardDAO;
import com.koreait.pjt.vo.BoardVO;
import com.koreait.pjt.vo.UserVO;

// '/' 를 붙이면 마지막 끝의 주소값만 변경
// 쿼리스트링 : 클라이언트가 값을 보낼 때 사용


@WebServlet("/board/regmod")
public class BoardRegModSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	//등록창, 수정창
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession hs = request.getSession();
		if(hs.getAttribute(Const.LOGIN_USER) == null) {
			response.sendRedirect("/login");
			return;
		}
		
		//<a href="/board/regmod?i_board=${data.i_board}">
		String strI_board = request.getParameter("i_board");
		
		if(strI_board != null) { //수정창
			int i_board = Integer.parseInt(strI_board); 
			
			BoardVO param = new BoardVO();
			param.setI_board(i_board);
			
			BoardVO data = BoardDAO.detailBoard(param);
			request.setAttribute("data", data);
		}
		
		ViewResolver.forword("board/regmod", request, response); 
	}

	//DB의 등록과 수정 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
			String title = request.getParameter("title");
			String ctnt = request.getParameter("ctnt");
			String stri_board = request.getParameter("i_board");
			
			HttpSession hs = request.getSession();
			UserVO loginUser = (UserVO) hs.getAttribute(Const.LOGIN_USER);
				
			BoardVO param = new BoardVO();
			
			param.setTitle(title);
			param.setCtnt(ctnt);
			param.setI_user(loginUser.getI_user());
			
			int result = 0;
			
			if(stri_board != "") { //수정
				param.setI_board(Integer.parseInt(stri_board));
				result = BoardDAO.upBoard(param);
			} else { //등록
				result = BoardDAO.insBoard(param);
			}
			
			response.sendRedirect("/board/list");
	}
}
