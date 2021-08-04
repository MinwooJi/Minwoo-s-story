package com.ssafy.happyhouse.model.dto;
//회원 
public class Member {


	// 아이디 
	private String id;

	// 비밀번호 
	private String password;

	// 이름 
	private String name;

	// 기본주소 
	private String addressDefault;

	// 휴대폰전화번호 
	private String phonenum;

	// 상세주소 
	private String addressDetail;

	// 우편번호 
	private String postnum;

	// 집전화번호 
	private String homenum;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddressDefault() {
		return addressDefault;
	}

	public void setAddressDefault(String addressDefault) {
		this.addressDefault = addressDefault;
	}

	public String getPhonenum() {
		return phonenum;
	}

	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public String getPostnum() {
		return postnum;
	}

	public void setPostnum(String postnum) {
		this.postnum = postnum;
	}

	public String getHomenum() {
		return homenum;
	}

	public void setHomenum(String homenum) {
		this.homenum = homenum;
	}

	// Member 모델 복사
	public void CopyData(Member param)
	{
		this.id = param.getId();
		this.password = param.getPassword();
		this.name = param.getName();
		this.addressDefault = param.getAddressDefault();
		this.phonenum = param.getPhonenum();
		this.addressDetail = param.getAddressDetail();
		this.postnum = param.getPostnum();
		this.homenum = param.getHomenum();
	}

	public Member() {}

	public Member(String id, String password, String name, String addressDefault, String phonenum, String addressDetail,
			String postnum, String homenum) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.addressDefault = addressDefault;
		this.phonenum = phonenum;
		this.addressDetail = addressDetail;
		this.postnum = postnum;
		this.homenum = homenum;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", password=" + password + ", name=" + name + ", addressDefault=" + addressDefault
				+ ", phonenum=" + phonenum + ", addressDetail=" + addressDetail + ", postnum=" + postnum + ", homenum="
				+ homenum + "]";
	}
	

}