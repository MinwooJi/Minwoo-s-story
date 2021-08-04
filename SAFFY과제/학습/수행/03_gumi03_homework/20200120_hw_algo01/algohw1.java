package com.ssafy.java;
import java.util.Scanner;

 
/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class algohw1
{
     public static void main(String[] args) {
      // TODO Auto-generated method stub
      int N = 1000;
      int R_N = 101; // 0~100점 
       
      int[] n_arr = new int[N]; // 1000명
      Scanner sc = new Scanner(System.in);
      int tc = sc.nextInt();
      for(int k=1;k<=tc;k++)
      {
         int n=sc.nextInt();
         for(int i=0;i<N;i++)
            n_arr[i] = sc.nextInt();
         int[] rst_arr = new int[R_N];
         int num = 0;
         for(int i=0;i<R_N;i++ )
         {
            for(int j=0;j<N;j++)
            {
               if(i==n_arr[j])
                  num ++;
            }
            rst_arr[i] = num;
            num = 0;
         }
         int maxindex = 0;
         for(int i=0;i<R_N;i++)
         {
            if(rst_arr[i]>=rst_arr[maxindex])
               maxindex = i;
 
         }
         System.out.println("#" + n + " "+ maxindex);
      }
   }
}