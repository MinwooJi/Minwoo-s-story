package com.ssafy.java.day01_04collection;

import java.util.ArrayList;
import java.util.List;

class Data02{
	int val;
	Data02(int val){
		this.val = val;
	}
	public boolean equals(Object o) {
		if(this == o) return true;
		if( o instanceof Data02) {
			Data02 other = (Data02) o;
			if(this.val == other.val)return true;
		}
		return false;
	}
}
public class Test02 {
	public static void main(String[] args) {
		List<Data02> list = new ArrayList<>();
		list.add(new Data02(1));
		Data02 d = new Data02(2);
		list.add(d);
		list.add(new Data02(3));
		System.out.println(list.indexOf(new Data02(2))); // Data02에 equals 오버라이딩이 안되있다면 Objec의 equals가 호출되고
														 // 이는 양쪽의 주소값을 비교하기떄문에 -1이 반환됨
		System.out.println(list.indexOf(d));
	}

}
