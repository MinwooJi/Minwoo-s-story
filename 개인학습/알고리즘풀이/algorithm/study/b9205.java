import java.util.Scanner;
public class b9205 {
	static class Point{
		int j;
		int i;
		public Point(int j, int i) {
			super();
			this.j = j;
			this.i = i;
		}
		public String toString() {
			return "Point [j=" + j + ", i=" + i + "]";
		}
	}
	static boolean flag;
	static boolean v[];
	static Point[] pArr;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		for(int t=0;t<tc;t++) {
			int n = sc.nextInt();
			pArr = new Point[n+2]; // 상근이집 /편으점 / 목적지
			v = new boolean[n+2];
			for(int i=0;i<pArr.length;i++) {
				pArr[i] = new Point(sc.nextInt(),sc.nextInt());
			}
			flag = false;
			v[0] = true;
			dfs(0);
			if(flag) 
				System.out.println("happy");
			else
				System.out.println("sad");
		}
	}
	static void dfs(int idx) {
		if(pArr[idx].i==pArr[pArr.length-1].i && pArr[idx].j == pArr[pArr.length-1].j) {
			flag = true;
			return;
		}
		if(flag) return;
		for(int i=1;i<pArr.length;i++) {
			if(idx == i) continue;
			if(v[i]) continue;
			if(calc(pArr[idx],pArr[i]) <= 1000) {
				v[i] = true;
				dfs(i);
//				v[i] = false;
			}
		}
	}
	static int calc(Point a, Point b) {
		return Math.abs(a.i-b.i) + Math.abs(a.j-b.j);
	}
}
