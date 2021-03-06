package com.koreait.pjt.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.pjt.Const;
import com.koreait.pjt.MyUtils;
import com.koreait.pjt.ViewResolver;
import com.koreait.pjt.db.UserDAO;
import com.koreait.pjt.vo.LoginHistroryVO;
import com.koreait.pjt.vo.UserVO;


@WebServlet("/login")
public class LoginSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getRemoteAddr().equals("192.168.2.6")) {		
			response.sendRedirect("/blockip");
		}
		
		ViewResolver.forword("user/login", request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		String encrypt_pw = MyUtils.encryptString(user_pw);
		
		UserVO param = new UserVO();
		param.setUser_id(user_id);
		param.setUser_pw(encrypt_pw);
		
		int result = UserDAO.login(param); //param의 주소값
		
		if(result !=1) {
			request.setAttribute("data", param);
			switch(result) {
			case 2 : request.setAttribute("msg", "비밀번호가 다릅니다");
			break;
			case 3 : request.setAttribute("msg", "아이디가 다릅니다");
			break;
			default : request.setAttribute("msg", "로그인을 실패했습니다");
			}
			param = null;
			doGet(request, response);
			return;
		}
		
		//로그인 히스토리 기록
		String agent = request.getHeader("User-Agent");
		System.out.println("agent : " + agent);
		String os = getOs(agent);
		String browser = getBrowser(agent);
		String ip_addr = request.getRemoteAddr();
		
		System.out.println("os : " + os);
		System.out.println("browser : " + browser);
		System.out.println("ip_addr : " + ip_addr);
		
		LoginHistroryVO ulhVO = new LoginHistroryVO();
		ulhVO.setI_user(param.getI_user());
		ulhVO.setOs(os);
		ulhVO.setIp_addr(ip_addr);
		ulhVO.setBrowser(browser);
		UserDAO.insUserLoginHistory(ulhVO);
		
		
		HttpSession hs = request.getSession();
		hs.setAttribute(Const.LOGIN_USER, param);
		
		System.out.println("로그인 성공");
		response.sendRedirect("/board/list");
		
	}
	private String getBrowser(String agent) {
		if(agent.toLowerCase().contains("msie")) {
			return "IE";
		} else if(agent.toLowerCase().contains("chrome")) {
			return "chrome";
		} else if(agent.toLowerCase().contains("safari")) {
			return "safari";
		}
		return "";
	}
	private String getOs(String agent) {
	
		if(agent.contains("mac")) {
			return "mac";
		} else if(agent.toLowerCase().contains("windows")) {
			return "windows";
		} else if(agent.toLowerCase().contains("x11")) {
			return "linux";
		} else if(agent.toLowerCase().contains("android")) {
			return "android";
		} else if(agent.toLowerCase().contains("iphone")) {
			return "ios";
		}
		return "";
	}

}
