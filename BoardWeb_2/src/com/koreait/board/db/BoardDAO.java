package com.koreait.board.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.koreait.board.vo.BoardVO;


public class BoardDAO {
	
	//boardList
	public static List<BoardVO> selBoardList(){
		List<BoardVO> list = new ArrayList();
		
		Connection conn = null; 
		PreparedStatement ps=null;
		ResultSet rs= null;
		
		String sql = "select i_board, title, i_student from t_board order by i_board desc";
		
		try {
		conn = Dbconn.getConn();
		ps = conn.prepareStatement(sql);
		rs = ps.executeQuery();
		
		while(rs.next()) {
			int i_board = rs.getInt("i_board");
			String title = rs.getNString("title");
			int i_student = rs.getInt("i_student");
			
			BoardVO vo = new BoardVO();
			vo.setI_board(i_board);
			vo.setTitle(title);
			vo.setI_student(i_student);
			
			list.add(vo);		
		}  
			
	} catch(Exception e) {
		e.printStackTrace();
	} finally {
		Dbconn.close(conn, ps, rs);
	}

		return list;
}
	//boardDetail
	public static BoardVO selBoard(BoardVO param) { //where의 특정한 값이 필요하기 때문에  파라미터로 받기
		
		BoardVO vo = new BoardVO();
		Connection conn = null; 
		PreparedStatement ps=null;
		ResultSet rs= null;
		
		String sql = "select i_board, title, ctnt, i_student from t_board where i_board = ?";
		
		try {
		
		conn = Dbconn.getConn();
		ps = conn.prepareStatement(sql);
		ps.setInt(1, param.getI_board());
		rs = ps.executeQuery();
		
		while(rs.next()) {
			int i_board = rs.getInt("i_board");
			String title = rs.getNString("title");
			String ctnt = rs.getNString("ctnt");
			int i_student = rs.getInt("i_student");
			
			vo = new BoardVO();
			vo.setI_board(i_board);
			vo.setTitle(title);
			vo.setCtnt(ctnt);
			vo.setI_student(i_student);		
		}  
			
	} catch(Exception e) {
		e.printStackTrace();
	} finally {
		Dbconn.close(conn, ps, rs);
	}	
	 return vo;	
	}
	
	//boardRegmod
	public static int insBoard(BoardVO param2) { //void 가능
			
		int result=0;
		Connection conn = null;
		PreparedStatement ps = null;
		
		
		String sql = "insert into t_board(i_board, title, ctnt, i_student)" +
				  "values" + "(swq_board.nextval, ?, ?, ? )";
		
		try {
			conn = Dbconn.getConn();
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, param2.getTitle());
			ps.setString(2, param2.getCtnt());
			ps.setInt(3, param2.getI_student());
			
			result = ps.executeUpdate();
		
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			Dbconn.close(conn, ps);
		} return result;
		
		}
	
	//boardDelete
	public static int delBoard(int i_board) {
	//	public static int delBoard(BoardVO param3)  //수정을 쉽게 하기 위해 파라미터는 객체로 받기
	// setter : ?의 값 넣어주기
		
		int result = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		
		String sql = "delete from t_board where i_board = ?"; 
		
		try { 
			conn = Dbconn.getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, i_board);
//			ps.setInt(1, param3.getI_board()); 
			result = ps.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			Dbconn.close(conn, ps);
		}
			return result;
		
	}
	
	//boardUpdate
	public static int upBoard(BoardVO param3) {
		
		String sql = "update t_board set title=?, ctnt=? where i_board=?";
		//String sql = "update t_board set title=?, ctnt=?, i_student=? where i_board=?";
		
		int result = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = Dbconn.getConn();
			ps = conn.prepareStatement(sql);
			ps.setNString(1, param3.getTitle());
			ps.setNString(2, param3.getCtnt());
			//ps.setInt(?, param3.getI_student());
			ps.setInt(3, param3.getI_board());
			
			result = ps.executeUpdate();
				
		} catch(Exception e){
			e.printStackTrace(); 

		} finally { 	
			Dbconn.close(conn, ps);
			}
		return result;
	}
	
}
