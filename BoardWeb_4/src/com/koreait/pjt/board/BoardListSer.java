package com.koreait.pjt.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.pjt.Const;
import com.koreait.pjt.MyUtils;
import com.koreait.pjt.ViewResolver;
import com.koreait.pjt.db.BoardDAO;
import com.koreait.pjt.db.BoardDomain;
import com.koreait.pjt.vo.BoardVO;


@WebServlet("/board/list")
public class BoardListSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs = request.getSession();
		if(hs.getAttribute(Const.LOGIN_USER) == null) {
			response.sendRedirect("/login");
			return;
		}
		
		int page = MyUtils.getIntParameter(request, "page");
		//페이지 쿼리 스트링을 보내지 않았을 때, 숫자이외에 문자가 섞여 있을때(MyUtils.getIntParamete), 0이 출력됨
		page = (page == 0 ? 1 : page);
		
		request.setAttribute("nowPage", page); //현재 페이지를 nowPage에 넣어줌
		
		int eIdx = page * Const.RECORD_CNT;
		int sIdx = eIdx - Const.RECORD_CNT;
		
		BoardDomain param = new BoardDomain();
		
		param.seteIdx(eIdx);
		param.setsIdx(sIdx);
		param.setRecord_cnt(Const.RECORD_CNT);
		
		List<BoardVO> list = BoardDAO.selBoardList(param);
		request.setAttribute("list", list);
		
		request.setAttribute("name", "loginUser");
		request.setAttribute("paginCnt", BoardDAO.selPagingCnt(param));
		
		ViewResolver.forwordLoginChk("board/list", request, response);
				
	}	
	
}