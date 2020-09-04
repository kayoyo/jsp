package com.koreait.pjt.db;

import com.koreait.pjt.vo.BoardVO;

public class BoardDomain extends BoardVO {
	
		private String nm;
		private int record_cnt; //페이지당 나오는 레코드 수(게시글 수)
		private int eIdx;
		private int sIdx;
		private String searchText;
		private String searchType;
		private String profile_img;
		private int like_cnt;
		private int count_cmt;
		private int you_like;

		
		
		public String getSearchType() {
			return searchType;
		}

		public void setSearchType(String searchType) {
			this.searchType = searchType;
		}

		public int getLike_cnt() {
			return like_cnt;
		}

		public void setLike_cnt(int like_cnt) {
			this.like_cnt = like_cnt;
		}

		public int getCount_cmt() {
			return count_cmt;
		}

		public void setCount_cmt(int count_cmt) {
			this.count_cmt = count_cmt;
		}

		public int getYou_like() {
			return you_like;
		}

		public void setYou_like(int you_like) {
			this.you_like = you_like;
		}

		public String getProfile_img() {
			return profile_img;
		}

		public void setProfile_img(String profile_img) {
			this.profile_img = profile_img;
		}

		public String getSearchText() {
			return searchText;
		}

		public void setSearchText(String searchText) {
			this.searchText = searchText;
		}

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
