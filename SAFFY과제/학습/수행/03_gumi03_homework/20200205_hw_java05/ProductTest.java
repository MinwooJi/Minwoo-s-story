package com.ssafy.java.day04.clazz04.self;

public class ProductTest {
	public static void main(String[] args) {
		TV tv1 = new TV(202872,"SAMSUNG G40",928000,14,40,"OLED");
		TV tv2 = new TV(220768,"LG GBPRO",888000,2,36,"LED");
		Refrigerator rg = new Refrigerator(108004,"DURIAN 4",798000,3,70);
		System.out.println(tv1.toString());
		System.out.println(tv2.toString());
		System.out.println(rg.toString());
	}
}
