import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class b2589 {
	static int H;
	static int W;
	static char[][] mat;
	static boolean[][] v;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {-1,0,1,0};
	static int max = 0;
	static class Point{
		int i;
		int j;
		int length;
		public Point(int i, int j, int length) {
			this.i = i;
			this.j = j;
			this.length = length;
		}

	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		H = sc.nextInt();
		W = sc.nextInt();
		mat = new char[H][W];
		String temp = new String("");
		for(int i=0;i<H;i++) {
			temp = sc.next();
			mat[i] = temp.toCharArray();
		}// 입력끝
		for(int i=0;i<H;i++) {
			for(int j=0;j<W;j++) {
				if(mat[i][j] == 'L') {
					bfs(i,j);
				}
			}
		}
		
		System.out.println(max);
	}
	static void bfs(int i,int j) {
		Queue<Point> q = new LinkedList<>();
		Point temp;
		v = new boolean[H][W];
		v[i][j] = true;
		q.offer(new Point(i,j,0));
		int ny,nx;
		while(!q.isEmpty()) {
			temp = q.poll();
			max = Math.max(max, temp.length);
			for(int l =0;l<4;l++) {
				ny = temp.i + dy[l];
				nx = temp.j + dx[l];
				if(!isBound(ny, nx)) continue;
				if(v[ny][nx]) continue;
				if(mat[ny][nx] == 'L') {
					v[ny][nx] = true;
					q.offer(new Point(ny,nx,temp.length+1));
				}
			}
		}
		
	}
	static boolean isBound(int ny,int nx) {
		return ny>= 0 && nx>=0 && ny<H && nx <W;
	}
}
