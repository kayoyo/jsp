package com.koreait.pjt.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.koreait.pjt.vo.BoardCmtVO;

public class BoardCmtDAO {
	
	public static int insCmt(BoardCmtVO param) {
		
		String sql = " insert into t_board_cmt(i_cmt, i_board, i_user, cmt) values (seq_t_board_cmt.nextval, ?, ?, ?)";
		
		return JdbcTemplate.excuteUpdate(sql, new JdbcUpdateInterface() {

			@Override
			public void update(PreparedStatement ps) throws SQLException {
				
				ps.setInt(1, param.getI_board());
				ps.setInt(2, param.getI_user());
				ps.setNString(3, param.getCmt());
				
			}
			
		});
		
	}
	
	public static List<BoardCmtVO> selCmtList(int i_board){
		List<BoardCmtVO> list = new ArrayList();
		
		String sql = " select A.i_cmt, B.i_user, A.cmt, A.r_dt, B.user_nm "
					+ " from t_board_cmt A "
					+ " left join t_user B "
				    + " on A.i_user = B.i_user where i_board=? "
					+ " order by A.i_cmt " ;
		int result = JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {

			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
				ps.setInt(1, i_board);
			}

			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				
				while(rs.next()) {
					
					BoardCmtVO vo = new BoardCmtVO();
					vo.setI_cmt(rs.getInt("i_cmt"));
					vo.setCmt(rs.getNString("cmt"));
					vo.setR_dt(rs.getNString("r_dt"));
					vo.setUser_nm(rs.getNString("user_nm"));
					vo.setI_user(rs.getInt("i_user"));
					
					list.add(vo);		
				}
				return 1;
			}
			
		});
		
		return list;
		
	}
	
	public static int updCmt(BoardCmtVO param) {
		
		String sql = " update t_board_cmt set cmt=? where i_cmt=? ";
		
		return JdbcTemplate.excuteUpdate(sql, new JdbcUpdateInterface() {

			@Override
			public void update(PreparedStatement ps) throws SQLException {
				
				ps.setNString(1, param.getCmt());
				ps.setInt(2, param.getI_cmt());
			}		
			
		});		
		
	}
		
	
	public static int delCmt(BoardCmtVO param) {
		
		String sql = " delete from t_board_cmt where i_cmt = ? and i_user = ? ";
		
		return JdbcTemplate.excuteUpdate(sql, new JdbcUpdateInterface() {

			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setInt(1, param.getI_cmt());
				ps.setInt(2, param.getI_user());
			}
			
		});
	}

}