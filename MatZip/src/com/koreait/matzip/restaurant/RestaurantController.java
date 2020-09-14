package com.koreait.matzip.restaurant;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.koreait.matzip.CommonDAO;
import com.koreait.matzip.CommonUtils;
import com.koreait.matzip.Const;
import com.koreait.matzip.SecurityUtils;
import com.koreait.matzip.ViewRef;
import com.koreait.matzip.vo.RestaurantVO;
import com.koreait.matzip.vo.UserVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


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
	
	public String restDetail(HttpServletRequest request) {
		int i_rest = CommonUtils.getIntParameter("i_rest", request);
		
		RestaurantVO param = new RestaurantVO();
		param.setI_rest(i_rest);
		
		request.setAttribute("data", service.getRest(param));
		request.setAttribute(Const.TITLE, "디테일");
		request.setAttribute(Const.VIEW, "restaurant/restDetail");
		
		return ViewRef.TEMP_MEUE_TEMP;
	}
	
	public String addRecMenusProc(HttpServletRequest request) {
		
		String uploads = request.getRealPath("/res/img");
		MultipartRequest multi = null;
		String strI_rest = null;
		String[] menu_nmArr = null;
		String[] menu_priceArr = null;
		try {
			multi=new MultipartRequest(request, uploads,5*1024*1024,"UTF-8",new DefaultFileRenamePolicy());
			
			strI_rest = multi.getParameter("i_rest");
			menu_nmArr = multi.getParameterValues("menu_nm");
			menu_priceArr = multi.getParameterValues("menu_price");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(menu_nmArr != null && menu_priceArr != null) {
			for(int i=0; i<menu_nmArr.length; i++) {
				System.out.println(i + ":" + menu_nmArr[i] + ", " + menu_priceArr[i]);
			}	
		}
		
		return "redirect:/restaurant/restDetail?i_rest=" + strI_rest;
	}
	
	
	public String restRegProc(HttpServletRequest request) {
		
		//로그인한 i_user 넣어주기
		UserVO loginUser = SecurityUtils.getLoginUser(request);
		int i_user = loginUser.getI_user();
		
		String nm = request.getParameter("nm");
		String addr = request.getParameter("addr");
		double lat = CommonUtils.getDoubleParameter("lat", request);
		double lng = CommonUtils.getDoubleParameter("lng", request);
		int cd_category = CommonUtils.getIntParameter("cd_category", request);
		
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
		return "ajax:" + service.getRestList();
	}
	
	
}
