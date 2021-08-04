package com.ssafy.java.day02_05exeption;

//public class JuminNumberException extends Exception{ // 컴파일 시점 체크 예외
public class JuminNumberException extends RuntimeException{ // 런타임 시점 체크 예외
	public JuminNumberException(String msg) {
		super("주민번호는 13자리 이어야합니다.(" + msg + ")");
	}
}
