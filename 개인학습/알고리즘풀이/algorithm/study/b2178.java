import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class b2178 { // 미로탐색
	static int N;
	static int M;
	static StringBuffer sb[];
	static boolean v[][];
	static int[] dx = {0,1,0,-1};
	static int[] dy = {-1,0,1,0};
	static int rst = Integer.MAX_VALUE;
	static class Point{
		int i;
		int j;
		int level;
		@Override
		public String toString() {
			return "Point [i=" + i + ", j=" + j + ", level=" + level + "]";
		}
		public Point(int i, int j, int level) {
			super();
			this.i = i;
			this.j = j;
			this.level = level;
		}
			
	}
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		sb = new StringBuffer[N];
		v = new boolean[N][M];
		for(int i=0;i<N;i++) {
			sb[i] = new StringBuffer(sc.next());
		}// 입력끝
		bfs();
		System.out.println(rst);
		
	}
	static void bfs() {
		Queue<Point> q = new LinkedList<>();
		int ny,nx;
		Point temp;
		v[0][0] = true;
		q.offer(new Point(0,0,1));
		while(!q.isEmpty()) {
			temp = q.poll();
			for(int l=0;l<4;l++) {
				ny = temp.i + dy[l];
				nx = temp.j + dx[l];
				if(!isBound(ny,nx)) continue;
				if(v[ny][nx]) continue;
				if(sb[ny].charAt(nx)=='0') continue;
				if(ny == N-1 && nx == M-1) {
					rst = temp.level+1;
					return;
				}
				v[ny][nx] = true;
				q.offer(new Point(ny,nx,temp.level+1));
			}
		}
	}
	static boolean isBound(int ny,int nx) {
		return ny>=0 && nx>=0 && ny<N && nx<M;
	}
}
