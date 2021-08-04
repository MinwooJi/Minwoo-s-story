/*
 * 명시적 형변환
 * 
 * 자식 = 부모 : 컴파일 에러
 * 자식 = (타입)부모 : 형변환 연산자를 이용한 변환
 * 
 * 부모 객체 자리에 올 수 있는 것은 묵시적 형변환된 객체만 가능하다.
 */

package com.ssafy.java.day05.clazz03;

class Fruit{}

class Apple extends Fruit{}

public class Test02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Fruit f = new Fruit();
//		Apple a = f;	// Apple(자식) = Fruit(부모), 컴파일 에러발생
//		Apple a = (Apple)f; // f가 가리키는 메모리에 Apple 객체의 정보가 없기 때문에 실행시에 에러발생
		Fruit f = new Apple(); // f 객체는 묵시적 형변환(f가 가리키는 메모리에는 Apple 정보가 포함)
		Apple a = (Apple)f;
		System.out.println(1);
		

	}

}
