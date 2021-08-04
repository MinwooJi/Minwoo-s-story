/**
 * 추상클래스 - 구체화되지 않은것?? 구현이 안된것
 * - 키워드 : abstract
 * - abstract 사용 가능  : 클래스(0), 메서드(0), 필드(변수)(X)
 *   abstract int i; (X)
 *   abstract int call(); (O)
 *   abstract class Test(O)
 *   
 * - abstract class 
 *   : 추상클래스(abstract 적용된 클래스)
 *   : 추상메서드를 포함하는 클래스는 반드시 추상 클래스로 선언되어야 한다.
 *   : 구현된 메서드 + 추상 메서드 
 * - abstract method
 *   : 추상클래스(abstract 적용된 메서드)
 *   : 메서드의 선언만 있고 구현부가 없다({} 괄호자체가 없어야한다.)
 *     
 */
package com.ssafy.java.day01_02abstract;
abstract public class Test05 { // 추상클래스는 반드시 추상메서드를 가질필요는 없지만 추상메서드는 반드시 추상클래스 내에 써야한다
	void test() {} // 구현된 메서드 (괄호안에 아무것도 없게끔 구현된것)
	//void call(); // 구현되지 않았음에도 abstract 키워드가 없음(컴페일 에러)
	abstract void call(); // 추상메서드
//	abstract void call() {} // 추상메서드(컴파일 에러 - 추상메서드는 메서드 바디를 가질수 없다(구현부를 가질수없다))
}
