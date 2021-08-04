import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class b15683 { // 감시 (복잡한 시뮬레이션 + dfs)
	static int[][] mat;
	static List<Pos>list;
	static int N,M;
	static int dx[] = {0,1,0,-1};
	static int dy[] = {-1,0,1,0};
	static int minRst = Integer.MAX_VALUE;
	static class Pos{
		int i;
		int j;
		public Pos(int i, int j) {
			this.i = i;
			this.j = j;
		}
		@Override
		public String toString() {
			return "Pos [i=" + i + ", j=" + j + "]";
		}

	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		mat = new int[N][M];
		list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				mat[i][j] = Integer.parseInt(st.nextToken());
				if(mat[i][j] != 0 && mat[i][j] != 6) {
					list.add(new Pos(i,j));
				}
			}
		}// 입력끝
		dfs(0,mat);
		System.out.println(minRst);

	}
	static void dfs(int idx,int now[][]) {
		if(idx == list.size()) {
			int cnt = 0;
			for (int i = 0; i < now.length; i++) {
				for (int j = 0; j < now[0].length; j++) {
					if(now[i][j] == 0) cnt++;
				}
			}
			minRst = Math.min(minRst, cnt);
			return;
		}
		int newMat[][] = new int[N][M];
		Pos p = list.get(idx);
		int func = mat[p.i][p.j];
		switch(func) {
		case 1:
			for (int i = 0; i < 4; i++) {
				for (int k = 0; k < newMat.length; k++) {
					newMat[k] = now[k].clone();
				}
				run(p,newMat,i);
				dfs(idx+1,newMat);
			}
			break;
		case 2:
			for (int i = 0; i < 2; i++) {
				for (int k = 0; k < newMat.length; k++) {
					newMat[k] = now[k].clone();
				}
				run(p,newMat,i);
				run(p,newMat,(i+2)%4);
				dfs(idx+1,newMat);
			}
			break;
		case 3:
			for (int i = 0; i < 4; i++) {
				for (int k = 0; k < newMat.length; k++) {
					newMat[k] = now[k].clone();
				}
				run(p,newMat,i);
				run(p,newMat,(i+1)%4);
				dfs(idx+1,newMat);
			}
			break;
		case 4:
			for (int i = 0; i < 4; i++) {
				for (int k = 0; k < newMat.length; k++) {
					newMat[k] = now[k].clone();
				}
				run(p,newMat,i);
				run(p,newMat,(i+1)%4);
				run(p,newMat,(i+3)%4);
				dfs(idx+1,newMat);
			}
			break;
		case 5:
			for (int k = 0; k < newMat.length; k++) {
				newMat[k] = now[k].clone();
			}
			run(p,newMat,0);
			run(p,newMat,1);
			run(p,newMat,2);
			run(p,newMat,3);
			dfs(idx+1,newMat);
			break;

		}
	}
	static void run(Pos p,int newMat[][],int dir) {
		int ny,nx;
		ny = p.i + dy[dir];
		nx = p.j + dx[dir];
		while(isBound(ny,nx)) {
			if(mat[ny][nx] == 6) break;
			newMat[ny][nx] = 7;
			ny += dy[dir];
			nx += dx[dir];
		}

	}
	static boolean isBound(int ny,int nx) {
		return ny>=0 && nx >=0 && ny< N && nx<M;
	}
}
