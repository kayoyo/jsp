package com.koreait.pjt.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.pjt.MyUtils;
import com.koreait.pjt.db.BoardCmtDAO;
import com.koreait.pjt.vo.BoardCmtVO;
import com.koreait.pjt.vo.UserVO;

/*
 	1. JdbcTemplate 리턴타입
	2. 무결성 제약 조건 : 자료형이 틀렸거나, 컬럼 누락, sql 확인
	3. number format exception : 숫자로 된 것 확인, 쿼리스트링의 숫자 누락 
 */
@WebServlet("/board/cmt")
public class BoardCmtSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	//댓글(삭제)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String strI_cmt = request.getParameter("i_cmt");
		int i_cmt = MyUtils.parseStrToInt(strI_cmt);
		
		String strI_board = request.getParameter("i_board");
		int i_board = MyUtils.parseStrToInt(strI_board);
		
		UserVO loginUser = MyUtils.getLoginUser(request);
		int i_user = loginUser.getI_user();
		
		BoardCmtVO param = new BoardCmtVO();
		
		param.setI_cmt(i_cmt);
    	param.setI_user(i_user);
		
    	BoardCmtDAO.delCmt(param);
		
		response.sendRedirect("/board/detail?i_board=" + i_board);
				
		
	}

	//댓글(등록, 수정)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String strI_cmt = request.getParameter("i_cmt");
		int i_cmt = MyUtils.parseStrToInt(strI_cmt);
		
		String strI_board = request.getParameter("i_board");
		int i_board = MyUtils.parseStrToInt(strI_board);
		
		//System.out.println(i_board);
		
		String cmt = request.getParameter("cmt");
		
		UserVO loginUser = MyUtils.getLoginUser(request);
		int i_user = loginUser.getI_user();
		
		BoardCmtVO param = new BoardCmtVO();
		
		param.setCmt(cmt);
		param.setI_user(i_user);
		
		
		switch(strI_cmt) {
		case "0": //등록
		param.setI_board(i_board);
		BoardCmtDAO.insCmt(param);
			break;
			
		default: //수정
		param.setI_cmt(i_cmt);
		BoardCmtDAO.updCmt(param);
			break;
		}
		
		response.sendRedirect("/board/detail?i_board=" + i_board);
	}
}
