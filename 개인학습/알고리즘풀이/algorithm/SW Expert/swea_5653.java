import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class swea_5653 { // 미생물 배양 (살짝 특이한 bfs)
	static int[] dx = {0,1,0,-1};
	static int[] dy = {-1,0,1,0};
	static int N,M,K;
	static int[][] mat;
	static List<Ceil> list[];
	static class Ceil{
		int i;
		int j;
		int val;
		int forAct;
		int life;
		@Override
		public String toString() {
			return "ceil [i=" + i + ", j=" + j + ", val=" + val + ", forAct=" + forAct + ", life="
					+ life + "]";
		}
		public Ceil(int i, int j, int val, int forAct, int life) {
			this.i = i;
			this.j = j;
			this.val = val;
			this.forAct = forAct;
			this.life = life;
		}
	}

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int tc = Integer.parseInt(br.readLine());
		for (int t = 1; t <= tc; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			mat = new int[N+K][M+K];
			list = new ArrayList [10];
			for (int i = 0; i < 10; i++) {
				list[i] = new ArrayList<>();
			}
			for (int i = K/2; i < K/2+N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = K/2; j < K/2+M; j++) {
					int tmp = Integer.parseInt(st.nextToken());
					if(tmp != 0) {
						list[tmp-1].add(new Ceil(i,j,tmp,tmp,tmp));
						mat[i][j] = tmp;
					}
				
				}
			}// 입력끝
			bfs();
			int cnt = 0;
			for (int i = 0; i < mat.length; i++) {
				for (int j = 0; j < mat[0].length; j++) {
					if(mat[i][j] != 0 && mat[i][j] != -1)
						cnt++;
				}
			}
			sb.append("#").append(t).append(" ").append(cnt).append("\n");
		}
		System.out.print(sb);
	}
	static void bfs() {
		Queue<Ceil>q = new LinkedList<>();
		for (int i = 9; i >= 0; i--) {
			for (Ceil ceil : list[i]) {
				q.offer(ceil);
			}
		}
		int ny,nx,size;
		Ceil temp;
		int cnt = 0;
		while(!q.isEmpty()) {
			size = q.size();
			cnt++;
			for (int i = 0; i < size; i++) {
				temp = q.poll();
				if(temp.forAct != 0) {
					q.offer(new Ceil(temp.i, temp.j, temp.val, temp.forAct-1, temp.life));
					continue;
				}
				if(temp.life != 0) {
					for (int l = 0; l < 4; l++) {
						ny = temp.i + dy[l];
						nx = temp.j + dx[l];
						if(mat[ny][nx] == 0) {
							q.offer(new Ceil(ny, nx, temp.val, temp.val, temp.life));
							mat[ny][nx] = temp.val;
						}
					}
				}
				if(temp.life-1 != 0)
					q.offer(new Ceil(temp.i, temp.j, temp.val, temp.forAct, temp.life-1));
				else
					mat[temp.i][temp.j] = -1;
			}
			if(cnt == K) break;
		}
	}
}

























