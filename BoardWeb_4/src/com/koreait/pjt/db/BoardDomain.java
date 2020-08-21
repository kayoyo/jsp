package com.koreait.pjt.db;

import com.koreait.pjt.vo.BoardVO;

public class BoardDomain extends BoardVO {
	
		private String nm;

		public String getNm() {
			return nm;
		}

		public void setNm(String nm) {
			this.nm = nm;
		}
}
