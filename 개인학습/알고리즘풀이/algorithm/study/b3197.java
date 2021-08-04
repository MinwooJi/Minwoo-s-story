import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class b3197 {
	static class Point{
		int i;
		int j;
		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
		@Override
		public String toString() {
			return "Point [i=" + i + ", j=" + j + "]";
		}
	}
	static boolean[][] v; // melting
	static boolean[][] sV;
	static StringBuffer[] sb;
	static int N;
	static int M;
	static int [] dx = {0,1,0,-1};
	static int [] dy = {-1,0,1,0};
	static boolean meet = false;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		sb = new StringBuffer[N];
		for(int i=0;i<N;i++) {
			sb[i] = new StringBuffer(sc.next());
		}// 입력끝
		Point[] swanP = new Point[2]; 
		v = new boolean[N][M];
		sV = new boolean[N][M];
		Queue<Point> q = new LinkedList<>(); // melt
		Queue<Point> nQ = new LinkedList<>();
		Queue<Point> sQ = new LinkedList<>(); // swan
		Queue<Point> sNQ = new LinkedList<>();
		Queue<Point> sQ2 = new LinkedList<>(); // swan
		Queue<Point> sNQ2 = new LinkedList<>();
		int idx = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if((sb[i].charAt(j) == '.') ) {
					v[i][j] = true;
					q.offer(new Point(i,j));
				}else if(sb[i].charAt(j) == 'L') {
					v[i][j] = true;
					q.offer(new Point(i,j));
					swanP[idx++] = new Point(i, j);
				}

			}
		} // 현재 물 다 넣음
		sQ.offer(swanP[0]);
		sb[swanP[0].i].setCharAt(swanP[0].j, 'L');
		sQ2.offer(swanP[1]);
		sb[swanP[1].i].setCharAt(swanP[1].j, 'R');
		int rst = 0;
		while(!q.isEmpty()) { // 녹이기
			meltingBFS(q,nQ);
			while(!nQ.isEmpty()) {
				q.offer(nQ.poll());
			}
			swanMoveBFS(sQ,sNQ,'L','R');
			swanMoveBFS(sQ2,sNQ2,'R','L');
//			for(int i=0;i<sb.length;i++) {
//				System.out.println(sb[i]);
//			}
//			System.out.println();
			if(meet) break;
			while(!sNQ.isEmpty()) {
				sQ.offer(sNQ.poll());
			}
			while(!sNQ2.isEmpty()) {
				sQ2.offer(sNQ2.poll());
			}
			rst++;
		}
		System.out.println(rst);
	}
	static void swanMoveBFS(Queue<Point> q,Queue<Point> nQ,char fill,char target) {
		Point temp;
		int ny,nx;
		while(!q.isEmpty() && !meet) {
			temp = q.poll();
			sb[temp.i].setCharAt(temp.j, fill);
			for(int l=0;l<4;l++) {
				ny = temp.i + dy[l];
				nx = temp.j + dx[l];
				if(!isBound(ny,nx)) continue;
				if(sb[ny].charAt(nx) == target) {
					meet = true;
					break;
				}
				if(sV[ny][nx]) continue;
				if(sb[ny].charAt(nx)=='.') {
					q.offer(new Point(ny, nx));
					sV[ny][nx] = true;
				}
				else if(sb[ny].charAt(nx)=='X') {
					sV[ny][nx] = true;
					nQ.offer(new Point(ny,nx));
				}

			}
		}
	}
	static void meltingBFS(Queue<Point> q,Queue<Point> nQ) {
		Point temp;
		int ny,nx;
		while(!q.isEmpty()) {
			temp = q.poll();
			if(sb[temp.i].charAt(temp.j)=='X') { // 처음이 아닌경우
				sb[temp.i].setCharAt(temp.j, '.');
			}
			for(int l=0;l<4;l++) {
				ny = temp.i + dy[l];
				nx = temp.j + dx[l];
				if(!isBound(ny,nx)) continue;
				if(v[ny][nx]) continue;
				if(sb[ny].charAt(nx)=='X') {
					v[ny][nx] = true;
					nQ.offer(new Point(ny,nx));
				}
			}
		}
	}
	static boolean isBound(int ny,int nx) {
		return ny>=0 && nx >= 0 && ny<N && nx <M ;
	}
}
