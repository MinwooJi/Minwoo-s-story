import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;
class b2493{
   static class Tower{
      int heigt;
      int idx;
      Tower(int heigt,int idx){
         this.heigt = heigt;
         this.idx = idx;
      }
   }
   static class scan {
       private static BufferedReader br;
       private static StringTokenizer st;
       static void init() {
           br = new BufferedReader(new InputStreamReader(System.in));
           st = new StringTokenizer("");
       }
       static String next() {
           while (!st.hasMoreTokens()) {
               try {
                   st = new StringTokenizer(br.readLine());
               } catch (IOException e) {
               }
           }
           return st.nextToken();
       }

       static int nextInt() {
           return Integer.parseInt(next());
       }
   }
   public static void main(String[] args) {
	  scan.init();
      int N = scan.nextInt();
      int[] arr = new int[N];
      int[] rst = new int[N];
      Stack<Tower> stk = new Stack<Tower>();
      for(int i=0;i<N;i++) 
         arr[i] = scan.nextInt();
      // 입력끝
      stk.push(new Tower(arr[N-1],N-1));
      for(int i = N-1;i>0;i--) {
         if(arr[i] < arr[i-1]) { // 앞에서 레이저를 수신한다
            while(!stk.isEmpty() && stk.peek().heigt < arr[i-1]) {
               rst[stk.pop().idx] = i;
            }
            stk.push(new Tower(arr[i-1],i-1));
            
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
