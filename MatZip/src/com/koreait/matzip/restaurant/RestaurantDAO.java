package com.koreait.matzip.restaurant;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.koreait.matzip.SecurityUtils;
import com.koreait.matzip.db.JdbcSelectInterface;
import com.koreait.matzip.db.JdbcTemplate;
import com.koreait.matzip.db.JdbcUpdateInterface;
import com.koreait.matzip.vo.RestaurantDomain;
import com.koreait.matzip.vo.RestaurantVO;



public class RestaurantDAO {
	
	public int restRegProc(RestaurantVO param) {

		int result = 0;

		String sql = " INSERT INTO t_restaurant (i_user, nm, addr, lat, lng, cd_category) VALUES (?, ?, ?, ?, ?, ?) ";

		return JdbcTemplate.excuteUpdate(sql, new JdbcUpdateInterface() {

			@Override
			public void update(PreparedStatement ps) throws SQLException {			
					
				ps.setInt(1, param.getI_user());
				ps.setNString(2, param.getNm());
				ps.setNString(3, param.getAddr());
				ps.setDouble(4, param.getLat());
				ps.setDouble(5, param.getLng());
				ps.setInt(6, param.getCd_category());
			}

		});

	}
	public List<RestaurantDomain> selRestList() {
		List<RestaurantDomain> list = new ArrayList();
		
		String sql = " select i_rest, nm, lat, lng freom t_restaurant ";
		
		JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {

			@Override
			public void prepared(PreparedStatement ps) throws SQLException { }

			@Override
			public void executeQuery(ResultSet rs) throws SQLException {
				while(rs.next()) {
					
					RestaurantDomain vo = new RestaurantDomain();
					
					vo.setI_rest(rs.getInt("i_rest"));
					vo.setNm(rs.getNString("nm"));
					vo.setLat(rs.getDouble("lat"));
					vo.setLng(rs.getDouble("lng"));
					
					list.add(vo);
				}				
			}		
		});
		return list;
	}
}
