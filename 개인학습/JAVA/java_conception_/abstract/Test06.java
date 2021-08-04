/**
 * 추상클래스를 사용할떄 주의사항
 * 
 * - 객체 생성을 할 수 없다(구현이 안되었으므로??)
 *  ex> new 추상클래스(); (X)
 * - 객체 변수로 선언이 가능(응??)
 *   > 객체 변수의 값을 만들기 위해서 자식 클래스를 이용한다. (묵시적 형변환 - 오호~)
 *   추상메서드를 자식클래스에서 강제로 오버라이딩을 하게 만들었음(안하면 컴파일 에러 발생 - 굉장히 치밀하다..)
 *   
 */
package com.ssafy.java.day01_02abstract;
abstract class AbsSuper{
	public void test() {
		
	}
	public abstract void call();
}
class AbsSub extends AbsSuper{
	public void call() { // abstract 활용제한자는 뺴고 .. 
		System.out.println("call() 호출");
	}
}
public class Test06 {
	public static void main(String[] args) {
//		AbsSuper aSuper = new AbsSuper(); // Cannot instantiate the type AbsSuper
//		AbsSuper aSuper = null;
		AbsSuper aSuper = new AbsSub();
		aSuper.test();
		aSuper.call();
	}
}
