/**
 *  예외 객체를 활용한 예외처리
 */
package com.ssafy.java.day02_05exeption;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Test06 {
	public static void main(String[] args) {
		try {
			FileReader fr = new FileReader("a1.txt");
		} catch (Exception e) {
			// 예외처리 구문 작성
			// 간단한 예외 메세지 출력하기
//			String errMsg = e.getMessage();
//			System.out.println("에러 -> " + errMsg);
			
			// 상세 예외 정보 확인하기
//			e.printStackTrace();
			
			// 사용자가 예외 정보에 대한 조작을 직접하자...
			StackTraceElement[] stacks= e.getStackTrace();
			for(StackTraceElement ele : stacks) {
				System.out.println(
						ele.getFileName() + "\t" +
						ele.getClassName() + "\t" +
						ele.getMethodName() + "\t" +
						ele.getLineNumber() 
				);
			}
		}
	}
}
