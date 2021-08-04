import java.util.Scanner;

public class swea7206 { // swea_7206 숫자게임 (dfs+memo+bitmask(powerset))
	static int[]dp = new int[100000];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int tc = sc.nextInt();
		int num;
		for (int t = 1; t <= tc; t++) {
			num = sc.nextInt();
			sb.append("#").append(t).append(" ").append(sol(num)).append("\n");
		}
		System.out.print(sb);
				
	}
	static int sol(int num) {
		if(num < 10) return 0;
		if(dp[num] != 0) return dp[num];
		String temp = num+"";
		int length = temp.length()-1;
		int maxCnt = 0;
		for (int i = 1; i < 1<<length; i++) {
			int cur = temp.charAt(0)-'0';
			int rst = 1;
			for (int j = 0; j < length; j++) {
				if((i & (1<<j) ) == 0) {
					cur = cur*10 + temp.charAt(j+1)-'0';
				}else {
					rst*=cur;
					cur = temp.charAt(j+1)-'0';
				}
			}
			rst *= cur;
			maxCnt = Math.max(sol(rst),maxCnt);
		}
		return dp[num] = maxCnt+1;
	}
}
