/**
 * 직접처리 + 간접처리
 * (특이 case)
 */
package com.ssafy.java.day02_05exeption;

import java.io.FileNotFoundException;
import java.io.FileReader;

class Boss{
	public void manage() {
		Alba alba = new Alba();
		try {
			alba.work();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
class Alba{
	public void work() throws Exception { // 관리하는데 보고
		try {
			FileReader fr = new FileReader("a1.txt"); 
		} catch (FileNotFoundException e) {
			e.printStackTrace(); // 알바생 상황 인지함..
//			throw new Exception(e); // Exception(e);에대한 처리코드가 함수내에 없음 -> 위의 throws를 타고 관리자쪽으로 던져짐
			throw e; // new는 만들어진 예외 객체가 없을때 이경우에는 위의 catch에서 만들어짐 
		}
//		FileReader fr = new FileReader("a1.txt");
//		try { // 직접처리 -> 관리하는 쪽에서 예외가 발생한것인지 모름
//			FileReader fr = new FileReader("a1.txt"); 
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}
public class Test09 {
	
	public static void main(String[] args) {
		Boss a = new Boss();
		// 편의점 사장
		// 알바생 일을 시킨다
		// 알바생이 일하는 중 에러발생
		// 사장에게 알려야 하는가?
		// 스스로 해결해야 하는가?
		// 스스로 해결하고 사장에게도 보고해야하는가??
	}
}
