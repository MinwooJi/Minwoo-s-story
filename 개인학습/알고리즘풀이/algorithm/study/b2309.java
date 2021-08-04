import java.util.Arrays;
import java.util.Scanner;

public class b2309 { // 일곱 난쟁이
	static int[]arr = new int[9];
	static int[]set = new int[7];
	static boolean flag = false;
	static boolean[] v;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		v = new boolean[9];
		for(int i=0;i<9;i++) {
			arr[i] = sc.nextInt();
		}
		comb(0,0,0);
	}
	static void comb(int idx,int cnt,int sum) {
		if(flag) return;
		if(cnt == 7) {
			if(sum == 100) {
				flag = true;
				Arrays.sort(set);
				for(int i=0;i<set.length;i++) {
					System.out.println(set[i]);
				}
			}
			return;
		}
		for(int i=idx;i<9;i++) {
			if(v[i]) continue;
			v[i] = true;
			set[cnt] = arr[i];
			comb(i,cnt+1,sum+arr[i]);
			v[i] = false;
		}
	}
}
