import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class b7562 {
	static int N;
	static boolean[][] v;
	static int dx[] = {1,2,2,1,-1,-2,-2,-1};
	static int dy[] = {-2,-1,1,2,2,1,-1,-2};
	static int min = Integer.MAX_VALUE;
	static class Point{
		int i;
		int	j;
		int level;
		public Point(int i, int j, int level) {
			this.i = i;
			this.j = j;
			this.level = level;
		}
		public Point() {}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		for(int t=0;t<tc;t++) {
			N = sc.nextInt();
			v = new boolean[N][N];
			Point[] p = new Point[2];
			for(int i=0;i<2;i++) {
				p[i] = new Point(sc.nextInt(),sc.nextInt(),0);
			}
			bfs(p);
			if(min == Integer.MAX_VALUE)
				min = 0;
			System.out.println(min);
			min = Integer.MAX_VALUE;
		}
	}
	static void bfs(Point[] p) {
		Point start = p[0];
		Point target = p[1];
		Point temp = new Point();
		Queue<Point> q = new LinkedList<>();
		v[start.i][start.j] = true;
		q.offer(start);
		while(!q.isEmpty()) {
			temp = q.poll();
			for(int l=0;l<8;l++) {
				int ny = temp.i + dy[l];
				int nx = temp.j + dx[l];
				if(!isBound(nx,ny)) continue;
				if(v[ny][nx]) continue;
				if(ny==target.i && nx == target.j) {
					min = Math.min(temp.level+1, min);
					continue;
				}
				v[ny][nx] = true;
				q.offer(new Point(ny,nx,temp.level+1));
			}
		}
	}
	static boolean isBound(int x,int y) {
		return x>=0 && y>=0 && x<N && y<N;
	}
}
