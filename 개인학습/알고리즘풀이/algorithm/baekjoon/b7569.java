import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class b7569 {
	static int M;
	static int N;
	static int H;
	static int[][][] mat;
	static boolean[][][] v;
	static int dx[] = {0,1,0,-1,0,0};
	static int dy[] = {-1,0,1,0,0,0};
	static int dz[] = {0,0,0,0,1,-1};
	static class Point{
		int z;
		int i;
		int j;
		@Override
		public String toString() {
			return "Point [z=" + z + ", i=" + i + ", j=" + j + "]";
		}
		public Point(int z, int i, int j) {
			super();
			this.z = z;
			this.i = i;
			this.j = j;
		}
		
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);	
		M =sc.nextInt();
		N = sc.nextInt();
		H = sc.nextInt();
		mat = new int[H][N][M];
		v = new boolean[H][N][M];
		ArrayList<Point> p = new ArrayList<>();
		for(int k=0;k<H;k++) {
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					mat[k][i][j] = sc.nextInt();
					if(mat[k][i][j] == 1) {
						p.add(new Point(k,i,j));
					}
				}
			}
		}//입력끝
		int rst = bfs(p);
		outer :
		for(int k=0;k<H;k++) {
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					if(mat[k][i][j] == 0) {
						rst = -1;
						break outer;
					}
				}
			}
		}//입력끝
		System.out.println(rst);
	}
	static int bfs(ArrayList<Point> p) {
		Point temp;
		Queue<Point>q = new LinkedList<>();
		int ny,nx,nz;
		int level = 0;
		for(int i=0;i<p.size();i++) {
			q.offer(p.get(i));
		}
		while(!q.isEmpty()) {
			int size = q.size();
			for(int k=0;k<size;k++) {
				temp = q.poll();
				for(int l=0;l<6;l++) {
					ny = temp.i + dy[l];  
					nx = temp.j + dx[l];  
					nz = temp.z + dz[l];
					if(!isBound(ny,nx,nz)) continue;
					if(v[nz][ny][nx]) continue;
					if(mat[nz][ny][nx] == -1) continue;
					if(mat[nz][ny][nx] == 1) continue;
					v[nz][ny][nx] = true;
					q.offer(new Point(nz,ny,nx));
					mat[nz][ny][nx] = 1;
					
				}
			}
			level ++;
		}
		return level-1;
	}
	static boolean isBound(int ny,int nx,int nz) {
		return ny >=0 && nx >=0 && nz>=0 && nx<M && ny <N && nz < H;
	}
}
