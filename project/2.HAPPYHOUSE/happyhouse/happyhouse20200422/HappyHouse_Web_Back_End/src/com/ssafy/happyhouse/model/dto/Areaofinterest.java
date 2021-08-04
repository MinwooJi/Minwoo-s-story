package com.ssafy.happyhouse.model.dto;

//관심지역 
public class Areaofinterest {

	// 아이디 
	private String id;

	// 법정동코드 
	private String code;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	// Areaofinterest 모델 복사
	public void CopyData(Areaofinterest param)
	{
		this.id = param.getId();
		this.code = param.getCode();
	}

	@Override
	public String toString() {
		return "Areaofinterest [id=" + id + ", code=" + code + "]";
	}

}
