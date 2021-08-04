import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class swe5604 { // 구간 합
	static long a,b;
	static long[] cnt;// int도 가능할수도
	static long rst;
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int tc = Integer.parseInt(br.readLine());
		for(int t=1;t<=tc;t++) {
			cnt = new long[10];
			rst = 0;
			st = new StringTokenizer(br.readLine());
			a = Long.parseLong(st.nextToken());
			b = Long.parseLong(st.nextToken());
			sol();
			for (int i = 0; i < cnt.length; i++) {
				rst+= (i*cnt[i]);
			}
			sb.append("#").append(t).append(" ").append(rst).append("\n");
		}
		System.out.print(sb);
	}
	static void sol() {
        long mult = 1;
        long temp;
        Outer:
        while(a<=b) {
            while(a%10 != 0 || b%10 != 9) {
                if(a%10 != 0) {
                    calc(a,mult);
                    a++;
                }
                if(a>b) break Outer; // 같을경우로 시작할때.
                if(a!=b && b%10 != 9) {
                    calc(b,mult);
                    b--;
                }
                if(a==b) {
                    calc(a,mult);
                    break Outer;
                }
            }// a는 0 b는 9 맞추기
            a = a/10;
            b = b/10;
            temp = mult*(b-a+1);
            for (int i = 0; i < cnt.length; i++) {
                cnt[i] += temp;
            }
            mult*=10;
        }
    }
	static void calc(long num,long mult) {
		while(num>0) {
			cnt[(int)(num%10)] += mult;
			num /= 10;
		}
	}
}
