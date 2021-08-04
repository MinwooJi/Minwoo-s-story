import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class b2206 {
	static int N;
	static int M;
	static char[][] mat;
	static int min= 987654321;
	static boolean[][] v; // 벽부순적 있는지?
	static int dx[] = {0,1,0,-1};
	static int dy[] = {-1,0,1,0};
	static class Point{
		int i;
		int j;
		int length;
		boolean breakwall;
		public Point(int i, int j, int length,boolean breakWall) {
			this.i = i;
			this.j = j;
			this.length = length;
			this.breakwall = breakWall;
		}
		public Point() {}

	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		mat = new char[N][M];
		v = new boolean[N][M];
		String[] temp  = new String[N];
		for(int i=0;i<N;i++) {
			temp[i] = sc.next();
			mat[i] = temp[i].toCharArray();
		}
		bfs();
		if(min == 987654321)
			min = -1;
		System.out.println(min);

	}
	static void bfs() { // 길찾기
		Queue<Point> q = new LinkedList<>();
		boolean[][] visit = new boolean[N][M];
		boolean[][] visit2 = new boolean[N][M];
		Point temp = new Point();
		visit[0][0] = true;
		q.offer(new Point(0,0,1,false));
		while (!q.isEmpty()) {
			temp = q.poll();
			if(temp.i == N-1 && temp.j== M-1) { // 도착
				min = Math.min(min, temp.length);
			}

			for(int l=0;l<4;l++) {
				int ny = temp.i + dy[l];
				int nx = temp.j + dx[l];
				if(!isBound(nx,ny))
					continue;
				if(visit[ny][nx])
					continue;
				if(temp.length >= min)
					continue;
				if(!temp.breakwall) { // 벽 아직 부순적없고 통로를 만났을떄
					if(mat[ny][nx] == '0') {
						visit[ny][nx] = true;
						q.offer(new Point(ny,nx,temp.length+1,temp.breakwall));
					}else { // 부순다
						visit2[ny][nx] = true; // 이제부턴 visit2로 사용
						q.offer(new Point(ny,nx,temp.length+1,true));
					}
				}else if(mat[ny][nx] == '0'&& !visit2[ny][nx]){ // 벽을 전에 부순경우
					visit2[ny][nx] = true;
					q.offer(new Point(ny,nx,temp.length+1,temp.breakwall));
				}
			}
		}
	}
	static boolean isBound(int x,int y) {
		return x>=0 && y>=0 && x<M && y<N;
	}
}
