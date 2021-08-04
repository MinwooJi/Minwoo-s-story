import java.util.Scanner;

public class b1182 { // 부분수열의 합
	static int N;
	static int S;
	static int[] arr;
	static boolean v[];
	static int rst = 0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		S = sc.nextInt();
		arr = new int[N];
		v = new boolean[N];
		for(int i=0;i<arr.length;i++) {
			arr[i] = sc.nextInt();
		}
		comb(0,0,0);
		System.out.println(rst);
	}
	static void comb(int idx,int sum,int cnt) {
		if(cnt!=0 && sum == S) {
			rst ++; // return 하면 안됨
		}
		for(int i = idx; i<N;i++) {
			if(v[i]) continue;
			v[i] = true;
			comb(i,sum+arr[i],cnt+1);
			v[i] = false;
		}
	}
}
