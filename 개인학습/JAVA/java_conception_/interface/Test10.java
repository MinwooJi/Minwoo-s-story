package com.ssafy.java.day01_03interface;
interface AI{
	void work(); // public abstact
	void tell();
//	1.8 버전부터 제공하는 default 메서드를 활요하자 " 확장을 하기 위해서..
//	void print(); // SsafyAi에서 사용하기위해 추가했더니 Kakao에서 오버라이딩이 없어 컴파일에러 
	/*abstract*/ // only one of abstract, default, or static permitted
	default void print() { // default로 선언했기떄문에 kakao에서 재정의 할필요 없고 Ssafy에서는 Ssafy내 재정의한 함수가 실행됨
		System.out.println("해당 AI 제품의 print 기능은 추후 업데이트 될 예정입니다.");
	}
	static void alarm() { // 오버라이딩 안되고 전부 동일하게 사용함
		System.out.println("10분뒤 점심 먹자..");
	}
	// 인터페이스.alarm() 만 사용가능
}
class KakaoAi implements AI{
//	void work() { // 접근제한자가 default로 줄어들어서 컴파일 에러
//	}
//	void tell() {
//	}
	public void work() {
		System.out.println("KaKao 일함");
	}
	public void tell() {
		System.out.println("Kakao 말하고 있음");
	}
}
class SsafyAi implements AI{
	public void print() {
		System.out.println("Ssafy 표현함");
	}
	public void work() {
		System.out.println("Ssafy 일함");
	}
	public void tell() {
		System.out.println("Ssafy 말하고 있음");
	}
}
public class Test10 {
	public static void main(String[] args) {
		AI.alarm();
		AI ai = new SsafyAi();
		ai.print();
		ai.tell();
		ai.work();
		ai = new KakaoAi();
		ai.print();
		ai.tell();
		ai.work();
	}
}
