package com.koreait.pjt.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.pjt.db.BoardCmtDAO;
import com.koreait.pjt.db.BoardDAO;
import com.koreait.pjt.vo.BoardCmtVO;
import com.koreait.pjt.vo.BoardVO;
import com.koreait.pjt.vo.UserVO;
import com.koreait.pjt.Const;
import com.koreait.pjt.MyUtils;
import com.koreait.pjt.ViewResolver;


@WebServlet("/board/detail")
public class BoardDetailSer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserVO loginUser = MyUtils.getLoginUser(request);
		
		HttpSession hs = request.getSession();
		if(hs.getAttribute(Const.LOGIN_USER) == null) {
			response.sendRedirect("/login");
			return;
		}
		
		String strI_board = request.getParameter("i_board");
		int i_board = Integer.parseInt(strI_board);
		
		if(i_board == 0) {
			response.sendRedirect("/board/list");
			return;
		}
	
		BoardVO param = new BoardVO();
		param.setI_board(i_board);
		param.setI_user(loginUser.getI_user());
		
		//단독으로 조회수 올리기 방지! -- [start] 
		ServletContext application = getServletContext(); //어플리케이션 내장객체 얻어오기
		Integer readI_user = (Integer) application.getAttribute("read_" + strI_board);
		
//		System.out.println("if문 바깥 readI_user : " + readI_user);
//		System.out.println("if문 바깥 loginuser : " + loginUser.getI_user());
		if(readI_user==null || readI_user!= loginUser.getI_user()) {
			BoardDAO.addHits(param);
			application.setAttribute("read_" + strI_board, loginUser.getI_user());
//			System.out.println("if문 안 readI_user : " + readI_user);
//			System.out.println("if문 안 loginuser : " + loginUser.getI_user());
		}
		//단독으로 조회수 올리기 방지! -- [end]
		
		request.setAttribute("cmtList", BoardCmtDAO.selCmtList(i_board));
		
		BoardVO data = BoardDAO.detailBoard(param);
		request.setAttribute("data", data);
		
		ViewResolver.forword("board/detail", request, response);
		
	}
}


