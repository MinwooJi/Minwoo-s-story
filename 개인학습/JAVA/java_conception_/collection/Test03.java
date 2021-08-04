package com.ssafy.java.day01_04collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Test03 {
	public static void main(String[] args) {
		List<String>list = new ArrayList<>();
		list.add("a");
		list.add("b");
		list.add("c");
		System.out.println("1. list.toString()");
		System.out.println(list.toString());
		System.out.println(list);
		
		System.out.println("2. 1.4 반복 활용-----------------------------");
		for(int i=0;i<list.size();i++) {
			System.out.println(list.get(i));
		}
		System.out.println("3. 1.5 반복 활용 : ehanced lopp--------------------");
		for(String val : list) {
			System.out.println(val);
		}
		System.out.println("4. 순환자 활용(Iterator) -----------------");
		Iterator<String> ite = list.iterator();
		while(ite.hasNext()) {
			String v = ite.next();
			System.out.println(v);
			
		}
		System.out.println("5. 1.8부터 가능 : 스트림 이용");
//		Stream<String> s = list.stream();
//		Consumer<String> c = new MyCOnsumer<>();
//		s.forEach(c);
//		list.stream().forEach(new MyCOnsumer<>());
		list.stream().forEach(new Consumer<String>() {
			public void accept(String t) {
				// TODO Auto-generated method stub
				System.out.println("내부클래스 -> " + t);
			}
		});
		System.out.println("6. 1.8부터 가능: 스트림 이용(람다 표현식)"); // 람다표현식은 무조건 인터페이스 안에 함수가 하나만 존재해야한다. 그래야 인터페이스에 어떤함수를 구현하는건지 어떤 매개변수를 쓰는지 유추할수있다.
		list.stream().forEach(t->System.out.println("람다표현식 -> " + t));   // 인터페이스안에 함수를 정의한다고 생각. 화살표 외쪽은 매개변수 오른쪽은 구현내용
		
		System.out.println("6. 1.8 (리스트 자체 이용) "); // stream 에서 forEach만 사용할떄 
		list.forEach(t->System.out.println("람다표현식 -> " + t));   
		//내부클래스 - 익명클래스
		/*
			클래스 또는 인터페이스 -> 부모클래스
			new 클래스 또는 인터페이스() {
				(클래스 바디)
			}
		*/ 
//		A a = new B();
//		// 익명클래스 만들기 new부터 시작
//		A aa = /*여기는 new b()와 같음*/new A() { // 이름없는 클래스가 A를 상속한다는 의미 {} 안의 바디부분은 B를 의미
//		
//		};
//		// new AA(){} 는 BB클래스와 같다
////		한번밖에 사용하지 않기떄문에 익명클래스로 만드는 것이 좋다.
//		AA aaa =new AA() {
//			public void call() {}
//		};
	}
}
interface AA{
	void call();
}
class BB implements AA{
	public void call() {
		
	}
}


class A{}
class B extends A{}
class MyCOnsumer<T> implements Consumer<T>{
	public void accept(T t) {
		// TODO Auto-generated method stub
		System.out.println("호출 -> " + t);
	}
	
}