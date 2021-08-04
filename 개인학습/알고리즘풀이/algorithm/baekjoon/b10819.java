import java.util.Scanner;

public class b10819 { // 차이를 최대로
	static int N;
	static int[] arr;
	static int[] set;
	static boolean[] v;
	static int maxRst = 0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		arr = new int[N];
		set = new int[N];
		v = new boolean[N];
		for(int i=0;i<arr.length;i++) {
			arr[i] = sc.nextInt();
		}// 입력끝;
		perm(0);
		System.out.println(maxRst);
		
	}
	static void perm(int cnt) {
		if(cnt == N) {
			int rst = 0;
			for(int i=0;i<set.length-1;i++) {
				rst += Math.abs(set[i]-set[i+1]);
			}
			maxRst = Math.max(maxRst, rst);
			return;
		}
		for(int i=0;i<N;i++) {
			if(v[i]) continue;
			v[i] = true;
			set[cnt] = arr[i];
			perm(cnt+1);
			v[i] = false;
		}
	}
}
