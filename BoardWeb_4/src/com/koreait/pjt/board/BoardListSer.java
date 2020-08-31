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
		
		String searchText = request.getParameter("searchText");
		searchText = (searchText == null) ? "" : searchText;
		
		int page = MyUtils.getIntParameter(request, "page");
		//페이지 쿼리 스트링을 보내지 않았을 때, 숫자이외에 문자가 섞여 있을때(MyUtils.getIntParamete), 0이 출력됨
		page = (page == 0 ? 1 : page);
		
		int recordCnt = MyUtils.getIntParameter(request, "record_cnt");
		recordCnt = (recordCnt == 0 ? 10 : recordCnt);
		
		
		BoardDomain param = new BoardDomain();
		param.setRecord_cnt(recordCnt);
		param.setSearchText("%" + searchText + "%");
		
		int pagingCnt = BoardDAO.selPagingCnt(param);
		
		if(page > pagingCnt) {
			page = pagingCnt;
		}
		
		request.setAttribute("nowPage", page); //현재 페이지를 nowPage에 넣어줌
		request.setAttribute("paginCnt", pagingCnt);
		
		int eIdx = page * recordCnt;
		int sIdx = eIdx - recordCnt;
		
		param.seteIdx(eIdx);
		param.setsIdx(sIdx);
		
		List<BoardVO> list = BoardDAO.selBoardList(param);
		request.setAttribute("list", list);
		
		request.setAttribute("data", "loginUser");
		ViewResolver.forwordLoginChk("board/list", request, response);
				
	}	
	
	
}
