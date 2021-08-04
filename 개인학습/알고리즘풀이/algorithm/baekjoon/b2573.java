import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/*
 * 
 * 5 7
0 0 0 0 0 0 0
0 2 4 5 3 0 0
0 3 0 2 5 2 0
0 7 6 2 4 0 0
0 0 0 0 0 0 0
 */
public class b2573 {
	static class Point{
		int x;
		int y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public Point() {}
		
	}
	static int N;
	static int M;
	static int[][] mat;
	static int[][] copyMat;
	static int[][] copyMat2;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {-1,0,1,0};
	static int num = 1;
	static boolean[][] visited;
	static boolean fin = false;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		mat = new int[N][M];
		copyMat = new int[N][M];
		copyMat2 = new int[N][M];
		visited = new boolean[N][M];
		for(int i=0;i<N;i++) {
			for(int j =0;j<M;j++ ) {
				mat[i][j] = sc.nextInt();
			}
		}// 입력 끝
		int cnt = 0;
		boolean flag = false;
//		for(int i=0;i<N;i++) {
//			for(int j =0;j<M;j++ ) {
//				copyMat[i][j] = mat[i][j];
//			}
//		}// dfs용 복사
		while(!fin) {
			cnt++;
//			meltBfs();
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					if(mat[i][j]!=0)
						copyMat[i][j] = melt(i,j);
				}
			}
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					if(mat[i][j]-copyMat[i][j]<0)
						mat[i][j] = 0;
					else
						mat[i][j] -= copyMat[i][j];
				}
			}
			copyMat = new int[N][M];
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					copyMat2[i][j] = mat[i][j];
				}
			}
			for(int i=1;i<N-1;i++) {
				for(int j =1;j<M-1;j++) {
					if(copyMat2[i][j]==0)
						continue;
					flag = true;
					if(!visited[i][j]) {
						sol(i,j);
						num++;
					}
				}
			}
			if(!flag) {
				cnt = 0;
				break;
			}
			flag = false;
			num = 1;
			visited = new boolean[N][M];
		}
		System.out.println(cnt);
	}
//	static void meltBfs() {
//		Queue<Point> q = new LinkedList<>();
//		boolean[][] v = new boolean[N][M];
//		for(int i=0;i<N;i++) {
//			for(int j=0;j<M;j++) {
//				if(mat[i][j] == 0) {
//					q.offer(new Point(j,i));
//					v[i][j] = true;
//				}
//			}
//		}
//		Point temp;
//		int ny,nx;
//		while(!q.isEmpty()) {
//			temp = q.poll();
//			for(int l=0;l<4;l++) {
//				ny = temp.y+dy[l];
//				nx = temp.x+dx[l];
//				if(!isBound(ny,nx))
//					continue;
//				if(mat[ny][nx]!=0) {
//					mat[ny][nx] -=1;
//				}
//				if(mat[ny][nx]==0 && !v[ny][nx]) {
//					q.offer(new Point(nx,ny));
//					v[ny][nx] = true;
//				}
//				
//			}
//		}
//	}
	static void sol(int y,int x) {
		if(visited[y][x])
			return;
		if(num>1) {
			fin = true;
			return;
		}
		visited[y][x] = true;
		copyMat2[y][x] = num;
		int ny,nx;
		for(int l=0;l<4;l++) {
			ny = y + dy[l];
			nx = x + dx[l];
			if(!isBound(ny,nx))
				continue;
			if(mat[ny][nx]==0)
				continue;
			sol(ny,nx);
		}


	}
	static int melt(int y,int x) {
		int ny,nx;
		int cnt = 0;
		for(int l =0;l<4;l++) {
			ny = y + dy[l];
			nx = x + dx[l];
			if(!isBound(ny,nx)) {
				continue;
			}
			if(mat[ny][nx] == 0)
				cnt++;
		}
		return cnt;
	}

	static boolean isBound(int ny,int nx) {
		return ny>=0 && nx>=0 && ny<N && nx<M;
	}
}
