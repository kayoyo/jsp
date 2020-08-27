package com.koreait.pjt.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.koreait.pjt.vo.LoginHistroryVO;
import com.koreait.pjt.vo.UserVO;

public class UserDAO {
	public static int insUser(UserVO param) {
		
		String sql = "insert into t_user" + "(i_user, user_id, user_pw, user_nm, e_mail)"
		+ "values" + "(seq_user.nextval, ?, ?, ?, ? )";
		
		return JdbcTemplate.excuteUpdate(sql, new JdbcUpdateInterface(){ 
			//new JdbcUpdateInterface() : 인터페이스를 객체화 한 것이 아님 
			
			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setNString(1, param.getUser_id());
				ps.setNString(2, param.getUser_pw());
				ps.setNString(3, param.getUser_name());
				ps.setNString(4, param.getUser_email());				
			}
		});
	}

	public static int login(UserVO param) {
		
		String sql = "select i_user, user_pw, user_nm from t_user where user_id = ?";
		
		return JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {
 
			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
				ps.setNString(1, param.getUser_id());
				
			}

			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				if(rs.next()) { //레코드가 있다.
					String dbPw = rs.getNString("user_pw");
					
					if(dbPw.equals(param.getUser_pw())) { //DB에 저장된 비밀번호와 사용자가 입력한 비밀번호 비교
						int i_user = rs.getInt("i_user");
						String nm = rs.getNString("user_nm");
						param.setUser_pw(null);
						param.setI_user(i_user);
						param.setUser_name(nm);
						
						return 1; //로그인 성공
					} else { 
						return 2;//비밀번호 틀림
					}
				} else {			
					return 3;//아이디 없음
				}
			}
			
		});

}
		public static int insUserLoginHistory(LoginHistroryVO ulhVO) {
			
			String sql = "insert into t_user_loginhistory (i_history, i_user, ip_addr, os, browser) values (seq_userloginhistory.nextval, ?, ?, ?, ?)";  
			
			return JdbcTemplate.excuteUpdate(sql, new JdbcUpdateInterface() {

				@Override
				public void update(PreparedStatement ps) throws SQLException {
					ps.setInt(1, ulhVO.getI_user());
					ps.setNString(2, ulhVO.getIp_addr());
					ps.setNString(3, ulhVO.getOs());
					ps.setNString(4, ulhVO.getBrowser());
				}
				
			});
					
		}
		
		
}