package com.koreait.pjt.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.koreait.pjt.vo.BoardVO;

public class BoardDAO {
	public static List<BoardVO> selBoardList(BoardDomain param){
		final List<BoardVO> list = new ArrayList(); //객체의 주소값을 변경할 수 없다. 값을 추가 할 수는 있다.
		
		String sql = " select A.* from "		
				+ " ( select rownum as rnum, A.* from "
				+ " ( select i_board, title, hits, i_user, r_dt "
				+ " , (SELECT count(*) FROM t_board4_like where i_board = A.i_board) as like_cnt "
				+ " from t_board4 A order by i_board desc ) A "
				+ " where rownum <= ? ) A "
				+ " where A.rnum > ? ";
		int result = JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {
		

			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
				
				ps.setInt(1, param.geteIdx()); //~까지
				ps.setInt(2, param.getsIdx()); //~부터
			}
			//eidx = p * r
			//sidx = eidx - r 
			
			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				
				while(rs.next()) {
					int i_board = rs.getInt("i_board");
					String title = rs.getNString("title");
					int hits = rs.getInt("hits");
					int i_user = rs.getInt("i_user");
					String r_dt = rs.getNString("r_dt");
					int like_cnt = rs.getInt("like_cnt");
					
					BoardVO vo = new BoardVO();
					vo.setI_board(i_board);
					vo.setTitle(title);
					vo.setHits(hits);
					vo.setI_user(i_user);
					vo.setR_dt(r_dt);
					vo.setLike_cnt(like_cnt);
					
					list.add(vo);
				}			
					return 1;
			}			
			
		});
			return list;
	}
	
	public static int insBoard(BoardVO param) {
		
		String sql = "insert into t_board4(i_board, title, ctnt, i_user)" +
				  "select nvl(max(i_board),0) + 1, ?, ?, ?" + "from t_board4";
		
		return JdbcTemplate.excuteUpdate(sql, new JdbcUpdateInterface() {

			@Override
			public void update(PreparedStatement ps) throws SQLException { //sql로 ps를 만들어서  ps를받음
				ps.setNString(1, param.getTitle());
				ps.setNString(2, param.getCtnt());
				ps.setInt(3, param.getI_user());
			}	
			
		});
		
	}
	public static BoardVO detailBoard(BoardVO param) {
		/*String sql = " SELECT A.i_board, A.title, A.ctnt, B.user_nm, A.i_user, A.r_dt, A.hits "
				+ " FROM t_board4 A "
				+ " inner join t_user B "
				+ " on A.i_user = B.i_user "
				+ " WHERE A.i_board = ? ";*/
		
		String sql = " select A.*, B.user_nm, decode(C.i_user, null, 0, 1) as likey "
				+ ", (SELECT count(*) FROM t_board4_like where i_board = ?) as like_cnt "
				+ " from t_board4 A "
				+ " inner join t_user B "
				+ " on A.i_user = B.i_user "
				+ " left join t_board4_like C "
				+ " on A.i_board = C.i_board and c.i_user = ? where A.i_board = ? ";
		//i_user : 로그인 한 사람, i_board : 선택된 게시글
		
		int resultInt = JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {

			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
				ps.setInt(1, param.getI_board());
				ps.setInt(2, param.getI_user());
				ps.setInt(3, param.getI_board());
				
			}

			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				if(rs.next()) {
					int i_board = rs.getInt("i_board");
					String title = rs.getNString("title");
					String ctnt = rs.getNString("ctnt");
					String user_nm = rs.getNString("user_nm");
					String r_dt = rs.getNString("r_dt");
					int hits = rs.getInt("hits");
					int i_user = rs.getInt("i_user");
					int likey = rs.getInt("likey");
					int like_cnt = rs.getInt("like_cnt");
					
					param.setI_user(i_board);
					param.setTitle(title);
					param.setCtnt(ctnt);
					param.setUser_nm(user_nm);
					param.setR_dt(r_dt);
					param.setHits(hits);
					param.setI_user(i_user); //작성자
					param.setLikey(likey);
					param.setLike_cnt(like_cnt);
					
					} 
				return 1;				
				}
		});
		return param;
	}
	
		public static int delBoard(BoardVO param) {
			
			String sql = " delete from t_board4 where i_board = ? ";
		
			return JdbcTemplate.excuteUpdate(sql, new JdbcUpdateInterface() {
				@Override
				public void update(PreparedStatement ps) throws SQLException {				
				ps.setInt(1, param.getI_board());
			}
		});
		
	}
		
	
		public static int upBoard(BoardVO param) {
		
		String sql = "update t_board4 set title=?, ctnt=? where i_board=?";
		return JdbcTemplate.excuteUpdate(sql, new JdbcUpdateInterface() {

			@Override
			public void update(PreparedStatement ps) throws SQLException {
				
				ps.setNString(1, param.getTitle());
				ps.setNString(2, param.getCtnt());
				ps.setInt(3, param.getI_board());
				
			}
			
			
		});
		
		
		
	}
		
		public static int addHits(BoardVO param) {
			
			String sql = "update t_board4 set hits=? where i_board=?";
			return JdbcTemplate.excuteUpdate(sql, new JdbcUpdateInterface() {

				@Override
				public void update(PreparedStatement ps) throws SQLException {					
					int addhits = param.getHits() + 1;
					ps.setInt(1, addhits);
					ps.setInt(2, param.getI_board());
					param.setHits(addhits);
				}
				
				
			});  
		}
		
		public static int likey(BoardVO param) {
			
			String sql = " insert into t_board4_like (i_board, i_user) values (?, ?) ";
			
			return JdbcTemplate.excuteUpdate(sql, new JdbcUpdateInterface() {

				@Override
				public void update(PreparedStatement ps) throws SQLException { 
					ps.setInt(1, param.getI_board());
					ps.setInt(2, param.getI_user());

				}	
				
			});
			
		}
		
		public static int unlikey(BoardVO param) {
			
			String sql = " delete from t_board4_like where i_board=? and i_user=? ";
			
			return JdbcTemplate.excuteUpdate(sql, new JdbcUpdateInterface() {

				@Override
				public void update(PreparedStatement ps) throws SQLException { 
					ps.setInt(1, param.getI_board());
					ps.setInt(2, param.getI_user());
					
				}	
				
			});
			
			
		}
		
		//페이지 숫자 가져오기
		public static int selPagingCnt(final BoardDomain param) {
			String sql = " select ceil(count(i_board) / ?) from t_board4 ";
			return JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {

				@Override
				public void prepared(PreparedStatement ps) throws SQLException {
					ps.setInt(1, param.getRecord_cnt());
					
				}

				@Override
				public int executeQuery(ResultSet rs) throws SQLException {
					if(rs.next()) {
						return rs.getInt(1); //첫번째 열값을 인덱스로 가져와서 리턴
					}
					return 0;
				}
				
			});
			
		}
		
		
			
}


	

