package com.ssafy.java.day01_01static;
/**
 * static : 정적인 특성 부여
 * - 적용 가능 : 클래스(X),내부클래스(O), 메서드(O), 필드(변수)(O)
 * - 클래스가 로딩되는 시점에 결정됨
 * - 객체 생성없이 클래스로만 접근 가능
 *   Class.필드명, Class.메서드 명
 *   
 * - static block : 클래스가 로딩될 때 한번만 수행
 * - instance block : 객체가 생성될 때 마다 수행, 생성자보다 먼저 수행, 각 생성자에서 공용으로 처리하는 작업을 할수있다.
 */
class Ticket{
	int price; // instance variable, field
	static int totCnt = 10; // class variable
	static void call() {} // class method
}
public class Test01 {
	public static void main(String[] args) {
		Ticket t = new Ticket();
		System.out.println(t.totCnt); // t가 가리키는 Heap영역의 인스턴스 Ticket을 갔다가 Ticket에서 class area로 다시 보내서 
		                              // static 변수를 찾음( 뺑뻉이 돌림, 좋지않은 접근 )
		System.out.println(Ticket.totCnt); // 스테틱 필드인 totcnt를 바로 static way로 접근
		Ticket.call();
	}
}
