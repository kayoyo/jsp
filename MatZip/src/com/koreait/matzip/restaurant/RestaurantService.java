package com.koreait.matzip.restaurant;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.koreait.matzip.CommonUtils;
import com.koreait.matzip.FileUtils;
import com.koreait.matzip.vo.RestaurantDomain;
import com.koreait.matzip.vo.RestaurantRecommendFoodVO;
import com.koreait.matzip.vo.RestaurantVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class RestaurantService {
	
	private RestaurantDAO dao;
	
	public RestaurantService() {
		dao = new RestaurantDAO();		
	}
	
	public int insRestaurant(RestaurantVO param) {
		return dao.restRegProc(param);	
	}
	
	public String getRestList() {
		
		List<RestaurantDomain> list = dao.selRestList();
		Gson gson = new Gson();
		return gson.toJson(list);	
	}
	
	public RestaurantDomain getRest(RestaurantVO param) {
		return dao.selRest(param);
	}
	
	public int addRecMenus(HttpServletRequest request) {
		
		String savePath = request.getServletContext().getRealPath("/res/img/restaurant"); //기준이되는 절대경로
		String tempPath =  savePath + "/temp"; //getRealPath() : WAS에서 작동되고 있는 절대 경로에 savePath + "/temp" 더해서 저장 후 임시로 폴더를 생성
		System.out.println(tempPath);
		FileUtils.makeFolder(tempPath); //temp 폴더가 없다면 생성(한번만 temp가 생성됨)
		
		int makeFileSize = 10_485_760; //1024*1024*10(10MB) : 최대 파일 사이즈 
		
		MultipartRequest multi = null; 
		int i_rest = 0;
		String[] menu_nmArr = null;
		String[] menu_priceArr = null;
		List<RestaurantRecommendFoodVO> list = null;
		
		
		try {
			multi = new MultipartRequest(request, tempPath, makeFileSize,"UTF-8", new DefaultFileRenamePolicy()); //new DefaultFileRenamePolicy() 중복된 파일이 있다면 재정의하여 저장
			
			i_rest = CommonUtils.getIntParameter("i_rest", multi);
			System.out.println("i_rest : " + i_rest);
			
			menu_nmArr = multi.getParameterValues("menu_nm"); 
			menu_priceArr = multi.getParameterValues("menu_price");
			
			if(menu_nmArr == null || menu_priceArr == null) {
				return i_rest;
			}
			
			list = new ArrayList();
			for(int i=0; i<menu_nmArr.length; i++) {
				RestaurantRecommendFoodVO vo = new RestaurantRecommendFoodVO();
				vo.setI_rest(i_rest);
				vo.setMenu_nm(menu_nmArr[i]);
				vo.setMenu_price(CommonUtils.parseStringToInt(menu_priceArr[i]));
				list.add(vo);
				System.out.println(i + ":" + menu_nmArr[i] + ", " + menu_priceArr[i]);	
				}	
			
			
			String targetPath = savePath + "/" + i_rest;
			FileUtils.makeFolder(targetPath);
			
			
			String originfileNm = "";
			String saveFileNm = "";
			Enumeration files = multi.getFileNames(); 
			
			while(files.hasMoreElements()) { //hasMoreElements() : file이 있는 것 만큼 반복됨
				
				String key = (String)files.nextElement(); //input name 값
				System.out.println("key : " + key);
				
				originfileNm = multi.getFilesystemName(key); //업로드한 기존의 파일 이름, 웹에서 파일 업로드를 안하면 null값이 저장됨
				System.out.println("fileNm : " + originfileNm);
				
				if(originfileNm !=null) { 
					
					String ext = FileUtils.getExt(originfileNm); //업로드한 기존의 파일 이름의 확장자가 저장됨
					saveFileNm = UUID.randomUUID() + ext; //새롭게 만든 파일이름에 + 확장자를 더해서 저장
					System.out.println("saveFileNm : " + saveFileNm);	
					
					File oldFile = new File(tempPath + "/" + originfileNm); //temp 폴더(임시폴더)에 있는 기존 파일 이름
				    File newFile = new File(targetPath + "/" + saveFileNm); //PK가 붙은 폴더(저장될 폴더)에 있는 바뀌어진 파일 이름
				    
				    oldFile.renameTo(newFile); //기존 파일 이름이 바뀌어진 파일 이름으로 변경되어 PK가 붙은 폴더로 이동됨 
				    
				    int idx = CommonUtils.parseStringToInt(key.substring(key.lastIndexOf("_") + 1)); // ex)menu_pic_1 에서 _까지 index를 찾아서 1를 더하면 name의 숫자값을 알게됨				    
				    RestaurantRecommendFoodVO vo = list.get(idx);
				    vo.setMenu_pic(saveFileNm);		
				}
					
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(list != null) {
			for(RestaurantRecommendFoodVO vo : list) {
				dao.insRecommendFood(vo);
				}
			}	
		return i_rest;
	}
	
	public List<RestaurantRecommendFoodVO> getRecommendFoodList(int i_rest) {
		return dao.selRecommendFoodList(i_rest);
	}
	
	public int delRecMenu(RestaurantRecommendFoodVO param) {
		return dao.delRecommendFood(param);
	}
}
