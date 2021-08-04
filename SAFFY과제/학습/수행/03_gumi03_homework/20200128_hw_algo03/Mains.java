import java.util.Scanner;
public class Mains {
   static int[] arr;
   static int cnt=0;
   static int M = -1;
   public static void main(String[] args) {
      // TODO Auto-generated method stub
      Scanner sc = new Scanner(System.in);
      int N = sc.nextInt();
      M = sc.nextInt();
      arr = new int[N];
      sol(N,0);
   }
   public static void sol(int N,int idx){
      if(N==0) {
         if(cnt==M) 
            print();
         return;
      }
      for(int i=1;i<=6;i++) {
         arr[idx] = i;
         cnt += arr[idx];
         sol(N-1,idx+1);
         cnt -= arr[idx];
      }
   }
   public static void print() {
      for(int i=0;i<arr.length;i++) {
         System.out.print(arr[i]+" ");
      }
      System.out.println();
   }
}