/**
 * 인터페이스에 선언되는 모든 변수는 자동으로 상수로 인식된다. (public static final 키워드가 자동 추가됨)
 * 인터페이스에 선언되는 모든 메서드는 추상 메서드로 인식한다.(public abstract 키워드가 자동 추가됨)
 *  - 단, 1.8버전에 추가된 default,static은 제외
 */
package com.ssafy.java.day01_03interface;
interface SeverConfig{
	/*public static final*/String IP = "192.168.0.172";
//	/*public abstrct*/void info(){} //Abstract methods do not specify a body
	/*public abstrct*/void info();
}
public class Test08 {
	public static void main(String[] args) {
		System.out.println(SeverConfig.IP); // static 확인
//		SeverConfig.IP = "localhost"; // The final field SeverConfig.IP cannot be assigned
		// final 확인
	}
}
