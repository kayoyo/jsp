package com.koreait.board.param;

public class Utils {
	
	public static int parseStrToInt(String strI_board, int num) { 
	
		try {
			return Integer.parseInt(strI_board);
		} catch(Exception e) {
			return num;
		}
	}
}
		 
