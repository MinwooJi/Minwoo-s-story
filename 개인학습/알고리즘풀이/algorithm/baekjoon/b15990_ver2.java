import java.util.Scanner;

public class b15990_ver2 { // 1,2,3 더하기 5 dp_나열
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		final int mod = 1000000009;
		int[][] dp = new int[100001][3+1];
		dp[1][1] = 1;
		dp[2][2] = 1;
		dp[3][1] = 1; // 2+1 / 1+1+1은 뻄
		dp[3][2] = 1; // 1+2
		dp[3][3] = 1;
		for (int i = 4; i < dp.length; i++) {
			dp[i][1] = (dp[i - 1][2] + dp[i - 1][3]) % mod;
			dp[i][2] = (dp[i - 2][1] + dp[i - 2][3]) % mod;
			dp[i][3] = (dp[i - 3][2] + dp[i - 3][1]) % mod;
		}
		int tc = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		int n;
		long rst;
		for (int t = 0; t < tc; t++) {
			n = sc.nextInt();
			rst = 0;
			for (int i = 1; i <= 3; i++) {
				rst = (rst + dp[n][i]) % mod;
			}
			sb.append(rst).append("\n");
		}
		System.out.print(sb);
	}
}
