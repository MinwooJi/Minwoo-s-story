/**
 * 		예외 : Exeption, (5가지 키워드, 사용자 정의 예외)
 * 		- 예외가 발생했을 떄 처리하는 방식
 * 		  : 직접처리 방식, 간접처리 방식
 * 		  : 직접처리 방식(예외가 발생한 위치에서 처리코드 작성), (try, catch, finally)
 * 		  : 간접처리 방식(자신을 호출한 쪽으로 예외를 던져서 호출한 곳에서 예외처리를 한다.), throws
 * 		- 자바에서 발생하는 모든 예외들은 이미 클래스로 정의되어 있음
 * 		- API의 예외 클래스 구조 이해하기
 *  	클래스 구조
 *  					Object
 *   			 	      |
 *   		  		  Throwable (모든 예외들의 root)
 *   			   		  |
 *      	 	Exception     Error(프로그램에서 처리 불가능한 심각한 것)
 *              |       |
 * RuntimeException  기타 예외...
 *       |(실행할때 발생)
 * - 예외 발생 시점에 따른 분류
 *	 : 컴파일 시점에 발생하는 예외, RuntimeException 타입을 상속받지 않은 클래스, checked exception
 *	 : 런타임 시점에 발생하는 예외, RuntimeException 타입의 클래스들(자식들 포함), unchecked exception
 *
 *-사용자가 직접 예외 클래스를 만들수있다. (모든 예외들은 자바에 정의되어있지만 사용자가 만든 프로젝트에 따라 예외가 더필요하다(로또에서 45이상숫자 - 예외, 주민번호 입력시 13자리 이상 - 예외 등)
 *	: 기존 예외 클래스를 상속받는다.
 *	: 런타임 시점에 발생하는 예외클래스를 정의하고 싶다면 RuntimeException 타입의 클래스를 상속한다.
 *	: 컴파일 시점에 발생하는 예외클래스를 정의하고 싶다면 RuntimeException 타입 이외의 클래스를 상속한다.
 *	: 사용자 정의 예외와 함꼐 사용되는 키워드 - throw(예외 강제 발생시킨다)
 *
 *	예외 핵심 키워드 : (직접)try,catch,finally, (간접)throws, (사용자)finally
 *
 */
package com.ssafy.java.day02_05exeption;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Test01 {
	public static void main(String[] args){
		// Unhandled exception type FileNotFoundException
		try {
			//FileNotFoundException - 컴파일 시점의 예외 -> 개발자가 무조건 예외처리 코드를 짜야함
			/*
			 * 	Throwable
			 * 	Exception
			 * 	IOException
			 * 	FileNotFoundException
			 */
			FileReader fr = new FileReader("a.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
