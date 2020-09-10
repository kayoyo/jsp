package com.koreait.matzip.user;

import com.koreait.matzip.SecurityUtils;
import com.koreait.matzip.vo.UserVO;

public class UserService {
	
	private UserDAO dao;
	
	public UserService() {
		dao = new UserDAO();
	}
	
	public int join(UserVO param) {
		
		String pw = param.getUser_pw();
		String salt = SecurityUtils.generateSalt();
		String encryptPw = SecurityUtils.getEncrypt(pw, salt); //암호화된 비밀번호
		
		param.setUser_pw(encryptPw);
		param.setSalt(salt); //가입할 때 딱 한번만 발행
		
		return dao.join(param);
	}
	
	public int login(UserVO param) {
		int result = 0;
		
		UserVO dbResult = dao.selUser(param);
		
		if(dbResult.getI_user() == 0) { //아이디 없음
			result = 2;
		} else {
			String salt = dbResult.getSalt();
			String encryptPw = SecurityUtils.getEncrypt(param.getUser_pw(), salt); //암호화된 비밀번호
			
			if(encryptPw.equals(dbResult.getUser_pw())) { //암호화된 비밀번호 = dB 비밀번호
				//id와 pw는 service.login(param)에 들어가 있음
				param.setUser_pw(null);
				param.setI_user(dbResult.getI_user());
				param.setNm(dbResult.getNm());
				param.setProfile_img(dbResult.getProfile_img());
				
				param = dbResult;
				
				result = 1; //비밀번호가 같다(로그인 가능)
			} else { //비밀번호가 다르다(아이디는 같다) 
				result = 3; 
			}
			}
		
		return result;
	}
}
