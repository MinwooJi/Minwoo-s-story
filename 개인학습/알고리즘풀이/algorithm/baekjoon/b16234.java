import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class b16234 { // 인구이동 (bfs, 시뮬레이션)
	static int mat[][];
	static int matArea[][];
	static boolean v[][];
	static int[] areaVal;
	static int numbering;
	static int num;
	static int sum;
	static int dx[] = {0,1,0,-1};
	static int dy[] = {-1,0,1,0};
	static int N,L,R;
	static class Pos{
		int i;
		int j;
		public Pos(int i, int j) {
			this.i = i;
			this.j = j;
		}
		public String toString() {
			return "Pos [i=" + i + ", j=" + j + "]";
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		mat = new int[N][N];
//		Arrays.fill(areaVal, -1);
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				mat[i][j] = Integer.parseInt(st.nextToken());
			}
		}// 입력끝
		int cnt = 0;
		while(numbering-1 != N*N) {
			numbering = 1;
			matArea = new int[N][N];
			v = new boolean[N][N];
			areaVal = new int[N*N+1];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(v[i][j]) continue;
					v[i][j] = true;
					sum = mat[i][j];
					num = 1;
					bfs(new Pos(i,j));
					areaVal[numbering++] = sum/num;
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					mat[i][j] = areaVal[matArea[i][j]];
				}
			}
			cnt++;
		}
//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(matArea[i]));
//		}
//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(mat[i]));
//		}
//		System.out.println();
//		System.out.println(Arrays.toString(areaVal));
		System.out.println(cnt-1);
	}
	static void bfs(Pos p) {
		int ny,nx;
		Pos temp;
		Queue<Pos> q = new LinkedList<>();
		q.offer(p);
		matArea[p.i][p.j] = numbering;
		while(!q.isEmpty()) {
			temp = q.poll();
			for (int l = 0; l < 4; l++) {
				ny = temp.i + dy[l];
				nx = temp.j + dx[l];
				if(!isBound(ny, nx)) continue;
				if(v[ny][nx]) continue;
				if(Math.abs(mat[temp.i][temp.j]-mat[ny][nx]) >= L && Math.abs(mat[temp.i][temp.j]-mat[ny][nx]) <= R) {
					v[ny][nx] = true;
					matArea[ny][nx] = numbering;
					num ++;
					sum += mat[ny][nx];
					q.offer(new Pos(ny,nx));
				}
			}
		}
	}
	static boolean isBound(int ny,int nx) {
		return ny>=0 && nx>=0 && ny<N && nx<N;
	}
}
