package com.ssafy.java.day05.clazz03;

class Type {
	public void print() {
		System.out.println("Type 기본 제공 메서드");
	}
}

class Type1 extends Type {
	public void print() {
		System.out.println("Type1 호출됨");
	}
}

class Type2 extends Type {
	public void print() {
		System.out.println("Type2 호출됨");
	}

	public void test() {
		System.out.println("Type2의 특별한 기능");
	}

}

class Type3 extends Type {
}

public class Test03 {

	/*
	 * static void call(Type1 t) { t.print(); } static void call(Type2 t) {
	 * t.print(); }
	 */
	static void call(Type t) {
		t.print();

		if (t instanceof Type2) {
			Type2 t2 = (Type2) t;
			t2.test();
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Type1 t1 = new Type1();
		call(t1);
		Type2 t2 = new Type2();
		call(t2);
		Type3 t3 = new Type3();
		call(t3);
	}

}
