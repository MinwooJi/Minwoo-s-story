import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
public class swe5656 {//벽돌 꺠기
	static int N,M;
	static int K;
	static int[][] mat;
	static int[][] matC;
	static int[] set;
	static boolean[][] v;
	static int[]dy = {-1,0,1,0};
	static int[]dx = {0,1,0,-1};
	static int maxRst;
	static int zeroCnt;
	static class Pos{
		int i;
		int j;
		int breakNum;
		public Pos(int i, int j, int breakNum) {
			this.i = i;
			this.j = j;
			this.breakNum = breakNum;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		for(int t=1;t<=tc;t++) {
			K = sc.nextInt();
			M = sc.nextInt();
			N = sc.nextInt();
			mat = new int[N][M];
			matC = new int[N][M]; // 원본 저장해둘 배열
			v = new boolean[N][M];
			set = new int[K];
			maxRst = 0;
			zeroCnt = 0;
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					mat[i][j] = sc.nextInt();
					if(mat[i][j]==0)
						zeroCnt ++;
				}
			}// 입력끝
			for(int i=0;i<N;i++) {
				matC[i] = mat[i].clone();
			}
			perm(0);
			System.out.printf("#%d ",t);
			System.out.println(N*M - maxRst - zeroCnt);
		}
		
	}
	static void bfs(int[] arr) {
		int pJ;
		Queue<Pos> q = new LinkedList<>();
		Pos temp;
		int ny,nx;
		int rst = 0;
		for(int k=0;k<arr.length;k++) {
			v = new boolean[N][M];
			int pI = 0;
			pJ = arr[k];
			if(mat[N-1][pJ]==0) continue; // 끝까지 떨어졌는데 0
			while(pI < N && mat[pI][pJ]==0) {
				pI++;
			}// 아래로 떨어지면서 1인곳 pI찾음
			if(mat[pI][pJ]!=1)
				q.offer(new Pos(pI,pJ,mat[pI][pJ]));
			rst ++;
			mat[pI][pJ] = 0;
			v[pI][pJ] = true;
			while(!q.isEmpty()) {
				temp = q.poll();
				int breakNum = temp.breakNum-1;
				for(int w=1;w<=breakNum;w++) {
					for(int l=0;l<4;l++) {
						ny = temp.i + w*dy[l];
						nx = temp.j + w*dx[l];
						if(!isBound(ny,nx)) continue;
						if(mat[ny][nx]==0) continue;
						if(v[ny][nx]) continue;
						v[ny][nx] = true;
						if(mat[ny][nx]!=1)
							q.offer(new Pos(ny,nx,mat[ny][nx]));
						mat[ny][nx] = 0;
						rst++;
					}
				}
			}//1번 사이클
			falling();
		}
		maxRst = Math.max(maxRst, rst);
	}
	static void perm(int cnt) {
		if(N*M - maxRst - zeroCnt==0) return;
		if(cnt == K) {
			for(int i=0;i<N;i++) {
				mat[i] = matC[i].clone();
			}
			bfs(set);
			return;
		}
		for(int i=0;i<M;i++) {
			set[cnt] = i;
			perm(cnt+1);
		}
	}
	static void falling() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(mat[i][j]!=0) {
					if(i+1<N &&mat[i+1][j]==0) { // 아래 부서져 있음
						// 아래 몇개 떨어질지 계산
						int idx = i+1;
						int cntD = 1;
						while(isBound(idx+1,j)) {
							if(mat[++idx][j]==0) {
								cntD++;
							}else {
								break;
							}
						}
						// 위에 덩어리 크기 계산
						int cntU = 0;
						idx = i;
						while(idx-1>=0&& mat[idx-1][j]!=0) {
							cntU++;
							idx--;
						}
						// mat update
						for(int k=i;k>=i-cntU;k--) {
							mat[k+cntD][j] = mat[k][j];
							mat[k][j] = 0;
						}
					}
				}
			}
		}
	}
	static boolean isBound(int ny,int nx) {
		return ny>=0 && nx>=0 && ny<N && nx<M;
	}
}
