package com.saffy.java;

import java.util.Scanner;

public class DigitTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] num = new int[100];
		int[] count = new int[10];
		Scanner sc = new Scanner(System.in);
		int n = 0;
		for(int i=0;i<num.length;i++)
		{
			n = sc.nextInt();
			if(n==0)
			{
				count[0]++;
				break;
			}
			if(n/10 >0)
			{
				count[n/10]++;
				n = n%10;
			}
			if(n/10==0)
				count[n]++;
		}
		for(int i=0;i<count.length;i++)
			if(count[i]!=0)
				System.out.println(String.format("%d : %d개", i,count[i]));
	}

}
