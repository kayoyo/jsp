package com.koreait.matzip.restaurant;

import javax.servlet.http.HttpServletRequest;

import com.koreait.matzip.CommonDAO;
import com.koreait.matzip.Const;
import com.koreait.matzip.SecurityUtils;
import com.koreait.matzip.ViewRef;
import com.koreait.matzip.vo.RestaurantVO;
import com.koreait.matzip.vo.UserVO;


public class RestaurantController {
	
	private RestaurantService service;
	
	public RestaurantController() {
		service = new RestaurantService();
	}
	
	public String restMap(HttpServletRequest request) {
		request.setAttribute(Const.TITLE, "맵"); 
		request.setAttribute(Const.VIEW, "/restaurant/restMap");
		
		return ViewRef.TEMP_MEUE_TEMP;
	}
	
	public String restReg(HttpServletRequest request) {
		final int i_m = 1; //카테고리 코드(c_code_m table : i_m > 1, desc > 음식점 카테고리, cd > restaurant)
		
		request.setAttribute("categoryList", CommonDAO.selCodeList(i_m));
		
		request.setAttribute(Const.TITLE, "상호등록"); 
		request.setAttribute(Const.VIEW, "/restaurant/restReg");
		
		return ViewRef.TEMP_MEUE_TEMP;
	}
	
	public String restRegProc(HttpServletRequest request) {
		
		//로그인한 i_user 넣어주기
		UserVO loginUser = SecurityUtils.getLoginUser(request);
		
		int i_user = loginUser.getI_user();
		String nm = request.getParameter("nm");
		String addr = request.getParameter("addr");
		Double lat = Double.parseDouble(request.getParameter("lat"));
		Double lng = Double.parseDouble(request.getParameter("lng"));
		int cd_category = Integer.parseInt(request.getParameter("cd_category"));
		
		RestaurantVO param = new RestaurantVO();
		
		param.setNm(nm);
		param.setAddr(addr);
		param.setLat(lat);
		param.setLng(lng);
		param.setCd_category(cd_category);
		param.setI_user(i_user);
		
		int result = service.insRestaurant(param);
	
		return "redirect:/restaurant/restMap";
			
	}

	public String ajaxGetList(HttpServletRequest request) {		
		return "ajax : " + service.getRestList();
	}
	
	
}
