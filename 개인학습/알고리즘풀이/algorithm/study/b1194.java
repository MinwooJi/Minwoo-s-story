import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class b1194 {
	static class Point{
		int i;
		int j;
		int level;
		int bitMask;
		public Point(int i, int j, int level, int bitMast) {
			super();
			this.i = i;
			this.j = j;
			this.level = level;
			this.bitMask = bitMast;
		}
	}
	static boolean[][][] v; // 키먹을때마다 다르게 사용할것
	static StringBuffer[] sb;
	static int N;
	static int M;
	static int rst = Integer.MAX_VALUE;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {-1,0,1,0};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		sb = new StringBuffer[N];
		
		v = new boolean[63 + 1][N][M]; // 0abcdef 먹었을때
		for(int i=0;i<N;i++) {
			sb[i] = new StringBuffer(sc.next());
		}// 입력끝
		Point start = null;
		outerLoop:
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(sb[i].charAt(j)=='0') {
					start = new Point(i,j,0,0);
					break outerLoop;
				}
			}
		}
		v[0][start.i][start.j] = true;
		bfs(start);
		if(rst == Integer.MAX_VALUE)
			rst = -1;
		System.out.println(rst);
		
	}
	static void bfs(Point start) {
		Queue<Point> q = new LinkedList<>();
		int ny,nx;
		Point temp;
		q.offer(start);
		while(!q.isEmpty()) {
			temp = q.poll();
			for(int l=0;l<4;l++) {
				ny = temp.i + dy[l];
				nx = temp.j + dx[l];
				if(!isBound(ny,nx)) continue;
				if(sb[ny].charAt(nx) == '#') continue;
				if(sb[ny].charAt(nx) == '1') { // 도착
					rst = Math.min(rst, temp.level+1);
					return;
				}
				if(sb[ny].charAt(nx) == '.' || sb[ny].charAt(nx) == '0') {
					if(v[temp.bitMask][ny][nx]) continue;
					v[temp.bitMask][ny][nx] = true;
					q.offer(new Point(ny,nx,temp.level+1,temp.bitMask));
				}else if(sb[ny].charAt(nx)>='a' && sb[ny].charAt(nx)<='f') {//열쇠먹기
					if((temp.bitMask & 1<<(int)(sb[ny].charAt(nx) - 'a')) != 0) {//이미 먹은열쇠
						if(v[temp.bitMask][ny][nx]) continue;
						v[temp.bitMask][ny][nx] = true;
						q.offer(new Point(ny,nx,temp.level+1,temp.bitMask));
					}else {//먹은적 없음
						int bMask = temp.bitMask | 1<<(int)(sb[ny].charAt(nx) - 'a');
						if(v[bMask][ny][nx]) continue;
						if(v[temp.bitMask][ny][nx]) continue;
						v[temp.bitMask][ny][nx] = true;
						v[bMask][ny][nx] = true;
						q.offer(new Point(ny,nx,temp.level+1,bMask));
					}
				}else if(sb[ny].charAt(nx)>='A' && sb[ny].charAt(nx)<='F') {
					if((temp.bitMask & 1<<(int)(sb[ny].charAt(nx) - 'A')) == 0 ) { // 열쇠가 없다
						continue;
					}else {
						if(v[temp.bitMask][ny][nx]) continue;
						v[temp.bitMask][ny][nx] = true;
						q.offer(new Point(ny,nx,temp.level+1,temp.bitMask));
					}
				}
			}
		}
		
	}
	static boolean isBound(int ny,int nx) {
		return ny>=0 && nx>=0 && ny<N && nx < M;
	}
}