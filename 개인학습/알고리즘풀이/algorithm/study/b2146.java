import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
public class b2146 {
	static int N;
	static int[][] mat;
	static boolean[][] v;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {-1,0,1,0};
	static int numbering=1;
	static int minLength = 987654321;
	static class Point{
		int i;
		int j;
		int length;
		public Point(int i, int j, int length) {
			this.i = i;
			this.j = j;
			this.length = length;
		}
		public Point() {}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc  = new Scanner(System.in);
		N = sc.nextInt();
		mat = new int[N][N];
		v = new boolean[N][N];
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				mat[i][j] = sc.nextInt();
			}
		}// 입력끝
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(mat[i][j] != 0 && !v[i][j]) {
					mat[i][j] = numbering;
					dfsNumbering(j, i);
					numbering ++;
				}
			}
		}
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(mat[i][j] != 0) {
					// 여기서 4방탐색 처리?
					for(int l =0;l<4;l++) {
						int ny = i + dy[l];
						int nx = j + dx[l];
						if(!isBound(nx,ny))
							continue;
						if(mat[ny][nx] == 0) {
							v = new boolean[N][N];
							int myNum = mat[i][j];
							v[ny][nx] = true;
							bfsMakeBridge(ny,nx,myNum);
						}
					}
				}
			}
		}
		if(minLength==987654321)
			minLength = 0;
		System.out.println(minLength);
	}
	static void bfsMakeBridge(int i,int j,int myNum) {
		Queue<Point> q = new LinkedList<>();
		Point temp = new Point();
		int nx,ny;
		q.offer(new Point(i,j,1));
		while(!q.isEmpty()) {
			temp = q.poll();
			for(int l=0;l<4;l++) {
				nx = temp.j + dx[l];
				ny = temp.i + dy[l];
				if(temp.length+1 > minLength) 
					break;
				if(!isBound(nx,ny))
					continue;
				if(v[ny][nx])
					continue;
				if(mat[ny][nx] == 0) {
					v[ny][nx] = true;
					q.offer(new Point(ny,nx,temp.length+1));
				}
				else if(mat[ny][nx] != 0 && mat[ny][nx] != myNum ) {
					minLength = Math.min(minLength, temp.length);
					break;
				}
			}
		}

	}
	static void dfsNumbering(int x,int y) {
		int ny,nx;
		for(int i=0;i<4;i++) {
			ny = y + dy[i];
			nx = x + dx[i];
			if(!isBound(nx,ny))
				continue;
			if(v[ny][nx])
				continue;
			if(mat[ny][nx] != 0 ) {
				mat[ny][nx] = numbering;
				v[ny][nx] = true;
				dfsNumbering(nx,ny);
			}

		}
	}
	static boolean isBound(int x,int y) {
		return x>=0 && y>=0 && x<N && y<N;
	}

}