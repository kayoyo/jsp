package com.koreait.matzip.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DbManager {
	
		public static Connection getCon() throws Exception{ {
			String url = "jdbc:mysql://127.0.0.1:3306/matzip";
			String user = "root";
			String pw = "koreait2020";
			String className = "com.mysql.cj.jdbc.Driver";
			
			Class.forName(className);
			Connection conn = DriverManager.getConnection(url, user, pw);
			System.out.println("DB 연결 완료!");
			return conn;
		}
		
}
		public static void close(Connection conn, PreparedStatement ps, ResultSet rs) {
			
			if(rs != null){ try{ rs.close(); } catch(Exception e) {} } 
			if(ps != null){ try{ ps.close(); } catch(Exception e) {} } 
			if(conn != null){ try{ conn.close(); } catch(Exception e) {} }
			
		} 
			
		public static void close(Connection conn,  PreparedStatement ps) {
			close(conn, ps, null);
		}
		
}
