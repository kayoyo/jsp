package com.koreait.matzip.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JdbcTemplate {
	
	//insert, update, delete에 사용
	public static int excuteUpdate(String sql, JdbcUpdateInterface jdbc) {
		//jdbc가 가르키고 있는 객체는 update를 구현함(UserDAO)
		
		int result = 0;
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DbManager.getCon();
			ps = conn.prepareStatement(sql);	
			jdbc.update(ps);
			result = ps.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DbManager.close(conn, ps);
		}
		
		return result;
	}
	
	public static void executeQuery(String sql, JdbcSelectInterface jdbc) {
	
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = DbManager.getCon();
			ps = conn.prepareStatement(sql);
			jdbc.prepared(ps);
			rs = ps.executeQuery();
			jdbc.executeQuery(rs);
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DbManager.close(conn, ps, rs);
		}
	
	}
	
	

	
	}

	
	

