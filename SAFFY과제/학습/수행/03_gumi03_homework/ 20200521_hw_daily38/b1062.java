import java.util.Scanner;

public class b1062 { // 가르침 (비트마스킹 + 조합 + 문자열 처리)
	static int N,K;
	static String[] sArr;
	static int rst,max;
	static boolean flag;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		if(K < 5) {
			System.out.println(0);
			return;
		}
		sArr = new String[N];
		int v = 0;
		String s = "antic";
		for (int i = 0; i < s.length(); i++) {
			v |= 1<<s.charAt(i)-'a';
		}
		for (int i = 0; i < sArr.length; i++) {
			sArr[i] = sc.next();
		}// 입력끝
		comb(0,0,v);
		System.out.println(rst);
	}
	static void comb(int idx,int cnt,int v) {
		if(rst == N) return;
		if(cnt == K-5) {
			max = 0;
			for (int i = 0; i < sArr.length; i++) {
				flag = true;
				for (int j = 4; j < sArr[i].length()-4; j++) {
					if( (v & (1<<sArr[i].charAt(j)-'a') )!=0 ) continue;
					flag = false;
					break;
				}
				if(flag) max++;
			}
			rst = Math.max(rst, max);
			return;
		}
		for (int i = idx; i < 26; i++) {
			if(( v & (1<<i) ) != 0) continue;
			comb(i+1,cnt+1,v|(1<<i));
		}
	}
}
