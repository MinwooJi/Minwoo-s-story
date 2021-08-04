/*
 * 다중 catch 블럭의 사용시의 주의점
 * - 부모 타입의 예외가 자식 타입의 예외보다 먼저 선언되면 안된다.
 * 
 * - IOException
 *  	|
 *   FileNotFoundException
 *   
 *  } catch (IOException e){            // 잘못된 케이스
			System.out.println(2);

//			Unreachable catch block for FileNotFoundException. 
//			It is already handled by the catch block for IOException
//			이미 부모가 FileNotFoundExcption을 잡아서 아래catch로 넘어갈 일이 없다.

	} catch (FileNotFoundException e) {
			System.out.println(1);
 * 
 */
package com.ssafy.java.day02_05exeption;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Test04 {
	public static void main(String[] args) {
		try {
			FileReader fr = new FileReader("a1.txt");
			int ch = fr.read(); 
			System.out.println(ch/0);
			//			try {
			//				int ch = fr.read(); 
			//			} catch(IOException e ) {
			//				System.out.println(2);
			//			}
			//Unhandled exception type IOException
		} catch (FileNotFoundException e) {
			System.out.println(1);
		} catch (IOException e){
			System.out.println(2);

		} catch(Exception e) { // try블럭에 발생할수 있는 모든 예외를 처리하자.. // 런타임에러까지 방지
			System.out.println(3);
		}
	}
}
