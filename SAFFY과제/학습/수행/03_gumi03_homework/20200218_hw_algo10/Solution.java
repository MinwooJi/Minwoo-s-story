import java.util.Scanner;
  
public class Solution {
    static long mod = 1000000007;
    static long[] memo = new long[1000001];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        for(long i = 0;i<memo.length;i++) {
            dfs((int)i,(int)i);
        }
        for(int t=0;t<tc;t++) {
            int n = sc.nextInt();
            long sum =0;
            for(int i=1;i<=n;i++) {
                sum += (dfs(i,i)) ;
            }
            System.out.printf("#%d ",t+1);
            System.out.println(sum%mod);
        }
    }
    private static long dfs(int base, int exp) {
        if(exp == 0) {
            return 1;
        }
        if(exp == 1) {
            return base;
        }
        if(memo[base] != 0) {
            return memo[base];
        }
        else {
            long su = (dfs(base,exp/2)) %mod;
            su  = (su * su) %1000000007;
            if(exp %2 == 1) {
                su = (su%mod * base)%mod;
            }
            return memo[base] = su % 1000000007;
        }
    }
}