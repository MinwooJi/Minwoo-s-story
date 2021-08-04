package com.ssafy.java.day01_01static;

public class Test02 {
	static int data = 100;
	String msg;
	void call() {
		System.out.println(data);
		System.out.println(msg);
	}
	public static void main(String[] args) {
		//		System.out.println(msg); // static area에서는 non-static 을 볼수 없다.
		//		call();
		// 먼저 와있는 사람이 나중에 온 사람을 못본다. static에서 다른 instance변수를 접근하려면 new를 이용하여 객체를 생성한 이후에 사용가능
		Test02 t = new Test02();
		System.out.println(t.msg);
		
		System.out.println(data); // static영역에서 다른 static은 접근 할수있다. 
		// static은 web에서 무서운 존재
		// static 변수는 공요으로 사용하기 떄문에 한사람이 자기정보를 바꿧을떄 다른사람의 정보가 바뀔수도 있고
		// web특성상 프로그램이 꺼지지 않는데 이렇게 되면 항상 그메모리 영억을 차지하는 것이 된다.

	}
}
