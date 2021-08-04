import java.util.Scanner;

public class swe9282 { // 초콜릿과 건포도
   static int[][] mat;
   static boolean[][] v;
   static int rstTemp;
   static int N,M;
   static boolean flag = true;
   static int [][][][] dp;
   public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      int tc = sc.nextInt();
      for(int t=0;t<tc;t++) {
         rstTemp = 0;
         N = sc.nextInt();
         M = sc.nextInt();
         dp = new int[N+1][N+1][M+1][M+1];
         mat = new int[N][M];
         v = new boolean[N][M];
         for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
               mat[i][j] = sc.nextInt();
            }
         }// 입력끝
         System.out.printf("#%d ",t+1);
         System.out.println(dfs(0,N,0,M));
      }
   }
   static int dfs(int iMin,int iMax,int jMin,int jMax) {
      if(iMax-iMin <= 1 && jMax-jMin <=1) {
         return 0;
      }
      if(dp[iMin][iMax][jMin][jMax] != 0) 
    	  return dp[iMin][iMax][jMin][jMax];
      int sum = 0;
      int rst = Integer.MAX_VALUE;
      for(int i=iMin;i<iMax;i++) {
         for(int j=jMin;j<jMax;j++) {
            sum += mat[i][j];
         }
      }
      for(int i=iMin+1;i<iMax;i++) {
         rst = Math.min(rst, sum + dfs(iMin,i,jMin,jMax) + dfs(i,iMax,jMin,jMax));
      }
      for(int i=jMin+1;i<jMax;i++) {
         rst = Math.min(rst, sum + dfs(iMin,iMax,jMin,i) + dfs(iMin,iMax,i,jMax));
      }
      dp[iMin][iMax][jMin][jMax] = rst;
      return rst;
      
   }
}