import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class b15686 { // 치킨배달 조합+bfs(굳이 bfs안쓰는게 훨씬 나은 방법일듯)
	static int N,M;
	static int[][] mat;
	static int[] set;
	static boolean[][] v;
	static ArrayList<Pos> chcList = new ArrayList<>();
	static ArrayList<Integer> homeList = new ArrayList<>();
	static int[] dx = {0,1,0,-1};
	static int[] dy = {-1,0,1,0};
	static int minRst = Integer.MAX_VALUE;
	static class Pos{
		int i;
		int j;
		int id;
		public Pos(int i, int j, int id) {
			this.i = i;
			this.j = j;
			this.id = id;
		}
		@Override
		public String toString() {
			return "Pos [i=" + i + ", j=" + j + ", id=" + id + "]";
		}
		
	}
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		mat = new int[N][N];
		v = new boolean[N][N];
		set = new int[M];
		int idx = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				mat[i][j] = Integer.parseInt(st.nextToken());
				if(mat[i][j] == 2) {
					chcList.add(new Pos(i,j,idx++));
				}
			}
		}// 입력끝
		comb(0,0);
		System.out.println(minRst);
	}
	static void comb(int idx,int cnt) {
		if(cnt == M) {
			v = new boolean[N][N];
			homeList.clear();
			bfs();
			return ;
		}
		for (int i = idx; i < chcList.size(); i++) {
			set[cnt] = i;
			comb(i+1,cnt+1);
		}
	}
	static void bfs() {
		Pos temp;
		int ny,nx;
		Queue<Pos> q = new LinkedList<>();
		for (int i = 0; i < M; i++) {
			q.offer(chcList.get(set[i]));
			v[chcList.get(set[i]).i][chcList.get(set[i]).j] = true;
		}
		while(!q.isEmpty()) {
			temp = q.poll();
			for (int l = 0; l < 4; l++) {
				ny = temp.i + dy[l];
				nx = temp.j + dx[l];
				if(!isBound(ny,nx)) continue;
				if(v[ny][nx]) continue;
				if(mat[ny][nx] == 1) {
					homeList.add(calc(chcList.get(temp.id),new Pos(ny,nx,0)));
				}
				v[ny][nx] = true;
				q.offer(new Pos(ny,nx,temp.id));
				
			}
		}
		int rst = 0;
		for (int i = 0; i < homeList.size(); i++) {
			rst +=homeList.get(i);
		}
		minRst = Math.min(minRst, rst);
		
	}
	static int calc(Pos a,Pos b) {
		return Math.abs(a.i - b.i) + Math.abs(a.j - b.j);
	}
	static boolean isBound(int ny,int nx) {
		return ny>=0 && nx>=0 && ny<N && nx<N;
	}
}
