/**
 * 맵의 전체 데이터 조회하기
 * 
 */
package com.ssafy.java.day01_04collection;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Test06 {
	public static void main(String[] args) {
		Map<String,Integer>map = new HashMap<>();
		map.put("a", 10);
		map.put("b", 20);
		map.put("c", 30);
		map.put("d", 40);
		
		System.out.println("1. 맵의 키-------------");
		Set<String>keys = map.keySet(); // set의 형태로 key값을 받음 둘다 중복은 없는 상태
		System.out.println(keys);
		
		System.out.println("2. 맵의 값-------------");
		Collection<Integer>values = map.values(); // Collection의 형태로 value값을 받음
		System.out.println(values);
		
		System.out.println("3. 맵의 키,값-------------");
		keys = map.keySet();
		for(String key : keys){
			System.out.println(key + "---" + map.get(key));
		}
		System.out.println("4. 맵의 키,값-------------");
		Set<Entry<String,Integer> > entrySet = map.entrySet();
		for(Entry<String,Integer> entry : entrySet) {
			System.out.println(entry.getKey()+"---" + entry.getValue());
		}
		System.out.println("5. 맵의 키,값-------------");
		map.forEach((k,v)->System.out.println("람다표현식 -> " + k + "---" + v)); 
	}
}
