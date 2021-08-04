/*
 * 
 */
package com.ssafy.java.day02_05exeption;

public class Test05 {
	public static void main(String[] args) {
		try {
			System.out.println(1);
			String s = null;
			System.out.println(s.length()); // NullPointerExeption
			System.out.println(2);
		}catch(Exception e){
			System.out.println(3);
			//return;						// 1 - 3 - 4 // try~~~ finally 까지 하나로 보고 return을 뒤로 보류
			System.exit(0);					// 1 - 3 반환하는 것이 아니라 실행되던 프로세스를 강제로 멈추는 개념이기에 finally로 가지 않음 // ctrl alt del 작업관리자에서 작업끝내기 하는 것과 같음
//											// finally를 수행하지 않는 유일한 case
		}finally{ // 예외가 발생해도 무조건 finally는 수행한다  앞에 리턴이 있더라도..!
			System.out.println(4);		
		}
		System.out.println(5);
	}
}
