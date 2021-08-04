package com.ssafy.java.day04.clazz04.self;

public class TV {
	private int productNum;
	private String productName;
	private int price;
	private int stockCnt;
	private int inch;
	private String displayType;
	public TV(int productNum, String productName, int price, int stockCnt, int inch, String displayType) {
		super();
		this.productNum = productNum;
		this.productName = productName;
		this.price = price;
		this.stockCnt = stockCnt;
		this.inch = inch;
		this.displayType = displayType;
	}
	public TV() {}
	public String toString() {
		return String.format("productNum:%-10d| productName:%-13s| price: %-10d| stockCnt: %-4d| inch: %-3d| displayType: %-5s", productNum,productName, price,stockCnt,inch,displayType);
	}
	
}
