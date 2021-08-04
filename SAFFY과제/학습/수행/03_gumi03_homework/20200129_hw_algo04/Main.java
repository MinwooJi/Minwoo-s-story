import java.util.Scanner;
import java.util.Stack;
class Main{
   static class Tower{
      int heigt;
      int idx;
      Tower(int heigt,int idx){
         this.heigt = heigt;
         this.idx = idx;
      }
   }
   public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      int N = sc.nextInt();
      int[] arr = new int[N];
      int[] rst = new int[N];
      Stack<Tower> stk = new Stack<Tower>();
      for(int i=0;i<N;i++) 
         arr[i] = sc.nextInt();
      // 입력끝
      stk.push(new Tower(arr[N-1],N-1));
      for(int i = N-1;i>0;i--) {
         if(arr[i] < arr[i-1]) { // 앞에서 레이저를 수신한다
            while(!stk.isEmpty() && stk.peek().heigt < arr[i-1]) {
               rst[stk.pop().idx] = i;
            }
            stk.push(new Tower(arr[i-1],i-1)); //앞에꺼 넣음
            
         }
         else {
            stk.push(new Tower(arr[i-1],i-1));
         }
      }
      for(int i=0;i<rst.length;i++) {
         System.out.print(rst[i]+" ");
      }
   }
} 