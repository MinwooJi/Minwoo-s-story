import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class b6593 {
	static int L;
	static int R;
	static int C;
	static String[][] mat;
	static boolean[][][] v;
	static int dx[] = {0,1,0,-1,0,0};
	static int dy[] = {-1,0,1,0,0,0};
	static int dz[] = {0,0,0,0,-1,1}; // 아래 위
	static int minRst = 987654321;
	static class Point{
		int k;
		int i;
		int j;
		int length;
		public Point(int k, int i, int j, int length) {
			this.k = k;
			this.i = i;
			this.j = j;
			this.length = length;
		}
		public Point() {}
		
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(true) {
			minRst = 987654321;
			L = sc.nextInt(); // 층수
			R = sc.nextInt(); // 세로
			C = sc.nextInt(); // 가로
			
			if(L == 0 && R == 0 && C == 0)
				break;
			mat = new String[L][R];
			v = new boolean[L][R][C];
			for(int i=0;i<L;i++) {
				for(int j=0;j<R;j++) {
					mat[i][j] = sc.next();
				}
			}// 입력끝
			boolean breakfor = false;
			for(int k=0;k<L;k++) {
				if(breakfor)
					break;
				for(int i=0;i<R;i++) {
					if(breakfor)
						break;
					for(int j=0;j<C;j++) {
						if(mat[k][i].charAt(j)=='S') {
							bfsSearch(k,i,j);
							breakfor = true;
							break;
						}
					}
				}
			}
			if(minRst == 987654321)
				System.out.println("Trapped!");
			else
				System.out.printf("Escaped in %d minute(s).\n",minRst);
		}
	}
	static void bfsSearch(int k,int i,int j) {
		Queue<Point> q = new LinkedList<>();
		Point temp = new Point();
		v[k][i][j] = true;
		q.offer(new Point(k,i,j,0));
		while (!q.isEmpty()) {
			temp = q.poll();
			for(int l=0;l<6;l++) {
				int nz = temp.k + dz[l];
				int ny = temp.i + dy[l];
				int nx = temp.j + dx[l];
				if(temp.length+1 >= minRst)
					break;
				if(!isBound(nz,ny,nx))
					continue;
				if(v[nz][ny][nx])
					continue;
				if(mat[nz][ny].charAt(nx) == '.') {
					v[nz][ny][nx] = true;
					q.offer(new Point(nz,ny,nx,temp.length+1));
				}
				if(mat[nz][ny].charAt(nx) == 'E') {
					minRst = Math.min(minRst, temp.length+1);
					break;
				}
			}
		}
	}
	static boolean isBound(int k,int i,int j) {
		return k>=0 && i>=0 && j>=0 && k<L && i<R && j<C;
	}
}
