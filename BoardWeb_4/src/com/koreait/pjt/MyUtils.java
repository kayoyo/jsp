package com.koreait.pjt;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.pjt.vo.UserVO;

public class MyUtils {
	public static int getIntParameter(HttpServletRequest request, String keyNm) {
		return parseStrToInt(request.getParameter(keyNm));
	}
	
	public static UserVO getLoginUser(HttpServletRequest request) {
		HttpSession hs = request.getSession();
		return (UserVO)hs.getAttribute(Const.LOGIN_USER);
	}
	
	//return true : 로그인 안됨, false : 로그인 됨
	public static boolean isLogout(HttpServletRequest request) throws IOException {
		
		if(null == getLoginUser(request)) {
			return true;
		}
			return false;
		}
	
	public static String encryptString(String str) {
		 String sha = "";

	       try{
	          MessageDigest sh = MessageDigest.getInstance("SHA-256");
	          sh.update(str.getBytes()); //바이트 단위로 바꿈
	          byte byteData[] = sh.digest();
	          
	          StringBuffer sb = new StringBuffer(); //StringBuffer : 필요한 만큼 문자열이 늘려짐(속도가 빠름)
	          for(int i = 0 ; i < byteData.length ; i++){
	              sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1)); 
	          }

	          sha = sb.toString();

	      }catch(NoSuchAlgorithmException e){
	          //e.printStackTrace();
	          System.out.println("Encrypt Error - NoSuchAlgorithmException");
	          sha = null;
	      }

	      return sha;
	}

	public static int parseStrToInt(String str) {
		return parseStrToInt(str, 0); 
	}
	
	/*오버로딩(Overloading) : 같은 이름의 메소드를 여러 개 가지면서 매개변수의 유형과 개수가 다르도록 하는 기술
	오버라이딩(Overriding) : 상위 클래스가 가지고 있는 메소드를 하위 클래스가 재정의 해서 상요한다.*/
	public static int parseStrToInt(String str, int defNo) {
		try {
			return Integer.parseInt(str); //문자열안에 숫자 제외한 문자가 들어있다면 0이 넘어옴
		} catch(Exception e) {
			return defNo;
		}
	}

}
