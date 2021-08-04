/**
 *	직접 처리 방식 이해하기
 *
 *  try			- 예외가 발생할수있는 코드를 묶어준다.
 *  catch		- try 블럭에서 예외가 발생했을때 예외처리 구문을 작성한다(예외를 어떻게 처리할지)
 *  			- 메서드의 파라미터 받는 형식과 동일하게 선언 // 내가 무었을 잡을수 있는지
 *  			- try 블럭에서 예외가 발생했을때만 실행한다.
 *  finally		- 무조건 실행
 *  			- 예외의 발생여부와 상관없이 무조건 실행
 *  			- 외부자원을 사용했을떄 정리하는 작업(IO, NetWork, Database..)
 *  사용형태
 *  try 단독 사용 불가능
 *  try ~ catch
 *  try ~ finally
 *  try ~ catch ~ finally
 */
package com.ssafy.java.day02_05exeption;

import java.io.FileReader;
import java.io.IOException;

public class Test03 {
	public static void main(String[] args) {
		//Unhandled exception type FileNotFoundException
		try {
			System.out.println(1);
			/*
			 * 예외발생하면 내부에서 처리하는 일...
			 * 
			 * 1. 예외 상황에 맞는 예외 클래스를 찾는다.(JVM)
			 * 2. 해당 예외정보를 설정한 예외 클래스 객체를 생성한다.
			 *    new FileNotFoundExcption("..");
			 * 3. JVM은 생성된 예외 객체를 처리할수있는 catch 블럭을 찾는다.
			 * 4. catch 블럭으로 자신이 생성한 에외객체를 넘긴다.
			 
			 * Object e = new FileNotFoundExcption("..");
			 * Throwable e = new FileNotFoundExcption("..");
			 * Exception e = new FileNotFoundExcption("..");
			 * IOException e = new FileNotFoundExcption("..");
			 * FileNotFoundException e = new FileNotFoundExcption("..");
			 */
			FileReader fr = new FileReader("a.txt"); // 예외가 발생할수 있는 코드
			System.out.println(2);
//		} catch(FileNotFoundException e){
//		} catch(IOException e){
//		} catch(Exception e){
		} catch(Throwable e){
//		} catch(Object e){  // No exception of type Object can be thrown; an exception type must be a subclass of Throwable
			
			System.out.println("직접 처리 방식 구현");
		}
		System.out.println("정상 종료");
	}
}
