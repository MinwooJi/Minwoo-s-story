import java.util.Scanner;

public class b14501 {//퇴사
	static class Day{
		int tI;
		int pI;
		public Day(int tI, int pI) {
			this.tI = tI;
			this.pI = pI;
		}
	}
	static Day[] dArr;
	static boolean[] v;
	static int maxRst = 0;
	static int N;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		dArr = new Day[N];
		v = new boolean[N];
		for(int i=0;i<N;i++) {
			dArr[i] = new Day(sc.nextInt(),sc.nextInt());
		}
		dfs(0,0);
		System.out.println(maxRst);
	}
	static void dfs(int cnt,int curPrice) {
		if(cnt>N) return;
		maxRst = Math.max(maxRst, curPrice);
		if(cnt==N)return;
		dfs(cnt+dArr[cnt].tI,curPrice+dArr[cnt].pI);
		dfs(cnt+1,curPrice);
	}
}
