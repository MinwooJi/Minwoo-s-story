package com.ssafy.java.day05.clazz01;

public class Emp02 {

	private int empNo;
	private String name;

	public Emp02(int empNo, String name) {
		this.empNo = empNo;
		this.name = name;

	}

	public Emp02() {
		this(-1,"이름 없음");
	}

	public String info() {
		return String.format("empNo = %d, name = %s", empNo, name);
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
