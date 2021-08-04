import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class swea_1949 { // 등산로조성(모의 역량테스트) // dfs+백트래킹
	static int[][] mat;
	static boolean[][] v;
	static int N,K;
	static int maxRst;
	static List<Pos> list;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {-1,0,1,0};
	static class Pos{
		int i;
		int j;
		public Pos(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int tc = Integer.parseInt(br.readLine());
		for (int t = 1; t <= tc; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			maxRst = 0; 
			mat = new int[N][N];
			list = new ArrayList<>();
			for (int i = 0; i < mat.length; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < mat[0].length; j++) {
					mat[i][j] = Integer.parseInt(st.nextToken());
				}
			}// 입력끝
			int max = 0;
			for (int i = 0; i < mat.length; i++) {
				for (int j = 0; j < mat[0].length; j++) {
					if(max < mat[i][j])
						max = mat[i][j];
				}
			}
			for (int i = 0; i < mat.length; i++) {
				for (int j = 0; j < mat[0].length; j++) {
					if(max == mat[i][j])
						list.add(new Pos(i,j));
				}
			}
			for (Pos pos : list) {
				v = new boolean[N][N];
				v[pos.i][pos.j] = true;
				dfs(pos.i,pos.j,mat[pos.i][pos.j],0,1);
			}
			sb.append("#").append(t).append(" ").append(maxRst).append("\n");
		}
		System.out.println(sb);
	}
	static void dfs(int y,int x,int val,int ab,int cnt) {
		if(cnt > maxRst) maxRst = cnt;
		int ny,nx;
		for (int l = 0; l < dx.length; l++) {
			ny = dy[l] + y;
			nx = dx[l] + x;
			if(!isBound(ny,nx)) continue;
			if(v[ny][nx]) continue;
			if(mat[ny][nx] < val) {
				v[ny][nx] = true;
				dfs(ny,nx,mat[ny][nx],ab,cnt+1);
			}else if(ab == 0 && mat[ny][nx] - K < val) {
				v[ny][nx] = true;
				dfs(ny,nx,val - 1,1,cnt+1);
			}
			v[ny][nx] = false;
		}
	}
	static boolean isBound(int ny,int nx) {
		return ny>=0 && nx>=0 && ny< N && nx< N;
	}
}
