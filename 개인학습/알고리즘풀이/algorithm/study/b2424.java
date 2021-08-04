import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class b2424 {
	static StringBuffer[] sb;
	static boolean v[][];
	static boolean v2[][];
	static int [][]mat ;
	static int [][]pirate ;
	static Queue<Point> q = new LinkedList<>();
	static int N;
	static int M;
	static boolean rst = false;
	static int dx[] = {0,1,0,-1};
	static int dy[] = {-1,0,1,0};
	static class Point{
		int i;
		int j;
		int level;
		public Point(int i, int j, int level) {
			super();
			this.i = i;
			this.j = j;
			this.level = level;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N =sc.nextInt();
		M =sc.nextInt();
		mat = new int[N][M];
		pirate = new int[N][M];
		v = new boolean[N][M];
		v2 = new boolean[N][M];
		sb = new StringBuffer[N];
		for(int i=0;i<N;i++) {
			sb[i] = new StringBuffer(sc.next());
		}// 입력끝
		Point[] p= new Point[2];
		int cnt = 0;
		outer:
			for(int i =0; i<N;i++) {
				for(int j=0;j<M;j++) {
					if(cnt == 2) break outer;
					if(sb[i].charAt(j)=='V') {
						p[0] = new Point(i,j,0);
						cnt ++;
					}else if(sb[i].charAt(j)=='Y') {
						p[1] = new Point(i,j,0);
						cnt ++;
					}else if(sb[i].charAt(j)=='T') {
					}
				}
			}
		pirateSight(p[0]);
		solBfs(p[1]);
		if(rst)
			System.out.println("YES");
		else
			System.out.println("NO");

	}
	static void solBfs(Point p) {
		int ny,nx;
		Point temp;
		Queue<Point> q = new LinkedList<>();
		v[p.i][p.j] = true;
		q.offer(p);
		while(!q.isEmpty()) {
			temp = q.poll();
			for(int l=0;l<4;l++) {
				ny = temp.i + dy[l];
				nx = temp.j + dx[l];
				if(!isBound(ny,nx)) continue;
				if(v[ny][nx]) continue;
				if(pirate[ny][nx] <= temp.level+1) continue;
//				if(sb[ny].charAt(nx) == 'I') continue;
				if(sb[ny].charAt(nx) == 'T') {
					rst = true;
					return;
				}
				v[ny][nx] = true;
				q.offer(new Point(ny,nx,temp.level+1));
			}
		}
	}
	static void pirateSight(Point p) {
		pirateBfs(p);
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				pirate[i][j] = mat[i][j];
			}
		}// 왼쪽부터 밀고 오른쪽으로 돌아오기 -> 상하
		int min = Integer.MAX_VALUE;
		for(int i=0;i<N;i++) {
			min = Integer.MAX_VALUE;
			for(int j=0;j<M;j++) {
				if(mat[i][j] == -1) { // 벽
					min = Integer.MAX_VALUE;
					continue;
				}
				min = Math.min(min, mat[i][j]);
				pirate[i][j] = min;
			}
			min = Integer.MAX_VALUE;
			for(int j=M-1;j>=0;j--) {
				if(mat[i][j] == -1) {
					min = Integer.MAX_VALUE;
					continue;
				}
				min = Math.min(min, mat[i][j]);
				if(min>pirate[i][j]) continue;
				pirate[i][j] = min;
			}
		}
		
		for(int j=0;j<M;j++) {
			min = Integer.MAX_VALUE;
			for(int i=0;i<N;i++) {
				if(mat[i][j]==-1) {
					min = Integer.MAX_VALUE;
					continue;
				}
				min = Math.min(min, mat[i][j]);
				if(min>pirate[i][j]) continue;
				pirate[i][j] = min;
			}
			min = Integer.MAX_VALUE;
			for(int i=N-1;i>=0;i--) {
				if(mat[i][j]==-1) {
					min = Integer.MAX_VALUE;
					continue;
				}
				min = Math.min(min, mat[i][j]);
				if(min>pirate[i][j]) continue;
				pirate[i][j] = min;
			}
		}
	}
	static void pirateBfs(Point p) {
		int ny,nx;
		Point temp;
		v2[p.i][p.j] = true;
		mat[p.i][p.j] = p.level;
		Queue<Point> q = new LinkedList<>();
		q.offer(p);
		while(!q.isEmpty()) {
			temp = q.poll();
			for(int l=0;l<4;l++) {
				ny = temp.i + dy[l];
				nx = temp.j + dx[l];
				if(!isBound(ny,nx)) continue;
				if(v2[ny][nx]) continue;
				if(sb[ny].charAt(nx) == 'I') {
					mat[ny][nx] = -1;
					continue;
				}
				v2[ny][nx] = true;
				mat[ny][nx] = temp.level+1;
				q.offer(new Point(ny,nx,temp.level+1));
			}
		}
	}
	static boolean isBound(int ny,int nx) {
		return ny>=0 && nx >=0 && ny < N && nx < M;
	}
}