import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17135_캐슬디펜스 { // BOJ_17135(캐슬디펜스)-삼성기출 시뮬레이션 + bfs
	static int N,M,D;
	static int[][] mat;
	static int[][] virtual;
	static int[] arr = new int[3];
	static int[] dy = {0,-1,0};
	static int[] dx = {-1,0,1};
	static Pos[] archer = new Pos[3];
	static Queue<Pos> q;
	static boolean[][][] range;
	static boolean[] v;
	static int rst;
	static int cur;
	static class Pos{
		int i;
		int j;
		int len;
		int mark;
		public Pos(int i, int j, int len, int mark) {
			super();
			this.i = i;
			this.j = j;
			this.len = len;
			this.mark = mark;
		}
		@Override
		public String toString() {
			return "Pos [i=" + i + ", j=" + j + ", len=" + len + ", mark=" + mark + "]";
		}
		
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()) + 1;
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		mat = new int[N][M];
		v = new boolean[M];
		rst = 0;
		for(int i=0;i<N-1;i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				mat[i][j] = Integer.parseInt(st.nextToken());
			}
		} // input end
		comb(0,0);
		System.out.println(rst);
	}
	static void proc() {
		range = new boolean[3][N][M];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < M; j++) {					
				range[i][N-1][j] = true;
			}
		}
		q = new LinkedList<Pos>();
		for(int i=0; i<3;i++) {
			q.offer(archer[i]);
		}
		int ny,nx;
		Pos temp;
		// 3 숫자세고 kill cnt
		boolean[] shoot = new boolean[3];
		while(!q.isEmpty()) {
			temp = q.poll();
			if(temp.len+1 > D) break;
			for (int l = 0; l < dx.length; l++) {
				ny = temp.i + dy[l];
				nx = temp.j + dx[l];
				if(!isBound(ny,nx)) continue;
				if(range[temp.mark][ny][nx]) continue;
				if(shoot[temp.mark]) continue;
				range[temp.mark][ny][nx] = true;
				if(virtual[ny][nx] == 1 || virtual[ny][nx] == 3) {
					shoot[temp.mark] = true;
					virtual[ny][nx] = 3;
					continue;
				}
				q.offer(new Pos(ny,nx,temp.len+1,temp.mark));
			}
		}
		for (int i = 0; i < N-1; i++) {
			for (int j = 0; j < M; j++) {
				if(virtual[i][j] == 3) {
					virtual[i][j] = 0;
					cur ++;
				}
			}
		}
	}
	static void sol() {
		cur = 0;
		for (int l = 0; l < mat.length-1; l++) {
			proc();
			move();
		}
		rst = Math.max(rst, cur);
	}
	static void comb(int idx, int num) {
		if(num >= 3) {
			virtual = new int[N][M];
			archer = new Pos[3];
			for (int i = 0; i < mat.length; i++) {
				virtual[i] = mat[i].clone();
			}
			for (int i = 0; i < arr.length; i++) {
				virtual[N-1][arr[i]] = 2; // 궁수
				archer[i] = new Pos(N-1,arr[i],0,i);
			}
//			System.out.println(Arrays.toString(arr));
			
			sol();
			return;
		}
		for(int i=idx;i<M;i++) {
			if(v[i]) continue;
			v[i] = true;
			arr[num] = i;
			comb(i,num+1);
			v[i] = false;
		}
	}
	static int cal(Pos a, Pos b) {
		return Math.abs(a.i-b.i) + Math.abs(a.j-b.j);
	}
	static void move() {
		for (int i = N-3; i >= 0; i--) {
			for (int j = 0; j < M; j++) {
				virtual[i+1][j] = virtual[i][j];
				virtual[i][j] = 0;
			}
		}
	}
	static boolean isBound(int ny,int nx) {
		return ny>=0 && nx>=0 && ny< N && nx<M;
	}
	
}
	