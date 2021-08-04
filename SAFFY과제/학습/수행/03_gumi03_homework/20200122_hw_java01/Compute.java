package com.java.first;

import java.util.Scanner;

public class Compute {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a,b;
		int rst1,rst2;
		a = sc.nextInt();
		b = sc.nextInt();
		rst1 = a*b;
		rst2 = a/b;
		System.out.println("°ö="+rst1);
		System.out.println("¸ò="+rst2);
	}
}
