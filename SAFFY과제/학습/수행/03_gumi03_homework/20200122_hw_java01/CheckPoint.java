package com.java.first;

import java.util.Scanner;

public class CheckPoint {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int height=sc.nextInt();
		int weight=sc.nextInt();
		int check = weight+100-height;
		System.out.println("비만수치는 "+check+"입니다.");
		if(check >0)
			System.out.println("당신은 비만이군요");
		
		

	}

}
