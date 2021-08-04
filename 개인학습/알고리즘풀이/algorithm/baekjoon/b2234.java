import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class b2234 {//성곽 (dfs bfs 완탐)
	static int N,M;
	static boolean[][] v;
	static boolean[] nVisit;
	static int[][] mat;
	static int[][] numbering;
	static int[] nCnt;
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,-1,0,1};
	static int maxRst;
	static class Pos{
		int i;
		int j;
		public Pos(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		M = sc.nextInt();
		N = sc.nextInt();
		mat = new int[N][M];
		numbering = new int[N][M];
		v = new boolean[N][M];
		nCnt = new int[N*M +1]; // 0버림
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				mat[i][j] = sc.nextInt();
			}
		} // 입력끝
		int number = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(!v[i][j]) {
					numbering(i,j,number);
					number ++;
				}
			}
		}
		System.out.println(number - 1);
		int maxArea = 0;
		for (int i = 1; i < nCnt.length; i++) {
			if(nCnt[i] == 0) break;
			else {
				maxArea = Math.max(maxArea, nCnt[i]);
			}
		}
		System.out.println(maxArea);
		v = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(!v[i][j]) {
					nVisit = new boolean[number];// 0버림
					v[i][j] = true;
					breakWall(i, j, numbering[i][j]);
				}
			}
		}
		System.out.println(maxRst);
		
	}
	static void breakWall(int si,int sj,int myNumber) {
		int ny,nx;
		for (int l = 0; l < 4; l++) {
			ny = si + dy[l];
			nx = sj + dx[l];
			if(!isBound(ny, nx)) continue;
			if(numbering[ny][nx] != myNumber) { // 인접한 다른 방과 만남
				if(nVisit[numbering[ny][nx]]) continue; // 계산한적있음
				nVisit[numbering[ny][nx]] = true;
				maxRst = Math.max(maxRst, nCnt[myNumber]+nCnt[numbering[ny][nx]]);
				continue;
			}
			if(v[ny][nx]) continue;
			v[ny][nx] = true;
			breakWall(ny,nx,myNumber);
		}
	}
	static void numbering(int si,int sj,int number) {
		Queue<Pos> q = new LinkedList<>();
		Pos temp;
		int ny,nx;
		v[si][sj] = true;
		nCnt[number] ++;
		numbering[si][sj] = number;
		q.offer(new Pos(si,sj));
		while(!q.isEmpty()) {
			temp = q.poll();
			for (int l = 0; l < 4; l++) {
				if((mat[temp.i][temp.j] & 1<<l) != 0) continue;
				ny = temp.i + dy[l];
				nx = temp.j + dx[l];
				if(!isBound(ny,nx)) continue;
				if(v[ny][nx]) continue;
				v[ny][nx] = true;
				numbering[ny][nx] = number;
				nCnt[number] ++;
				q.offer(new Pos(ny,nx));
			}
		}
	}
	static boolean isBound(int ny,int nx) {
		return ny>=0 && nx>=0 && ny<N && nx<M;
	}
}
