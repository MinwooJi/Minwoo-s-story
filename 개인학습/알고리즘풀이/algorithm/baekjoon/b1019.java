import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class b1019 { // 책 페이지  (그리디) 규칙찾기
	// 범위생각하고 자료형 선언, 입력값 잘확인
	static long a,b;
	static long[] cnt;// int도 가능할수도
	static long rst;
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			cnt = new long[10];
			rst = 0;
			a = 1;
			b = Long.parseLong(br.readLine());
			sol();
			for (int i = 0; i < cnt.length; i++) {
				System.out.print(cnt[i] + " ");
			}
			System.out.println();
	}
	static void sol() {
		int mult = 1;
		long temp;
		while(a<=b) {
			while(b%10 != 9 && a <= b) {
				calc(b,mult);
				b--;
			}
			if(b < a) {
				break;
			}
			while(a % 10 != 0 && a <= b) {
				calc(a,mult);
				a++;
			}
			a /= 10;
			b /= 10;
			temp = (b-a+1)*mult;
			for (int i = 0; i < cnt.length; i++) {
				cnt[i] += temp;
			}
			mult*=10;
		}
	}
	static void calc(long num,int mult) {
		while(num>0) {
			cnt[(int)(num%10)] += mult;
			num /= 10;
		}
	}
}
