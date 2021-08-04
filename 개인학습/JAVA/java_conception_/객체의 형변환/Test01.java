/* 객체의 형변환
 * 
 * - 객체 = 객체, 좌변과 우변의 객체의 타입이 다를때 발생
 * - 조건 : 상속관계가 있어야 한다.
 * - 묵시적 형변환
 *   부모 = 자식
 * - 명시적 형변환
 *   자식 = (자식을 받을 수 있는 타입)부모
 *   
 * 묵시적 형변환 연습
 * - 형변환된 객체는 자신 클래스에 선언된 변수와 메서드를 사용할 수 있다.
 * - 단, 자식 클래스에 부모클래스의 메서드를 오버라이딩 했을 경우 자식 클래스의 메서드가 호출된다.
 * 
 */
package com.ssafy.java.day05.clazz03;

class Animal{
	String name = "Animal";
	public void info() {
		System.out.println("동물 입니다.");
	}
}
class Cat extends Animal{
	String name = "Cat";
	String house = "민성";
	public void info() {
		System.out.println("고양이 입니다.");
	}
}

public class Test01 {

	public static void main(String[] args) {
//		Animal a = new Animal();
//		Cat C = (Cat)new Animal();  //런타임 에러
//		Cat C = new Animal();  //컴파일 에러
		Animal a = new Cat();	//묵시적 형변환
		//Animal이 출력
		Cat C = (Cat)a;
		System.out.println(a.name);
		System.out.println(C.name);
		
		//하지만 오버라이딩 할 경우 "고양이 입니다"가 출력
		a.info();
		
//		System.out.println(a.house);
	}

}
