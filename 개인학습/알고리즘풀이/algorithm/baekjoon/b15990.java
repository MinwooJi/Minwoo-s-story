import java.util.Scanner;

public class b15990 { // 1,2,3 더하기 5 - for
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
			for (int j = 1; j <= 3; j++) {
				for (int k = 1; k <= 3; k++) {
					if( j == k ) continue;
					dp[i][j] = (dp[i][j] + dp[i-j][k]) % mod;
				}
			}
		}
		int tc = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		for (int t = 0; t < tc; t++) {
			int n = sc.nextInt();
			int rst = 0;
			for (int i = 1; i <= 3; i++) {
				rst = (rst + dp[n][i]) % mod;
			}
			sb.append(rst).append("\n");
		}
		System.out.print(sb);
	}
}
