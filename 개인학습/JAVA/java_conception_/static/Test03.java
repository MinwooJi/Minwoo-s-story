package com.ssafy.java.day01_01static;
class Sub03{
	int i = 100;
	static int k = 100;
	// static 블럭은 클래스 정보가 로딩되는 시점에 한번만 수행
	static {
		k = 200;
		System.out.println(1);
	}
	// 객체가 생성될떄 마다
	Sub03(){
		System.out.println(2);
	}
	// 인스턴스 블럭:객체가 생성될 떄 마다, 생성자 호출 이전에 실행
	{
		System.out.println(3);
	}
}
public class Test03 {
	public static void main(String[] args) {
//		System.out.println("main start");
//		Sub03 s = new Sub03();
//		Sub03 s2 = new Sub03();
//		System.out.println("main end");
		
		System.out.println("main start");
		System.out.println(Sub03.k); // k라는 변수를 사용하겠다. -> 클래스 정보가 로딩됨
		Sub03 s = null; // = null은 클래스르 사용하는게아님. 클래스의 변수나 메서드를 부르거나, new를 했을때 클래스를 사용하는 것이고 class area에 올라감
		System.out.println(4);
		s = new Sub03();
		System.out.println(5);
		Sub03 s2 = new Sub03();
		System.out.println("main end");
	}
}
