package com.ssafy.happyhouse.model.dto;

public class Search {
	private String[] dealType;
	private String searchType;
	private String searchWord;
	
	public String[] getDealType() {
		return dealType;
	}
	public void setDealType(String[] dealType) {
		this.dealType = dealType;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	
}