/**
 * Map
 * 
 * 키.값의 쌍으로 관리
 * 키값의 중복을 허용하지 않는다.
 * 조회와 변경 모두다 빠른 구조(Arraylist보다는 조회가 빠르지 않고, linkedlist보다 변경이 빠르지 않지만 중간은간다)
 * 
 */
package com.ssafy.java.day01_04collection;

import java.util.HashMap;
import java.util.Map;

public class Test05 {
	public static void main(String[] args) {
		Map<String,String> map = new HashMap<>();
		map.put("id","hong");
		map.put("name","홍");
//		String prevId = map.get("id");
//		map.put("id", "Kim"); // 덮어씀
		String prevId = map.put("id", "Kim"); // 바뀌기전의 값을 prevId의 값으로 반환해줌
		System.out.println(prevId);
		System.out.println(map.put("email","Kim@a.com")); // email에 담긴 값이 없었기에 null 이 반환될 것
		System.out.println(map);
		
		System.out.println(map.containsKey("id")); // map안에 id라는 키가 포함?
		System.out.println(map.containsValue("kkk")); // map안에 kkk라는value가 존재?
		//원하는 데이터 추출
		//idx값은 없고 key를 이용하여 추출
//		String id = map.get("id");
//		System.out.println("id -> "+id);
//		
//		String email = map.get("email"); // null 반환
//		System.out.println("email -> " + email);
	}
}
