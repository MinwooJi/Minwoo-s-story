import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class swea_1953 { // 모의sw역량테스트 탈주범검거(bfs)
	static int[] dx = {0,1,0,-1};
	static int[] dy = {-1,0,1,0};
	static int rst;
	static int[][] mat;
	static boolean[][] v;
	static int N,M;
	static class Pos{
		int i;
		int j;
		int life;
		public Pos(int i, int j, int life) {
			this.i = i;
			this.j = j;
			this.life = life;
		}
		
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder() ;
		int tc = Integer.parseInt(br.readLine());
		int startI,startJ,life;
		Pos start;
		for (int t = 1; t <= tc; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			startI = Integer.parseInt(st.nextToken());
			startJ = Integer.parseInt(st.nextToken());
			life = Integer.parseInt(st.nextToken())-1;
			start = new Pos(startI,startJ,life);
			rst = 0;
			mat = new int[N][M];
			v = new boolean[N][M];
			for (int i = 0; i < mat.length; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < mat[0].length; j++) {
					mat[i][j] = Integer.parseInt(st.nextToken());
				}
			}// 입력끝
			bfs(start);
			sb.append("#").append(t).append(" ").append(rst).append("\n");
		}
		System.out.print(sb);
	}
	static boolean check(int val,int dir) {
		switch(val) {
		case 2:
			if(dir==1 || dir==3) return false;
			break;
		case 3:
			if(dir==0 || dir==2) return false;
			break;
		case 4:
			if(dir==2 || dir==3) return false;
			break;
		case 5:
			if(dir==0 || dir==3) return false;
			break;
		case 6:
			if(dir==0 || dir==1) return false;
			break;
		case 7:
			if(dir==1 || dir==2) return false;
			break;
		}
		return true;
	}
	static void bfs(Pos s) {
		Queue<Pos>q = new LinkedList<>();
		int ny,nx;
		Pos temp;
		q.offer(s);
		v[s.i][s.j] = true;
		rst ++;
		while(!q.isEmpty()) {
			temp = q.poll();
			if(mat[temp.i][temp.j] != 0 && temp.life > 0) {
				for (int l = 0; l < dx.length; l++) {
					if(!check(mat[temp.i][temp.j],l)) continue;
					ny = temp.i + dy[l];
					nx = temp.j + dx[l];
					if(!isBound(ny,nx)) continue;
					if(v[ny][nx]) continue;
					if(mat[ny][nx] == 0) continue;
					if(!check(mat[ny][nx],(l+2)%4)) continue;
					v[ny][nx] = true;
					rst ++;
					q.offer(new Pos(ny,nx,temp.life-1));
				}
			}
		}
	}
	static boolean isBound(int ny,int nx) {
		return ny>=0 && nx>= 0 && ny<N && nx < M; 
	}
}
