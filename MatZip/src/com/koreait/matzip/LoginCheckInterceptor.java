package com.koreait.matzip;

import javax.servlet.http.HttpServletRequest;

public class LoginCheckInterceptor { //잘못된 경로로 갔을 때 잡아줌
	
	public static String routerChk(HttpServletRequest request) {
		//리턴값이 null이라면 아무 일도 없음	
		//만약 문자열이 리턴되면 그 문자열로 sendRedirect	
		
		//로그인이 되어 있다면 아래의 주소값은 접근 못하게 막기
		String[] chkUserUriArr = {"login", "loginProc", "join", "joinProc", "ajaxIdChk"}; //2차 주소(1차 주소는 "user")
		
		
		boolean isLogout = SecurityUtils.isLogOut(request);
		String[] targetUri = request.getRequestURI().split("/");
		
		if(targetUri.length < 3) {  
			return null;			
		}
		
		if(isLogout) { //true 라면 로그아웃 상태
			if(targetUri[1].equals(ViewRef.URI_USER)) { //user가 맞다면 
				for(String uri : chkUserUriArr) { 
					if(uri.equals(targetUri[2])) { //for문 실행해서 chkUserUriArr의 배열과 비교
						return null; 											
					}
				}
			}
			return "/user/login"; //user가 아니라면  
			
		  
		} else { //false 라면 로그인 상태
			if(targetUri[1].equals(ViewRef.URI_USER)) { 
				for(String uri : chkUserUriArr) {
					if(uri.equals(targetUri[2])) {
					return "/restaurant/restMap"; 					
				}
			}
		}
			return null;
		}
	}

}
