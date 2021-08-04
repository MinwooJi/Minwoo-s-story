import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ct02hw_구미_3반_지민우 {
	static class Info{
		int t;
		int a;
		int b;
		public Info(int t, int a, int b) {
			this.t = t;
			this.a = a;
			this.b = b;
		}
		public String toString() {
			return "Info [t=" + t + ", a=" + a + ", b=" + b + "]";
		}
	}
	static long dp[];
	static final long mod = 998244353;
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int tc = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= tc; t++) {
			int N = Integer.parseInt(br.readLine());
			Info[] iArr = new Info[N+1]; // 0버림
			sb.append(String.format("#%d ", t));
			int tn,a,b;
			for (int i = 2; i < iArr.length; i++) {
				st = new StringTokenizer(br.readLine());
				tn =  Integer.parseInt(st.nextToken());
				a =  Integer.parseInt(st.nextToken());
				b =  Integer.parseInt(st.nextToken());
				iArr[i] = new Info(tn,a,b);
			}
			int M = Integer.parseInt(br.readLine());
			dp = new long[N+1];
			dp[0] = 1;
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				long x = Integer.parseInt(st.nextToken());
				dp[1] = x;
				for (int j = 2; j < dp.length; j++) {
					dp[j] = sol(iArr[j]);
				}
				sb.append(dp[N]).append(" ");
			}
			sb.append("\n");
		}// 입력끝
		System.out.print(sb);
	}
	static long sol(Info info) {
		if(info.t == 1) {
			return (dp[info.a] + dp[info.b])% mod;
		}else if(info.t == 2) {
			return (info.a*dp[info.b])%mod;
		}else {
			return (dp[info.a]*dp[info.b]) % mod;
		}
	}
}
