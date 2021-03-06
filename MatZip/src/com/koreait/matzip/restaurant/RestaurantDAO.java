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
import com.koreait.matzip.vo.RestaurantRecommendFoodVO;
import com.koreait.matzip.vo.RestaurantVO;



public class RestaurantDAO {
	
	
	public int delRecommendFood(RestaurantRecommendFoodVO param) {
		
		String sql = " DELETE FROM t_restaurant_recommend_food WHERE i_rest = ? AND seq = ? ";
		
		return JdbcTemplate.excuteUpdate(sql, new JdbcUpdateInterface() {

			@Override
			public void update(PreparedStatement ps) throws SQLException {
				
				ps.setInt(1, param.getI_rest());
				ps.setInt(2, param.getSeq());
				
			}
			
		});
		
	}
	
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
	
	public int insRecommendFood(RestaurantRecommendFoodVO param) {
		String sql= " insert into t_restaurant_recommend_food "
				+ " (seq, i_rest, menu_nm, menu_price, menu_pic) "
				+ " select ifnull(max(seq), 0) + 1, ?, ?, ?, ? "
				+ " from t_restaurant_recommend_food "
				+ " where i_rest = ? ";
		return JdbcTemplate.excuteUpdate(sql, new JdbcUpdateInterface() {

			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setInt(1, param.getI_rest());
				ps.setString(2, param.getMenu_nm());
				ps.setInt(3, param.getMenu_price());
				ps.setString(4, param.getMenu_pic());
				ps.setInt(5, param.getI_rest());
			}
			
		});
	}
		
	
	
	
	public List<RestaurantDomain> selRestList() {
		List<RestaurantDomain> list = new ArrayList();
		
		String sql = " select i_rest, nm, lat, lng from t_restaurant ";
		
		JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {

			@Override
			public void prepared(PreparedStatement ps) throws SQLException { }

			@Override
			public void executeQuery(ResultSet rs) throws SQLException {
				while(rs.next()) {
					
					RestaurantDomain vo = new RestaurantDomain();
					
					vo.setI_rest(rs.getInt("i_rest"));
					vo.setNm(rs.getString("nm"));
					vo.setLat(rs.getDouble("lat"));
					vo.setLng(rs.getDouble("lng"));
					
					list.add(vo);
				}				
			}		
		});
		return list;
	}



	public RestaurantDomain selRest(RestaurantVO param) {
		
		RestaurantDomain vo = new RestaurantDomain ();
		
		String sql = " SELECT A.i_rest, A.nm, A.addr, A.i_user, A.hits "
				+ ",B.val AS cd_category_nm, IFNULL(C.cnt, 0) as cntFavorite "
				+ " FROM t_restaurant A "
				+ " LEFT JOIN c_code_d B "
				+ " ON A.cd_category = B.cd "
				+ " AND b.i_m = 1 "
				+ " LEFT JOIN( "
				+ " SELECT i_rest, count(i_rest) AS cnt "
				+ " FROM t_user_favoaite "
				+ " WHERE i_rest = ? "
				+ " GROUP BY i_rest "
				+ " ) C "
				+ " ON A.i_rest = c.i_rest "
				+ " WHERE A.i_rest = ? ";
		
		JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {

			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
				ps.setInt(1, param.getI_rest());
				ps.setInt(2, param.getI_rest());
				
			}

			@Override
			public void executeQuery(ResultSet rs) throws SQLException {
				
				if(rs.next()) {
					vo.setI_rest(param.getI_rest());
					vo.setNm(rs.getNString("nm"));
					vo.setAddr(rs.getNString("addr"));
					vo.setI_user(rs.getInt("i_user"));
					vo.setCntHits(rs.getInt("hits"));
					vo.setCd_category_nm(rs.getNString("cd_category_nm"));
					vo.setCntFavorite(rs.getInt("cntFavorite"));
					
				}
				
			}
		});
		return vo;
	}
	
	public List<RestaurantRecommendFoodVO> selRecommendFoodList(int i_rest){
		List<RestaurantRecommendFoodVO> list = new ArrayList();
		String sql = " select seq, menu_nm, menu_price, menu_pic "
				+ " from t_restaurant_recommend_food "
				+ " where i_rest = ? ";
		
		JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {

			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
				ps.setInt(1, i_rest);
				
			}

			@Override
			public void executeQuery(ResultSet rs) throws SQLException {
				while(rs.next()) {
					RestaurantRecommendFoodVO vo = new RestaurantRecommendFoodVO();
					vo.setSeq(rs.getInt("seq"));
					vo.setMenu_nm(rs.getString("menu_nm"));
					vo.setMenu_price(rs.getInt("menu_price"));
					vo.setMenu_pic(rs.getString("menu_pic"));
					
					list.add(vo);
				}
				
			}
			
		});
		return list;
		
	}
}
		
	



