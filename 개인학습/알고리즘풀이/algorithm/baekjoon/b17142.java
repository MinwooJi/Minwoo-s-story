import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class b17142 { // 연구소 3 조합 + bfs
	static char[][] mat;
	static char[][] matC;
	static int[] set;
	static boolean[][] v;
	static int minRst = Integer.MAX_VALUE;
	static int[] dy = {-1,0,1,0};
	static int[] dx = {0,1,0,-1};
	static int N,M;
	static class Pos{
		int i;
		int j;
		int num;
		public Pos(int i, int j, int num) {
			this.i = i;
			this.j = j;
			this.num = num;
		}
		public Pos(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
	static ArrayList<Pos> pArr = new ArrayList<>();
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		mat = new char[N][N];
		matC = new char[N][N];
		v = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				mat[i][j] = st.nextToken().charAt(0); // 원본
			}
		}// 입력끝
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(mat[i][j] == '2') {
					pArr.add(new Pos(i,j));
					mat[i][j] = '*';
				}else if(mat[i][j] == '1') {
					mat[i][j] = '-';
				}
			}
		}
		for (int i = 0; i < mat.length; i++) {
			matC[i] = mat[i].clone();
		}
		set = new int[M];
		comb(0,0);
		System.out.println(minRst==Integer.MAX_VALUE?-1:minRst);
	}
	static void bfs(int[] set) {
		Queue<Pos> q = new LinkedList<>();
		int ny,nx;
		Pos temp;
		for (int i = 0; i < set.length; i++) {
			temp = pArr.get(set[i]);
			q.offer(temp);
			v[temp.i][temp.j] = true;
			matC[temp.i][temp.j] = '0';
		}

		while(!q.isEmpty()) {
			temp = q.poll();
			for (int l = 0; l < 4; l++) {
				ny = temp.i + dy[l];
				nx = temp.j + dx[l];
				if(!isBound(ny, nx)) continue;
				if(v[ny][nx]) continue;
				if(matC[ny][nx] == '-') continue;
				if(matC[ny][nx] == '*') {
					v[ny][nx] = true;
					q.offer(new Pos(ny,nx,temp.num+1));
					continue;
				}
				v[ny][nx] = true;
				matC[ny][nx] = (char)(temp.num+1+'0');
				q.offer(new Pos(ny,nx,temp.num+1));
			}
		}
	}
	static void comb(int cnt,int idx) {
		if(cnt == M) {
			v = new boolean[N][N];
			for (int i = 0; i < mat.length; i++) {
				matC[i] = mat[i].clone();
			}
			
			bfs(set);
			int max = 0;
			for (int i = 0; i < matC.length; i++) {
				for (int j = 0; j < matC[0].length; j++) {
					if(!v[i][j] && matC[i][j] == '0') {
						return;
					}else {
						max = Math.max(max, matC[i][j]-'0');
					}
				}
			}
			minRst = Math.min(minRst, max);
			return;
		}else {
			for (int i = idx; i < pArr.size(); i++) {
				set[cnt] = i;
				comb(cnt+1,i+1);
			}
		}
	}
	static boolean isBound(int ny,int nx) {
		return ny>=0 && nx>= 0 && ny<N && nx <N;
	}
}
