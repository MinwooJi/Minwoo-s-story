import java.util.Scanner;

public class swe1247 {
	static boolean v[];
	static int N;
	static Point[] pArr;
	static Point start, end;
	static int minRst;
	static class Point{
		int i;
		int j;
		public Point(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
		@Override
		public String toString() {
			return "Point [i=" + i + ", j=" + j + "]";
		}
		
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		for(int t=0;t<tc;t++) {
			N = sc.nextInt();
			v = new boolean[N];
			pArr = new Point[N];
			minRst = Integer.MAX_VALUE;
			start = new Point(sc.nextInt(),sc.nextInt());
			end = new Point(sc.nextInt(),sc.nextInt());
			for(int i=0;i<N;i++) {
				pArr[i] = new Point(sc.nextInt(),sc.nextInt());
			} // 입력끝
			dfs(0,0,start);
			System.out.printf("#%d ",t+1);
			System.out.println(minRst);
		}
	}
	static void dfs(int cnt, int curLength, Point curPos) {
		if(cnt == N) {
			minRst = Math.min(minRst, curLength+calc(curPos,end)); 
			return;
		}
		if(curLength > minRst) return;
		for(int i=0;i<N;i++) {
			if(v[i]) continue;
			v[i] = true;
			dfs(cnt+1,curLength+calc(curPos,pArr[i]),pArr[i]);
			v[i] = false;
		}
	}
	static int calc(Point a, Point b) {
		int rst = Math.abs(a.i - b.i) + Math.abs(a.j - b.j);
		return rst;
	}
}
