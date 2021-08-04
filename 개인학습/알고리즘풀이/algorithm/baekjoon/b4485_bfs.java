import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class b4485_bfs { // 녹색 옷 입은 애가 젤다지? 
	static int[][] dist;
	static int[][] mat;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {-1,0,1,0};
	static int N;
	static boolean[][] v;
	static class Pos{
		int i;
		int j;
		int val;
		public Pos(int i, int j, int val) {
			this.i = i;
			this.j = j;
			this.val = val;
		}
		@Override
		public String toString() {
			return "Pos [i=" + i + ", j=" + j + ", val=" + val + "]";
		}
		
	}
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int cnt = 1;
		while(true) {
			N = Integer.parseInt(br.readLine());
			if(N == 0) break;
			
			dist = new int[N][N];
			for (int i = 0; i < dist.length; i++) {
				Arrays.fill(dist[i], 140625);
			}// 최대 값으로 초기화
			mat = new int[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					mat[i][j] = Integer.parseInt(st.nextToken());
				}
			}// 입력 끝
			bfs();
			sb.append("Problem ").append(cnt++).append(": ").append(dist[N-1][N-1]).append("\n");
		}
		System.out.println(sb);
		
	}
	static void bfs() {
		Pos temp;
		int ny,nx;
		Queue<Pos> q = new LinkedList<>();
		q.offer(new Pos(0,0,mat[0][0]));
		dist[0][0] = mat[0][0];
		while(!q.isEmpty()) {
			temp = q.poll();
			if(dist[temp.i][temp.j] < temp.val) continue;
			for (int l = 0; l < 4; l++) {
				ny = temp.i + dy[l];
				nx = temp.j + dx[l];
				if(!isBound(ny,nx)) continue;
				if(dist[ny][nx] <= temp.val + mat[ny][nx]) continue;
				dist[ny][nx] = temp.val + mat[ny][nx];
				q.offer(new Pos(ny,nx,dist[ny][nx]));
			}
		}
	}
	static boolean isBound(int ny,int nx) {
		return ny>=0 && nx >=0 && nx< N && ny< N;
	}
}
