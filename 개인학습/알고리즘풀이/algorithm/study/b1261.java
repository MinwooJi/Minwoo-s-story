import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
public class b1261 {
	static int N;
	static int M;
	static StringBuffer[] mat;
	static boolean [][][]v;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {-1,0,1,0};
	static int minBreak = Integer.MAX_VALUE;
	static class Point{
		int i;
		int j;
		int breakWall;
		@Override
		public String toString() {
			return "Point [i=" + i + ", j=" + j + ", breakWall=" + breakWall + "]";
		}
		public Point(int i, int j, int breakWall) {
			super();
			this.i = i;
			this.j = j;
			this.breakWall = breakWall;
		}

	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		M= sc.nextInt();
		N= sc.nextInt();
		mat = new StringBuffer[N];
		v = new boolean[N*M][N][M];
		for(int i=0;i<N;i++) {
			mat[i] = new StringBuffer(sc.next());
		}//입력끝
		bfs();
		System.out.println(minBreak);
	}
	static void bfs() {
		Queue<Point> q =new LinkedList<>();
		v[0][0][0] = true;
		q.offer(new Point(0,0,0));
		int ny,nx;
		Point temp;
		while(!q.isEmpty()) {
			temp = q.poll();
			if(temp.i == N-1 && temp.j == M-1) {
				minBreak = Math.min(minBreak, temp.breakWall);
			}
			if(temp.breakWall > minBreak) continue;
			for(int l=0;l<4;l++) {
				nx = temp.j + dx[l];
				ny = temp.i + dy[l];
				if(!isBound(ny,nx)) continue;
				if(mat[ny].charAt(nx) == '0') {
					if(v[temp.breakWall][ny][nx]) continue;
					v[temp.breakWall][ny][nx] = true;
					q.offer(new Point(ny,nx,temp.breakWall));
				}else if(mat[ny].charAt(nx) == '1') {
					if(v[temp.breakWall+1][ny][nx]) continue;
					v[temp.breakWall+1][ny][nx] = true;
					q.offer(new Point(ny,nx,temp.breakWall+1));
				}
			}
		}
	}
	static boolean isBound(int ny,int nx) {
		return ny>=0 && nx>=0 && ny<N && nx<M;
	}
}