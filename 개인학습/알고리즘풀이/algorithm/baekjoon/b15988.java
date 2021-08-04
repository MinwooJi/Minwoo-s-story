import java.util.Scanner;

public class b15988 { // 1,2,3 더하기 3
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int tc = sc.nextInt();
		long[] dp = new long[1000001];
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;
		for (int i = 4; i <= 1000000; i++) {
			dp[i] = (dp[i-1] + dp[i-2] + dp[i-3]) % 1000000009;
		}
		for (int t = 1; t <= tc; t++) {
			sb.append(dp[sc.nextInt()]).append("\n");
		}
		System.out.print(sb);
	}
}
