import java.util.Scanner;

public class b11401 { // 이항계수3 
// 	 페르마의 소정리 (조합 이항계수 -> 페르마의소정리 사용)
//	            (페르마의 소정리를 이용한 지수계산 -> 지수를 0이될때까지 2로 나누어서 제곱하는 방법)
	static int N, K;
	static long dp[];
	static final int mod = 1000000007;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		dp = new long[4000001]; // factorial
		dp[0] = 1;
		dp[1] = 1;
		for (int i = 2; i < dp.length; i++) {
			dp[i] = (dp[i - 1] * i) % mod;
		}
		// nCk == N! / (N-k)!*k!
		long base = (dp[N - K] * dp[K]) % mod;
		long rst = (dp[N] * myPow(base, mod - 2)) % mod;
		System.out.println(rst);
	}
	static long myPow(long base, long exp) {
		if (exp == 0)
			return 1;
		long temp = myPow(base, exp / 2) % mod;
		if (exp % 2 == 0)
			return (temp * temp) % mod;
		else
			return (((temp * temp) % mod) * (base % mod)) % mod;
	}

}