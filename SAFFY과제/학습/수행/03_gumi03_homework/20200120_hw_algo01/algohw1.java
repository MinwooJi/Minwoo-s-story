package com.ssafy.java;
import java.util.Scanner;

 
/*
   ����ϴ� Ŭ�������� Solution �̾�� �ϹǷ�, ������ Solution.java �� ����� ���� �����մϴ�.
   �̷��� ��Ȳ������ �����ϰ� java Solution ������� ���α׷��� �����غ� �� �ֽ��ϴ�.
 */
class algohw1
{
     public static void main(String[] args) {
      // TODO Auto-generated method stub
      int N = 1000;
      int R_N = 101; // 0~100�� 
       
      int[] n_arr = new int[N]; // 1000��
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