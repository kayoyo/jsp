package com.koreait.pjt.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.pjt.Const;
import com.koreait.pjt.MyUtils;
import com.koreait.pjt.db.BoardDAO;
import com.koreait.pjt.vo.BoardVO;
import com.koreait.pjt.vo.UserVO;


@WebServlet("/like")
public class LikeSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String strI_board = request.getParameter("i_board");
		String strLike = request.getParameter("like");
		String page = request.getParameter("page");
		String record_cnt = request.getParameter("record_cnt");
		String searchText = request.getParameter("searchText");
		String searchType = request.getParameter("searchType");
		
		UserVO loginUser = MyUtils.getLoginUser(request);
		
		int i_board = Integer.parseInt(request.getParameter("i_board"));
		int like = Integer.parseInt(request.getParameter("like"));
		
		BoardVO param = new BoardVO();
		
		param.setI_board(i_board);
		param.setI_user(loginUser.getI_user()); //로그인한 사람의 i_user(세션에 담겨져 있음)
		

		if(like == 0) {
			BoardDAO.like(param);
			
		} else if(like == 1) {
			BoardDAO.unlike(param);
			
		}
		
		String target = String.format("/board/detail?i_board=%s&page=%s&record_cnt=%s&searchText=%s&searchType=%s"
				, strI_board, page, record_cnt, searchText, searchType);
		response.sendRedirect(target);
		
		

		
		
	}
		
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
	}

}
