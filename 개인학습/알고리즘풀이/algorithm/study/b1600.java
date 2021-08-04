import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class b1600 {
	static int dx[] = {1,2,2,1,-1,-2,-2,-1};
	static int dy[] = {-2,-1,1,2,2,1,-1,-2};
	static int dx1[] = {0,1,0,-1};
	static int dy1[] = {-1,0,1,0};
	static int K;
	static int H;
	static int W;
	static int mat[][];
	static int min = Integer.MAX_VALUE;
	static class Point{
		int i;
		int j;
		int level;
		int Jump;
		public Point(int i, int j, int level, int Jump) {
			this.i = i;
			this.j = j;
			this.level = level;
			this.Jump = Jump;
		}
		public String toString() {
			return "Point [i=" + i + ", j=" + j + ", level=" + level + ", Jump=" + Jump + "]";
		}
		
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		K =sc.nextInt();
		W = sc.nextInt();
		H = sc.nextInt();
		mat = new int[H][W];
		for(int i=0;i<H;i++) {
			for(int j=0;j<W;j++) {
				mat[i][j] = sc.nextInt();
			}
		}// 입력끝
		bfs();
		if(min == Integer.MAX_VALUE)
			min = -1;
		System.out.println(min);
	}
	static void bfs() {
		Queue<Point> q = new LinkedList<>();
		boolean v[][][] = new boolean[K+1][H][W]; // 말처럼 뛰기전
		v[0][0][0] = true;
		Point temp;
		int ny,nx;
		boolean flag;
		q.offer(new Point(0,0,0,0));
		while(!q.isEmpty()) {
			temp = q.poll();
			if(temp.Jump < K) {
				for(int l=0;l<8;l++) {
					ny = temp.i + dy[l];
					nx = temp.j + dx[l];
					if(!isBound(ny,nx)) continue;
					flag = false;
					for(int i=0;i<=temp.Jump+1;i++) { // 말뛰기 횟수 = v idx
						if(v[i][ny][nx]) {
							flag = true;
							break;
						}
					}
					if(flag) continue;
//					if(v[temp.Jump+1][ny][nx]) continue;
					if(temp.level+1 >= min) {
						break;
					}
					if(ny == H-1 && nx == W-1) {
						min = Math.min(min, temp.level+1);
						break; // break해도 될듯
					}
					if(mat[ny][nx] == 0) {
						v[temp.Jump+1][ny][nx] = true;
						q.offer(new Point(ny,nx,temp.level+1,temp.Jump+1));
					}
				}
			}
			for(int l=0;l<4;l++) {
				ny = temp.i + dy1[l];
				nx = temp.j + dx1[l];
				if(!isBound(ny, nx)) continue;
				flag = false;
				for(int i=0;i<temp.Jump+1;i++) { // 말뛰기 횟수 = v idx
					if(v[i][ny][nx]) {
						flag = true;
						break;
					}
				}
				if(flag) continue;
//				if(v[temp.Jump][ny][nx]) continue;
				if(temp.level+1 >= min) {
					break;
				}
				if(ny == H-1 && nx == W-1) {
					min = Math.min(min, temp.level+1);
					break; // break해도 될듯
				}
				if(mat[ny][nx] == 0) {
					v[temp.Jump][ny][nx] = true;
					q.offer(new Point(ny,nx,temp.level+1,temp.Jump));
				}
				
			}
		}
	}
	static boolean isBound(int ny,int nx) {
		return ny>=0 && nx>=0 && ny<H && nx<W;
	}
}
