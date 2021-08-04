/**
 * 예외의 간접 처리 방식
 * throws
 * 
 * - 메서드의 선언부에 사용
 *  :[접근제한자][황용제한자] 반환타입 메서드 명([파라미터]) [throws 예외 클래스타입, ...]{}
 * - 선언된 메서드 내에 특정 예외가 발생할 수 있음을 호출하는 쪽으로 알려준다. 호출한 쪽에서는 예외처리를 진행한다.
 */
package com.ssafy.java.day02_05exeption;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Test07 {
	private static void call() throws FileNotFoundException {
//	private static void call() throws IOException {
//	private static void call() throws Exception, NullPointerException {
		FileReader fr = new FileReader("al.txt");
	}
	private static void call2() /*throws NullPointerException*/ { 
		// 런타임 예외 같은경우에는 throws를 안해줘도 throws가 날라간다 안적어도 되지만 호출하는 쪽에 예외발생 정보를 주기떄문에구현시 하는게 좋음)
		String s = null;
		// NullPointerExeption
		System.out.println(s.length());
	}
	public static void main(String[] args) {
		try {
			call();
			call2();   // 런타임 예외를 던질 경우에는 catch블럭을 선택적으로 구현할 수 있다. (강제x)
			// 런타임 예외는  catch가 없어도 컴파일 에러가 안남 (선택적 예외 처리)
			// 예외 처리 루틴이 없을시 중간에 튕긴다. 선택이지만 대부분 처리를 해준다.
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}
}
