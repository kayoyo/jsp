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
import com.koreait.pjt.vo.UserVO;


@WebServlet("/board/list")
public class BoardListSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserVO loginUser = MyUtils.getLoginUser(request); //ID, PK, NAME
		
		if( loginUser == null) {
			response.sendRedirect("/login");
			return;
		}
		
		String searchType = request.getParameter("searchType");
		searchType = (searchType == null) ? "a" : searchType;
		
		String searchText = request.getParameter("searchText");
		searchText = (searchText == null) ? "" : searchText;
		
		int page = MyUtils.getIntParameter(request, "page");
		//페이지 쿼리 스트링을 보내지 않았을 때, 숫자이외에 문자가 섞여 있을때(MyUtils.getIntParamete), 0이 출력됨
		page = (page == 0 ? 1 : page);
		
		int recordCnt = MyUtils.getIntParameter(request, "record_cnt");
		recordCnt = (recordCnt == 0 ? 10 : recordCnt);
		
		
		BoardDomain param = new BoardDomain();
		param.setI_user(loginUser.getI_user());
		param.setRecord_cnt(recordCnt);
		param.setSearchType(searchType);
		param.setSearchText("%" + searchText + "%");
		
		int pagingCnt = BoardDAO.selPagingCnt(param);
		
		//System.out.println(" pagingCnt : "  + pagingCnt);
		//System.out.println("page : " + page);
		
		if(page > pagingCnt) { //이전 레코드수 값이 있고, 이전 레코드 수보다 변경한 수가 더 크다면
			page = pagingCnt;
		}
		
		request.setAttribute("searchType", searchType);
		request.setAttribute("searchText", searchText);
		request.setAttribute("page", page); //현재 페이지를 page에 넣어줌
		request.setAttribute("pagingCnt", pagingCnt);
		
		int eIdx = page * recordCnt;
		int sIdx = eIdx - recordCnt;
		
		param.seteIdx(eIdx);
		param.setsIdx(sIdx);
		
		List<BoardDomain> list = BoardDAO.selBoardList(param);
		
		//하이라이트 처리
		if(!"".equals(searchText) && ("a".equals(searchType) || "c".equals(searchType))) { //b는 내용
			for(BoardDomain item : list) {
				String title = item.getTitle();
				title = title.replace(searchText, "<span class=\"highlight\">" + searchText +"</span>");
				item.setTitle(title);
		}
		}
			
		request.setAttribute("list", list);
		request.setAttribute("data", "loginUser");
//		request.setAttribute("data", list);
		
		HttpSession hs = request.getSession();
		
		hs.setAttribute("page", page);
		hs.setAttribute("recordCnt", recordCnt);
		hs.setAttribute("searchType", searchType);
		hs.setAttribute("searchText", searchText);
		
		
		
		ViewResolver.forwordLoginChk("board/list", request, response);
	}	
	
	}	

