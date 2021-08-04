/**
 * final
 * - 변수 : 상수 적용
 * - 메서드 : 오버라이딩 금지
 * - 클래스 상속 금지
 */
package com.ssafy.java.day01_01static;

import java.util.Random;

/* final : 상속금지 */ class Parent{
	public /* final : 오버라이딩 */void call() {}
	// 이 메소드의 대한 내용정의는 내가 마지막이니
	// 아무도 오버라이딩 하지마라.
	public void print() {}
}
class Child extends Parent{
	public void call() {}
}
class Sub04{
	final int i; // 여기서 바로 값을 할당하거나, 생성자에서 반드시 값을 할당해야한다
	Sub04(){
		// final 멤버변수가 선언만되있고 할당이 없으면 오류가 뜸
		// 생성자에서 final 변수의 값을 할당할수있다. why 값을 한번만 할당할수있기에
		i = 89;
	}
	public void call() {
		final int k;
		k = 100;
		System.out.println(k);
	}
}
//class MyString extends String{
class MyRandom extends Random{ // api중에서도 상속가능한 final이 아닌 class가 있다
	public int nextInt(int bound) {
		return super.nextInt(bound)+1;
	}
	
}
public class Test04 {
	public static void main(String[] args) {
		/* final : 값의 할당은 한번만*/ 
		int i = 100;
		i = 120;
//		Random r = new Random();
		Random r = new MyRandom();
		//int num = r.nextInt(45) + 1;
		int num = r.nextInt(45);
	}
}
