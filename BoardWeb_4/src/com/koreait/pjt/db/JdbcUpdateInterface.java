package com.koreait.pjt.db;


import java.sql.PreparedStatement;
import java.sql.SQLException;

//Interface 부모 역할(가르킬때만 사용, 객체화 불가, insert/delete/update)
public interface JdbcUpdateInterface { 
	int update(PreparedStatement ps) throws SQLException; //public abstract 생략 가능
	
}