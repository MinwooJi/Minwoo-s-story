/**
 * 인터페이스 사용 특징
 *----------------------------------------------
 * 추상클래스와 동일한 특성
 * - 인터페이스 객체 생성 불가능
 * - 객체 변수로 선언 가능
 * - 자식 클래스를 이용해서 처리
 * - 자식 클래스는 인터페이스의 모든 추상메서드를 재정의 해야한다.
 *----------------------------------------------
 * 추상클래스와 다른점
 * - 일반 메서드의 구현여부 (추상클래스 : 일반 메서드 가능, 인터페이스 : 일반 메서드 불가능)
 */
package com.ssafy.java.day01_03interface;
public class Test09 {
	public static void main(String[] args) {
//		SamsungTV tv = new SamsungTV();
//		LgTV tv = new LgTV();
		TV tv = new LgTV(); 
		tv.powerOn();
		tv.channelUp();
		tv.setChannel(15);
		tv.channelDown();
		tv.powerOff();
	}
}
