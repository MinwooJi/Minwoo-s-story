/**
 * 인터페이스 
 * --------------------
 * - 완벽한 추상화된 클래스(추상 메서드만 선언 가능 - 1.8버전 부터  default, static 메서드 선언가능)
 * - 구현된 메서드 (X) + 추상메서드
 * - 클래스 선언 "class 이름" 대신에 "interface 이름"을 사용하면 인터페이스로 인식
 * - 추상클래스와의 차이점 : 다중 상속이 가능, 클래스 간의 상속은 단일 상속만 가능
 * - 상속
 *   class -> class : extends, 단일 상속만 간으 ,extends A,B(X) extends A(O)
 *   interface -> interface(인터페이스 끼리) : extends, 다중상속 가능, extends A,B,C(O) 
 *   class -> interface(클래스가 인터페이스를 상속받음) : implements, 다중상속 가능, inplements A,B,C(O)
 *   interface -> class(인터페이스가 클래스를 상속받음) : 불가능
 */
package com.ssafy.java.day01_03interface;
interface Inter01{}
interface Inter02{}
interface Inter03 extends Inter01{}
interface Inter04 extends Inter01,Inter02{} // 다중 상속 가능
class Class01{}
class Class02{}
class Class03 extends Class01{}
//class Class04 extends Class01,Class02{} // 다중상속이 불가능함
class Class05 implements Inter01,Inter02{} // 다중상속이 가능
class Class06 extends Class01 implements Inter01,Inter02{} // extends implemnts 동시에도 가능->순서는 항상 extends implements순서
public class Test07 {
	public static void main(String[] args) {
		
	}
}
