package com.ssafy.happyhouse.model.dto;

public class HouseDealLab {
	private int no;
	private String dong;
	private String aptName;
	private String dealAmount;
	private int dealAmountInt;

	public void setNo(int no) {
		this.no = no;
	}

	public int getNo() {
		return no;
	}

	public void setDong(String dong) {
		this.dong = dong;
	}

	public String getDong() {
		return dong;
	}

	public void setAptName(String aptName) {
		this.aptName = aptName;
	}

	public String getAptName() {
		return aptName;
	}

	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}

	public String getDealAmount() {
		return dealAmount;
	}

	public void setDealAmountInt(int dealAmountInt) {
		this.dealAmountInt = dealAmountInt;
	}

	public int getDealAmountInt() {
		return dealAmountInt;
	}
}
