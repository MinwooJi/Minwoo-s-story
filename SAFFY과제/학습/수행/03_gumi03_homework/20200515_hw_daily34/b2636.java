import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class b2636 { // 치즈 (bfs + 다른큐에 다음 진행할것 저장해두기)
	static int[][] mat;
	static boolean[][] v;
	static int hour;
	static int qSize;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {-1,0,1,0};
	static int N,M;
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
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		mat = new int[N][M];
		for (int i = 0; i < mat.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < mat[0].length; j++) {
				mat[i][j] = Integer.parseInt(st.nextToken());
			}
		}// 입력끝
		sol();
		System.out.println(hour);
		System.out.println(qSize);
	}
	static void sol() {
		Queue<Pos>q = new LinkedList<>();
		Queue<Pos>temp = new LinkedList<>();
		v = new boolean[N][M];
		int ny,nx;
		Pos p;
		v[0][0] = true;
		temp.offer(new Pos(0,0));
		q.offer(new Pos(0,0));
		hour = 0;
		boolean start = true;
		while(!temp.isEmpty()) {
			if(!start) {
				qSize = temp.size();
				for (Pos pos : temp) {
					mat[pos.i][pos.j] = 0;
					q.offer(pos);
				}
				hour++;
			}else{
				start = false;
			}
			temp.clear();
			while(!q.isEmpty()) {
				p = q.poll();
				for (int l = 0; l < 4; l++) {
					ny = p.i + dy[l];
					nx = p.j + dx[l];
					if(!isBound(ny,nx)) continue;
					if(v[ny][nx]) continue;
					v[ny][nx] = true;
					if(mat[ny][nx] == 0) {
						q.offer(new Pos(ny,nx));
					}else if(mat[ny][nx] == 1) {
						temp.offer(new Pos(ny,nx));
					}
				}
			}
		}
		
	}
	static boolean isBound(int ny,int nx) {
		return ny>=0 && nx>= 0 && ny< N && nx< M;
	}
}
