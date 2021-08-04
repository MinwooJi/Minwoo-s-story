package com.ssafy.happyhouse.model.dto;

//지역 
public class Region {

	// 법정동코드 
	private String code;

	// 도광역시 
	private String domegalopolis;

	// 시구군 
	private String citygugun;

	// 동 
	private String dong;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDomegalopolis() {
		return domegalopolis;
	}

	public void setDomegalopolis(String domegalopolis) {
		this.domegalopolis = domegalopolis;
	}

	public String getCitygugun() {
		return citygugun;
	}

	public void setCitygugun(String citygugun) {
		this.citygugun = citygugun;
	}

	public String getDong() {
		return dong;
	}

	public void setDong(String dong) {
		this.dong = dong;
	}

	// Region 모델 복사
	public void CopyData(Region param)
	{
		this.code = param.getCode();
		this.domegalopolis = param.getDomegalopolis();
		this.citygugun = param.getCitygugun();
		this.dong = param.getDong();
	}

	@Override
	public String toString() {
		return "Region [code=" + code + ", domegalopolis=" + domegalopolis + ", citygugun=" + citygugun + ", dong=" + dong
				+ "]";
	}

	public Region() {}

	public Region(String code, String domegalopolis, String citygugun, String dong) {
		super();
		this.code = code;
		this.domegalopolis = domegalopolis;
		this.citygugun = citygugun;
		this.dong = dong;
	}
	

}