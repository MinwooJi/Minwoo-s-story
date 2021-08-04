import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
public class b1600_수정 { // 말이되고싶은 언숭이 - 재채점 부분 수정
	static boolean [][][]v;
	static int [][] mat;
	static int N,M,K;
	static int [] dx = {0,1,0,-1};
	static int [] dy = {-1,0,1,0};
	static int [] hdx = {1,2,2,1,-1,-2,-2,-1};
	static int [] hdy = {-2,-1,1,2,2,1,-1,-2};
	static int minRst = Integer.MAX_VALUE;
	static class Pos{
		int i;
		int j;
		int apCnt;
		public Pos(int i, int j, int apCnt) {
			this.i = i;
			this.j = j;
			this.apCnt = apCnt;
		}
		public String toString() {
			return "Pos [i=" + i + ", j=" + j + ", apCnt=" + apCnt + "]";
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		mat = new int[N][M];
		v = new boolean[K+1][N][M];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				mat[i][j] = Integer.parseInt(st.nextToken());
			}
		}// 입력끝
		if(M== 1 && N ==1) { // 자기자신이 도착지가 될경우 추가
			System.out.println(mat[0][0]==0?0:-1);
		}else {
			bfs();
			System.out.println(minRst = minRst==Integer.MAX_VALUE? -1: minRst);
		}
	}
	private static void bfs() {
		Queue<Pos> q = new LinkedList<>();
		q.offer(new Pos(0,0,0));
		v[0][0][0] = true;
		int ny,nx,level=1;
		Pos temp;
		while(!q.isEmpty()) {
			int cnt = q.size();
			for(int c = 0;c<cnt;c++) {
				temp = q.poll();
				if(temp.apCnt < K) { // 능력 쓸수있음
					for(int l=0;l<8;l++) {
						ny = temp.i + hdy[l];
						nx = temp.j + hdx[l];
						if(!isBound(ny,nx)) continue;
						if(v[temp.apCnt+1][ny][nx]) continue;
						if(mat[ny][nx] == 1) continue;
						if(ny == N-1 && nx == M-1) {
							minRst = level;
							return;
						}
						v[temp.apCnt+1][ny][nx] = true;
						q.offer(new Pos(ny,nx,temp.apCnt+1));
					}
				}
				for(int l=0;l<4;l++) {
					ny = temp.i + dy[l];
					nx = temp.j + dx[l];
					if(!isBound(ny,nx)) continue;
					if(v[temp.apCnt][ny][nx]) continue;
					if(mat[ny][nx] == 1) continue;
					if(ny == N-1 && nx == M-1) {
						minRst = level;
						return;
					}
					v[temp.apCnt][ny][nx] = true;
					q.offer(new Pos(ny,nx,temp.apCnt));
				}
			}
			level++;
		}
	}
	static boolean isBound(int ny,int nx) {
		return ny>=0 && nx>=0 && ny< N && nx<M;
	}
}
