package com.ssafy.java.day05.clazz01;

public class Mng02 extends Emp02{
	
	private Emp02[] mngList = new Emp02[2];
	private int pos = 0;
	
	public void addEmp02(Emp02 emp) {
		if(pos != mngList.length)
			mngList[pos++] = emp;
	}
	
	public Mng02(int empNo, String name) {
		super(empNo,name);
		
		
	}

	public Mng02() {
		this(-1,"이름 없음");
	}

	public String info() {
		StringBuffer sb = new StringBuffer();
		sb.append(super.info());
		sb.append("\n관리하는 사원의 정보\n");
		sb.append("-----------------------------------\n");
		for(int i = 0; i<pos;i++) {
			Emp02 emp = mngList[i];
			sb.append(emp.info()+"\n");
		}
		sb.append("-----------------------------------\n");
		return sb.toString();
	}

}
