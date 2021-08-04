/* 오버라이딩(재정의)
 * - 메서드의 다형성 : 오버로딩, 오버라이딩
 * - 필수 조건
 *   1. 메서드 이름과 파라미터 타입과 갯수가 같아야 한다.
 *   2. 접근제한자는 부모의 접근 제한자보다 좁으면 안된다.
 *   	(같거나 커야한다.)
 *   3. 반환타입은 부모의 반환타입과 같거나 자식 타입을 설정 할 수 있다.
 *   
 *  자바의 모든 클래스는 java.lang.Object를 상속받는다.
 *  자바의 클래스간 상속은 단일 상속을 지원한다.
 *  - class A extends B, C (X)
 *  - 다중상속 개념이 필요할 떄는 인터페이스를 이용한다.
 *  
 *  toString() 메서드 이해하기
 *  
 *  java.lang : 프로그램 작성시 기본으로 사용되는 클래스들이 모여있는 패키지
 *  		  : 자동 import 된다.
 */

package com.ssafy.java.day05.clazz01;

import java.util.ArrayList;
import java.util.Date;

public class Test03 {

	public static void main(String[] args) {
		Dog d = new Dog("메리", 2);
		System.out.println(d);// 밑과 같은 결과
		System.out.println(d.toString());
		
		int[] a = {1};
		System.out.println(a);
		
		String s = new String("a");
		System.out.println(s.toString());
		
		Date d2 = new Date();
		System.out.println(d2);
		
		ArrayList<String> list = new ArrayList<>();
		list.add("a");
		list.add("b");
		System.out.println(list);
		
	}	
}
