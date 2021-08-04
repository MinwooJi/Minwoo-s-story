/*
 * 상속의 특징
 * 
 * 1. 재사용성과 확장성
 * 2. extends 키워드 사용
 * 	  class A extends B(상속할 클래스명)
 * 	  A 클래스는 B 클래스를 상속한다.
 * 	  A 클래스는 B 클래스의 변수 및 메서드를 사용 할 수 있다.
 * 	  단, 접근제한자가 허용하는 범위내에서 가능
 * 3. 클래스의 관계
 * 	  A - 자식, sub, 하위
 * 	  B - 부모, super, 상위
 * 4. 부모클래스를 접근할 때 사용하는 키워드
 *	  - super : 현재 실행중인 객체의 부모 객체
 *	  - super.변수
 *	  - super.메서드
 *	  - super([파라미터, ...])
 *		: 생성자 내에서만 사용 가능
 *		: 첫번째 구문으로 호출되어야 한다.
 */
package com.ssafy.java.day05.clazz01;


public class Test02 {
	public static void main(String[] args) {
		Emp02 e1 = new Emp02();
		e1.setEmpNo(1);
		e1.setName("김지석");
		
		Emp02 e2 = new Emp02(2, "김연아");
		
		Mng02 m = new Mng02(3, "손영길");
		m.addEmp02(e1);
		m.addEmp02(e2);
		
		System.out.println(m.info());
		/*
		System.out.println(e1.info());
		System.out.println(e2.info());
		*/
		
	}
}
