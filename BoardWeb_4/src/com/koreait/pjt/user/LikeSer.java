package com.koreait.pjt.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.pjt.Const;
import com.koreait.pjt.db.BoardDAO;
import com.koreait.pjt.vo.BoardVO;
import com.koreait.pjt.vo.UserVO;


@WebServlet("/like")
public class LikeSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int i_board = Integer.parseInt(request.getParameter("i_board"));
		int likey = Integer.parseInt(request.getParameter("likey"));
		
		HttpSession hs = request.getSession();
		UserVO loginUser = (UserVO) hs.getAttribute(Const.LOGIN_USER);
		
		BoardVO param = new BoardVO();
		
		param.setI_user(loginUser.getI_user()); //로그인한 사람의 i_user(세션에 담겨져 있음)
		param.setI_board(i_board);
		param.setLikey(likey);
		
		
		
		if(likey == 0) {
			BoardDAO.likey(param);
			response.sendRedirect("/board/detail?i_board=" + i_board);
			
		} else {
			BoardDAO.unlikey(param);
			response.sendRedirect("/board/detail?i_board=" + i_board);
			
		}
		
		
		
		
	    
		
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
	}

}