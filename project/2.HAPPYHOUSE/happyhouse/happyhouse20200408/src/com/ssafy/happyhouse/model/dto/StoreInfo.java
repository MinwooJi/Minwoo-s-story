package com.ssafy.happyhouse.model.dto;

//상가정보 
public class StoreInfo {

 // 상가번호 
 private int no;

 // 상가명 
 private String shopname;

 // 지역명 
 private String localname;

 // 대분류코드 
 private String code1;

 // 대분류명 
 private String codename1;

 // 중분류코드 
 private String code2;

 // 중분류명 
 private String codename2;

 // 소분류코드 
 private String code3;

 // 소분류명 
 private String codename3;

 // 표준산업분류코드 
 private String code4;

 // 표준산업분류명 
 private String codename4;

 // 시도코드 
 private String citycode;

 // 시도명 
 private String city;

 // 시군구코드 
 private String gucode;

 // 시군구명 
 private String gu;

 // 동코드 
 private String dongcode;

 // 동명 
 private String dong;

 // 지번주소 
 private String jibunaddress;

 // 도로명주소 
 private String address;

 // 구우편번호 
 private String oldpostcode;

 // 신우편번호 
 private String postcode;

 // 위도 
 private String lng;

 // 경도 
 private String lat;

 
 
 
 public StoreInfo(int no, String shopname, String localname, String code1, String codename1, String code2,
		String codename2, String code3, String codename3, String code4, String codename4, String citycode, String city,
		String gucode, String gu, String dongcode, String dong, String jibunaddress, String address, String oldpostcode,
		String postcode, String lng, String lat) {
	this.no = no;
	this.shopname = shopname;
	this.localname = localname;
	this.code1 = code1;
	this.codename1 = codename1;
	this.code2 = code2;
	this.codename2 = codename2;
	this.code3 = code3;
	this.codename3 = codename3;
	this.code4 = code4;
	this.codename4 = codename4;
	this.citycode = citycode;
	this.city = city;
	this.gucode = gucode;
	this.gu = gu;
	this.dongcode = dongcode;
	this.dong = dong;
	this.jibunaddress = jibunaddress;
	this.address = address;
	this.oldpostcode = oldpostcode;
	this.postcode = postcode;
	this.lng = lng;
	this.lat = lat;
}

public int getNo() {
     return no;
 }

 public void setNo(int no) {
     this.no = no;
 }

 public String getShopname() {
     return shopname;
 }

 public void setShopname(String shopname) {
     this.shopname = shopname;
 }

 public String getLocalname() {
     return localname;
 }

 public void setLocalname(String localname) {
     this.localname = localname;
 }

 public String getCode1() {
     return code1;
 }

 public void setCode1(String code1) {
     this.code1 = code1;
 }

 public String getCodename1() {
     return codename1;
 }

 public void setCodename1(String codename1) {
     this.codename1 = codename1;
 }

 public String getCode2() {
     return code2;
 }

 public void setCode2(String code2) {
     this.code2 = code2;
 }

 public String getCodename2() {
     return codename2;
 }

 public void setCodename2(String codename2) {
     this.codename2 = codename2;
 }

 public String getCode3() {
     return code3;
 }

 public void setCode3(String code3) {
     this.code3 = code3;
 }

 public String getCodename3() {
     return codename3;
 }

 public void setCodename3(String codename3) {
     this.codename3 = codename3;
 }

 public String getCode4() {
     return code4;
 }

 public void setCode4(String code4) {
     this.code4 = code4;
 }

 public String getCodename4() {
     return codename4;
 }

 public void setCodename4(String codename4) {
     this.codename4 = codename4;
 }

 public String getCitycode() {
     return citycode;
 }

 public void setCitycode(String citycode) {
     this.citycode = citycode;
 }

 public String getCity() {
     return city;
 }

 public void setCity(String city) {
     this.city = city;
 }

 public String getGucode() {
     return gucode;
 }

 public void setGucode(String gucode) {
     this.gucode = gucode;
 }

 public String getGu() {
     return gu;
 }

 public void setGu(String gu) {
     this.gu = gu;
 }

 public String getDongcode() {
     return dongcode;
 }

 public void setDongcode(String dongcode) {
     this.dongcode = dongcode;
 }

 public String getDong() {
     return dong;
 }

 public void setDong(String dong) {
     this.dong = dong;
 }

 public String getJibunaddress() {
     return jibunaddress;
 }

 public void setJibunaddress(String jibunaddress) {
     this.jibunaddress = jibunaddress;
 }

 public String getAddress() {
     return address;
 }

 public void setAddress(String address) {
     this.address = address;
 }

 public String getOldpostcode() {
     return oldpostcode;
 }

 public void setOldpostcode(String oldpostcode) {
     this.oldpostcode = oldpostcode;
 }

 public String getPostcode() {
     return postcode;
 }

 public void setPostcode(String postcode) {
     this.postcode = postcode;
 }

 public String getLng() {
     return lng;
 }

 public void setLng(String lng) {
     this.lng = lng;
 }

 public String getLat() {
     return lat;
 }

 public void setLat(String lat) {
     this.lat = lat;
 }

@Override
public String toString() {
	return "상점명=" + shopname + ", 지점명=" + localname
			+ ", codename1=" + codename1 +  ", codename2=" + codename2 
			+ ", codename3=" + codename3 + ", codename4=" + codename4
			+ ", city=" + city + ", gu=" + gu + ", dong=" + dong
			+  ", address=" + address +  "]";
}

 
 
}