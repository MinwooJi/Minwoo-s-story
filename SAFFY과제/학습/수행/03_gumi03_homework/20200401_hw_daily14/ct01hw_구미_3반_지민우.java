import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class ct01hw_구미_3반_지민우 {
	static int N,R;
	static long dp[];
	static final int mod = 1234567891;
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		dp = new long[1000001];
		Arrays.fill(dp, -1);
		dp[0] = 1;
		dp[1] = 1;
		for (int i = 0; i < dp.length; i++) {
			fact(i);
		}
		StringBuilder sb = new StringBuilder();
		int tc = Integer.parseInt(br.readLine());
		for (int t = 1; t <= tc; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			long bottom = (dp[R]*dp[N-R]) % mod;
			bottom = sol(bottom,mod-2);
			sb.append("#").append(t).append(" ").append((dp[N]*bottom) % mod).append("\n");
		}
		System.out.print(sb);
	}
	static long sol(long n,int r) {
		if(r == 0 ) return 1;
		long temp = sol(n,r/2);
		long rst = (temp * temp) % mod;
		if(r % 2 == 0) 
			return rst;
		else
			return (rst*n) % mod;
	}	
	static long fact(int num) {
		if(dp[num] != -1) {
			return dp[num];
		}
		if(num == 1) return 1;
		if(num == 0) return 1; 
		return dp[num] = num*fact(num-1)%1234567891;
	}
	
}
