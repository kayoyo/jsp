package com.koreait.matzip.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//Interface 부모 역할(가르킬때만 사용, 객체화 불가, select)
public interface JdbcSelectInterface {
	
	void prepared(PreparedStatement ps)throws SQLException; //값 추출할 준비단계
	void executeQuery(ResultSet rs) throws SQLException; //값 추출
	
	

}
