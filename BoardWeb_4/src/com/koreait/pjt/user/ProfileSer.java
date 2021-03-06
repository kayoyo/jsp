package com.koreait.pjt.user;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.pjt.MyUtils;
import com.koreait.pjt.ViewResolver;
import com.koreait.pjt.db.UserDAO;
import com.koreait.pjt.vo.UserVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


@WebServlet("/profile")
public class ProfileSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   //프로필 화면(나의 프로필 이미지, 이미지 변경 가능한 화면)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserVO loginUser = MyUtils.getLoginUser(request);
		request.setAttribute("data", UserDAO.selUser(loginUser.getI_user()));
		
		ViewResolver.forword("/user/profile", request, response);
	}

	// 이미지 변경 처리 : 쿼리스트링으로는 이미지 업로드 한계가 있음, 파일 업로드는 무조건 post방식
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			UserVO loginUser = MyUtils.getLoginUser(request);
			
			String savePath = getServletContext().getRealPath("img") + "/user/" + loginUser.getI_user();
			System.out.println("savePath :" + savePath);
			// 절대경로 : img / user / 생성된 directory 
			
			//만약 폴더가 없다면 폴더 생성 - 처음 프로필을 올리는 사람은 당연히 폴더가 없음
			File directory = new File(savePath);
			if(!directory.exists()) {
				directory.mkdirs();
			}
			
			int maxFileSize = 10_485_760; // 1024 * 1024 * 10 : 최대 파일 사이즈 크기
			//String originFileNm = "";
			String fileNm = "";
			String saveFileNm = null;
			
			try {
				MultipartRequest mr = new MultipartRequest(request, savePath, maxFileSize, "UTF-8", new DefaultFileRenamePolicy());
				
				Enumeration files = mr.getFileNames();
				
				while(files.hasMoreElements()) {
					//originFileNm = mr.getOriginalFileName(key);	
					String key = (String)files.nextElement();
					fileNm = mr.getFilesystemName(key);
					//lastIndexOf : 검색된 문자열이 처음 발견된 위치에 해당하는 index 값
					//substring : 문자열에서 특정 부분만 골라낼 때 
					String extension = fileNm.substring(fileNm.lastIndexOf("."));
					//System.out.println(extension);
					saveFileNm = UUID.randomUUID() + extension;
					
					File oldFile = new File(savePath + "/" + fileNm);
					File newFile = new File(savePath + "/" + saveFileNm);
					
					oldFile.renameTo(newFile);
				
					System.out.println("key : " + key);
					System.out.println("fileNm : " + fileNm);
					//System.out.println("originFileNm : " + originFileNm);
							
				}
				
			} catch(Exception e) {
				e.printStackTrace();
			}
			if(saveFileNm != null) { //DB에 프로필 파일명 저장
				UserVO param = new UserVO();
				param.setProfile_img(saveFileNm);
				param.setI_user(loginUser.getI_user());
				
				UserDAO.upUser(param);
			}
			
			response.sendRedirect("/profile");
			
	}

}
