package com.koreait.pjt.db;

import com.koreait.pjt.vo.BoardVO;

public class BoardDomain extends BoardVO {
	
		private String nm;
		private int record_cnt; //페이지당 나오는 레코드 수
		private int eIdx;
		private int sIdx;

		public int geteIdx() {
			return eIdx;
		}

		public void seteIdx(int eIdx) {
			this.eIdx = eIdx;
		}

		public int getsIdx() {
			return sIdx;
		}

		public void setsIdx(int sIdx) {
			this.sIdx = sIdx;
		}

		public int getRecord_cnt() {
			return record_cnt;
		}

		public void setRecord_cnt(int record_cnt) {
			this.record_cnt = record_cnt;
		}

		public String getNm() {
			return nm;
		}

		public void setNm(String nm) {
			this.nm = nm;
		}
}