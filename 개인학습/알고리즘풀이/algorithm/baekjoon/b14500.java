import java.util.Scanner;

public class b14500 { // 테트로미노
	static boolean v[][];
	static int[][] mat;
	static int N,M;
	static int maxRst = 0;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {-1,0,1,0};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		mat = new int[N][M];
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				mat[i][j] = sc.nextInt();
			}
		}
		v = new boolean[N][M];
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				v[i][j] = true;
				dfs(1,i,j,mat[i][j]);
				special(i,j);
				v[i][j] = false;
			}
		}
		System.out.println(maxRst);
	}
	static void special(int y,int x) {
		//ㅗ
		if(isBound(y-1,x) && isBound(y,x+1) && isBound(y,x-1)) {
			maxRst = Math.max(maxRst, mat[y][x] + mat[y-1][x] + mat[y][x-1] + mat[y][x+1]);
		}//ㅏ
		if(isBound(y-1,x) && isBound(y,x+1) && isBound(y+1,x)) {
			maxRst = Math.max(maxRst, mat[y][x] + mat[y-1][x] + mat[y+1][x] + mat[y][x+1]);
		}//ㅜ
		if(isBound(y+1,x) && isBound(y,x+1) && isBound(y,x-1)) {
			maxRst = Math.max(maxRst, mat[y][x] + mat[y+1][x] + mat[y][x-1] + mat[y][x+1]);
		}//ㅓ
		if(isBound(y-1,x) && isBound(y+1,x) && isBound(y,x-1)) {
			maxRst = Math.max(maxRst, mat[y][x] + mat[y-1][x] + mat[y][x-1] + mat[y+1][x]);
		}
	}
	static void dfs(int cnt,int y,int x,int curRst) {
		if(cnt == 4) {
			maxRst = Math.max(maxRst, curRst);
			return;
		}
		int ny,nx;
		for(int l=0;l<4;l++) {
			ny = y + dy[l];
			nx = x + dx[l];
			if(!isBound(ny,nx)) continue;
			if(v[ny][nx]) continue;
			v[ny][nx] = true;
			dfs(cnt+1,ny,nx,curRst + mat[ny][nx]);
			v[ny][nx] = false;
			
			
		}
	}
	static boolean isBound(int ny,int nx) {
		return ny >= 0 && nx >= 0 && ny < N && nx <M;
	}
}
