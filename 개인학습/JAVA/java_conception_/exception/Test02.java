/**
 * 	런타임에 발생하는 예외 확인하고 특징 이해하기
 */
package com.ssafy.java.day02_05exeption;

import java.util.Random;

public class Test02 {
	public static void main(String[] args) {
		Object o = new Random();
//		Exception in thread "main" java.lang.ClassCastException
//		런타임 예외의 대부분은 개발자 실수로 발생한다.
		/*
		 * Throwable
		 * Excption
		 * RuntimeException -> 예외처리를 강제가 아니라 선택으로 할수있다.
		 * ClassCastException ArithmeticException NullPointerException
		 */
		String s = (String)o;
		
		// ArithmeticException
		System.out.println(1/0);
		
		String s2 = null;
		// NullPointerException
		System.out.println(s2.length());
	}

}
