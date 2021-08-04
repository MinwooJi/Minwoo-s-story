/**
 * Set
 * ------------------------
 * - 데이터 중복 허용하지 않는다.
 * - 내부의 데이터를 확인한하기 위해서 equals(Object o) + hashCode() 호출결과 같은면 동일데이터로 판단
 * - 순서가 없으며 인덱스 관리를 하지않는다.
 * remove(int i) -> X
 * get(int i) -> X
 *   --> 인덱스로 접근하는것은 불가능 why? set은 인덱스 관리를 하지 않기떄문에
 *  * revome(Object o)
 * 
 */
package com.ssafy.java.day01_04collection;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class Test04 {
	public static void main(String[] args) {
		Set<String> set = new HashSet<>();
		set = new TreeSet<>(); // TreeSet을 쓸경우 정렬된 결과를 출력하게 된다/
		System.out.println(set.add("one")); // true
		set.add("two");
		System.out.println(set.add(new String("two"))); // false  중복데이터를 허용하지 않음 주소가 달라도 값으로 비
		set.add("three");
		System.out.println(set.size());
		//Set index로 처리되는 메서드는 지원하지 않는다.
//		set.get(1);
//		for(int i=0;i<set.size();i++) {
//			System.out.println(set.get(i));
//		}
		for(String v: set) { // 향상된for문은 index를 사용하지 않기떄문에 set에서도 사용가능
			System.out.println(v);
		}
		//람다식 - accept 메서드 구현
		set.forEach(v->System.out.println(v));
		//6개 1~45 사이의 랜덤한 중복되지 않는 숫자 출력
		Set<Integer> numbers = new HashSet<>();
//		numbers = new TreeSet<>();
		Random r = new Random();
		while(numbers.size()<6) { // 중복되지 않는 숫자 6개가 되기 전까지
			numbers.add(r.nextInt(45) + 1);
		}
		System.out.println(numbers);
	}
}
