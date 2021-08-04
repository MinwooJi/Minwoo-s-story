/**
 * 사용자 정의 예외 만들기
 * 
 * - API에서 인식하지 못하는 예외 상황을 처리할수 있는 예외 클래스를 정의
 * - 예외 클래스를 상속하면 사용자정의 예외 클래스가 됨..
 * - 컴파일시 발생 예외 클래스 : RuntimeException 타입 이외의 클래스를 상속
 * - 런타임시 발생 예외 클래스 : RuntimeException 타입의 클래스를 상속
 */
package com.ssafy.java.day02_05exeption;

import java.util.Scanner;

public class Test08 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("주민번호 입력: ");
		String juminNo = sc.next();
		try {
			if(juminNo.length() != 13) {
				throw new JuminNumberException(juminNo); // 허용범위가 아니면 exception을 던진다
				// 예외 발생시킨다. JuminNumberExeption
				// 직접 강제 예외를 발생시키기
				// throw 예외 객체
				// No exception of type JuminNumberException can be thrown; 
				// an exception type must be a subclass of Throwable
				//			throw new JuminNumberException();
			}
			System.out.println("정상적인 주민번호 입니다.");
		}catch(JuminNumberException e) {
			String errMsg = e.getMessage();
			System.out.println("에러 ->" + errMsg);
			e.printStackTrace();
		}
	}
}
