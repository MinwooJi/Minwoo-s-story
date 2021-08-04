/*
 *  상속과 생성자
 *  
 *  클래스가 다른 클래스를 명시적으로 받았을 경우만 아래의 케이스를 생각하자..
 *  
 *  생성자 정의시 주의사항...
 *  컴파일 시 생성자 내에 자동으로 처리되는 일
 *  1. 생성자 내에서 super([파라미터,...]) 또는 this([파라미터,...]) 생성자 호출하는 코드를 찾는다.
 *  2. 만약, 찾지 못한 경우 컴파일러는 자동으로 super() 생성자 호출하는 코드를 추가한다.
 */
package com.ssafy.java.day05.clazz01;

class Parent{
	Parent(int i){
		
	}
	
}

class Child extends Parent{
	Child(){
		super(1);
	}
}

public class Test04 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
