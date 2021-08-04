import java.util.Scanner;

public class b15658 { // 연산자 끼워넣기(2)
	static int N;
	static int[] arr;
	static int[] oper;
	static int maxRst = Integer.MIN_VALUE;
	static int minRst = Integer.MAX_VALUE;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		arr = new int[N];
		oper = new int[4];
		for(int i=0;i<arr.length;i++) {
			arr[i] = sc.nextInt();
		}
		for(int i=0;i<4;i++) {
			oper[i] = sc.nextInt();
		}
		perm(0,0,0,0,0,arr[0]);
		System.out.println(maxRst);
		System.out.println(minRst);
	}
	static void perm(int cnt,int plus,int minus,int mult,int divide,int curRst) {
		if(cnt == N-1) {
			maxRst = Math.max(curRst, maxRst);
			minRst = Math.min(curRst, minRst);
			return;
		}//배열에 같은 값들이 들어있을경우 같은 순열을 여러번 만들수있다
		// for문 이용하는 것대신 개수를 cnt 하면서 재귀하는 방법으로 해결가능
		if(plus<oper[0]) perm(cnt+1,plus+1,minus,mult,divide,curRst+arr[cnt+1]);
		if(minus<oper[1]) perm(cnt+1,plus,minus+1,mult,divide,curRst-arr[cnt+1]);
		if(mult<oper[2]) perm(cnt+1,plus,minus,mult+1,divide,curRst*arr[cnt+1]);
		if(divide<oper[3]) perm(cnt+1,plus,minus,mult,divide+1,curRst/arr[cnt+1]);
	}
	
}
