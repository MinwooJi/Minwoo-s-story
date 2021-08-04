import java.util.Scanner;

public class b4963 { // 섬의 개수
	static int N;
	static int M;
	static int mat[][];
	static boolean v[][];
	static int dx[] = {0,1,1,1,0,-1,-1,-1};
	static int dy[] = {-1,-1,0,1,1,1,0,-1};
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		while(true) {
			M = sc.nextInt();
			N = sc.nextInt();
			if(M == 0 && N == 0) break;
			mat = new int[N][M];
			v = new boolean[N][M];
			for(int i=0;i<mat.length;i++) {
				for(int j=0;j<mat[0].length;j++) {
					mat[i][j] = sc.nextInt();
				}
			}//입력끝
			int numbering = 0;
			for(int i=0;i<mat.length;i++) {
				for(int j=0;j<mat[0].length;j++) {
					if(mat[i][j]==1 && !v[i][j]) {
						v[i][j] = true;
						numbering ++;
						dfs(i,j);
					}
				}
			}
			System.out.println(numbering);
		}
	}
	static void dfs(int y,int x) {
		int ny,nx;
		for(int l=0;l<8;l++) {
			ny = y + dy[l];
			nx = x + dx[l];
			if(!isBound(ny,nx)) continue;
			if(v[ny][nx]) continue;
			if(mat[ny][nx]==0) continue;
			v[ny][nx] = true;
			dfs(ny,nx);
		}
	}
	static boolean isBound(int ny,int nx) {
		return ny>=0 && nx>=0 && ny<N && nx <M;
	}
}
