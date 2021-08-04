import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14503_로봇청소기 { // Queue를 이용한 시뮬레이션
	static int N,M;
	static Pos now;
	static int dir; // 0 상 1 우 2 남 3 서 
	static int[] dx = {0,1,0,-1};
	static int[] dy = {-1,0,1,0};
	static int[][] mat; // 0 빈칸 1벽 2청소끝
	static int cnt;
	static Queue<Pos> q = new LinkedList<>();
	static class Pos{
		int i;
		int j;
		int dir;
		public Pos(int i, int j, int dir) {
			this.i = i;
			this.j = j;
			this.dir = dir;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		now = new Pos(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		mat = new int[N][M];
		cnt = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				mat[i][j] = Integer.parseInt(st.nextToken());
			}
		}// input end
		q.offer(now);
		int ny,nx;
		Pos temp;
		int nDir;
		boolean end = false;
		while(!q.isEmpty()) {
			if(end) break;
			temp = q.poll();
			if(mat[temp.i][temp.j] == 0) {
				cnt ++;
				mat[temp.i][temp.j] = 2;
			}
			nDir = temp.dir;
			for (int l = 0; l < 4; l++) {
				nDir = (nDir -1 + 4) % 4;
				ny = temp.i + dy[nDir];
				nx = temp.j + dx[nDir];
				if(!isBound(ny,nx)) continue;
				if(mat[ny][nx] != 0) {
					if(l != 3 ) continue;
					ny = temp.i + dy[(temp.dir -2 + 4) % 4];
					nx = temp.j + dx[(temp.dir -2 + 4) % 4];
					if(!isBound(ny, nx) || mat[ny][nx] == 1) {
						end = true;
					}else {						
						q.offer(new Pos(ny,nx,temp.dir));
					}
					break;
				}
				q.offer(new Pos(ny,nx,nDir));
				break;
			}
		}
		System.out.println(cnt);
		
	}
	static boolean isBound(int ny,int nx) {
		return ny>=0 && nx>=0 && ny<N && nx<M;
	}
}
