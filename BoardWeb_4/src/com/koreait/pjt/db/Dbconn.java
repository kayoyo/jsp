package com.koreait.pjt.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Dbconn {
	public static Connection getConn() throws Exception {
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String username = "hr";
		String password = "koreait2020";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(url, username, password);
		
		System.out.println("접속 성공"); 
		return conn;
		
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
