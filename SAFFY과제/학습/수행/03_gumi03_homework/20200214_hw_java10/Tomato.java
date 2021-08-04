import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
public class Tomato {
	static int dx[] = {0,1,0,-1};
	static int dy[] = {-1,0,1,0};
	static boolean[][] v;
	static int[][] mat;
	static int M;
	static int N;
	static class Ij{
		int i;
		int j;
		int level;
		public Ij(int i, int j, int level) {
			this.i = i;
			this.j = j;
			this.level = level;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		M = sc.nextInt();
		N = sc.nextInt();
		mat = new int[N][M];
		v = new boolean[N][M];
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				mat[i][j] = sc.nextInt();
			}
		}//input end
		Bfs();
		int max = 0;
		boolean breakfor = false;
		for(int i=0;i<N;i++) {
			if(breakfor)
				break;
			for(int j=0;j<M;j++) {
				if(mat[i][j] == 0) {
					max = 0;
					breakfor =true;
					break;
				}
				max = Math.max(max, mat[i][j]);
			}
		}
		System.out.println(max-1); // 아무것도 없는거랑 비교하기위해서 1부터 시작했기떄문
	}
	static void Bfs() {
		Queue<Ij> q = new LinkedList<>();
		int cnt = 1;
		for(int i=0;i<N;i++) {
			for(int j = 0;j<M;j++) {
				if(mat[i][j] == 1) {
					q.offer(new Ij(i,j,cnt));
					v[i][j] = true;
				}
			}
		}
		while(!q.isEmpty()) {
			Ij temp = q.poll();
			mat[temp.i][temp.j] = temp.level;
			for(int l=0;l<4;l++) {
				int ny = temp.i + dy[l];
				int nx = temp.j + dx[l];
				if(!isBound(nx,ny))
					continue;
				if(v[ny][nx])
					continue;
				if(mat[ny][nx] == 0) {
					q.offer(new Ij(ny,nx,mat[temp.i][temp.j]+1));
					v[ny][nx] = true;
				}

			}
		}
	}
	static boolean isBound(int x,int y) {
		return x>=0&&y>=0&&x<M&&y<N;
	}
}
