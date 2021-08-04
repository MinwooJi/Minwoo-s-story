import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b16194 { // 카드구매하기2 (dp 응용점화식)
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int n = Integer.parseInt(br.readLine());
		int val[] = new int[n+1];
		int dp[] = new int[n+1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < val.length; i++) {
			val[i] = Integer.parseInt(st.nextToken());
		}
		dp[1] = val[1];
		int min;
		for (int i = 2; i < dp.length; i++) {
			min = val[i];
			for (int j = i-1; j > 0; j--) {
				min = Math.min(dp[j]+val[i-j], min);
			}
			dp[i] = min;
		}
		System.out.println(dp[n]);
	}
}
