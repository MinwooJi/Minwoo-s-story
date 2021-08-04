import java.util.Scanner;

public class b1987 { // 알파벳
	static int bitMask = 0;
	static StringBuffer sb[];
	static int N;
	static int M;
	static boolean v[][];
	static int[] dx = {0,1,0,-1};
	static int[] dy = {-1,0,1,0};
	static int rst = 0;
	static class Point{
		int i;
		int j;
		@Override
		public String toString() {
			return "Point [i=" + i + ", j=" + j + "]";
		}
		public Point(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
		
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		sb = new StringBuffer[N];
		v = new boolean[N][M];
		for(int i=0;i<N;i++) {
			sb[i] = new StringBuffer(sc.next());
		}// 입력끝
		v[0][0] = true;
		bitMask = bitMask | (1<<(sb[0].charAt(0)-'A'));
		dfs(0,0,1);
		System.out.println(rst);
	}
	static void dfs(int y,int x,int cnt) {
		rst = Math.max(cnt, rst);
		int ny,nx;
		for(int l=0;l<4;l++) {
			ny = y + dy[l];
			nx = x + dx[l];
			if(!isBound(ny,nx)) continue;
			if(v[ny][nx]) continue;
			if((bitMask & (1<<(sb[ny].charAt(nx)-'A')) ) != 0) continue;
			bitMask = bitMask | (1<<(sb[ny].charAt(nx)-'A'));
			v[ny][nx] = true;
			dfs(ny,nx,cnt+1);
			bitMask = bitMask &~ (1<<(sb[ny].charAt(nx)-'A'));
			v[ny][nx] = false;
		}
	}
	static boolean isBound(int ny,int nx) {
		return ny >=0 && nx >=0 && nx< M && ny< N;
	}
}
