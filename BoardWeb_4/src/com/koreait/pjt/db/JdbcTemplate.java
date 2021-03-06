package com.koreait.pjt.db;

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
			conn = Dbconn.getConn();
			ps = conn.prepareStatement(sql);	
			jdbc.update(ps);
			result = ps.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			Dbconn.close(conn, ps);
		}
		
		return result;
	}
	
	public static int executeQuery(String sql, JdbcSelectInterface jdbc) {
		
		int result = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = Dbconn.getConn();
			ps = conn.prepareStatement(sql);
			jdbc.prepared(ps);
			rs = ps.executeQuery();
			result = jdbc.executeQuery(rs);
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			Dbconn.close(conn, ps, rs);
		}
		
		return result;
	}

	
	}

	
	

