package com.ssafy.java.day04.clazz04.self;

public class Refrigerator {
	private int productNum;
	private String productName;
	private int price;
	private int stockCnt;
	private int size;
	public Refrigerator() {}
	public Refrigerator(int productNum, String productName, int price, int stockCnt, int size) {
		super();
		this.productNum = productNum;
		this.productName = productName;
		this.price = price;
		this.stockCnt = stockCnt;
		this.size = size;
	}
	public String toString() {
		return String.format("productNum:%-10d| productName:%-13s| price: %-10d| stockCnt: %-4d| size: %-3dL", productNum,productName, price,stockCnt,size);
	}
	
	
}
