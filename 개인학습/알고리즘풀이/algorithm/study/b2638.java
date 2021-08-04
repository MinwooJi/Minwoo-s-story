import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
public class b2638 {
	static int N;
	static int M;
	static int[][] mat;
	static int[][] out; // 공기에 노출된 부분 check
	static int[] dx = {0,1,0,-1};
	static int[] dy = {-1,0,1,0};
	static LinkedList<Point> list = new LinkedList<>(); // 없어질 좌표 저장
	static boolean v[][];
	static int flagCnt = 0;
	
	static class Point{
		int i;
		int j;
		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		mat = new int[N][M];
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				mat[i][j] = sc.nextInt();
			}
		}// 입력끝
		int cnt = 0;
		flagCnt = 1;
		while(flagCnt != 0) {
			out = new int[N][M];
			v = new boolean[N][M];
			out[0][0] = 2;
			flagCnt = 0;
			dfsInnerSide(0, 0);
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					if(!v[i][j] && mat[i][j] == 1) {
						bfsMelting(new Point(i,j));
						flagCnt++;
					}
				}
			}
			Point temp;
			while(!list.isEmpty()) {
				temp = list.poll();
				mat[temp.i][temp.j] = 0;
			}
			cnt ++;
		}
		System.out.println(cnt-1); // 전부다 지웠을때 한번더 반복함
	}
	static void bfsMelting(Point p) {
		Queue<Point> q = new LinkedList<>();
		Point temp;
		int ny,nx;
		int tempY,tempX;
		q.offer(p);
		int cnt = 0;
		for(int k=0;k<4;k++) {
			ny = p.i + dy[k];
			nx = p.j + dx[k];
			if(!isBound(ny,nx)) continue;
			if(out[ny][nx] == 2) {
				cnt++;
			}
			if(cnt >=2) { // 2개이상 노출되었다면
				list.offer(new Point(p.i,p.j));
				break;
			}
		}
		v[p.i][p.j] = true;
		while(!q.isEmpty()) {
			temp = q.poll();
			for(int l=0;l<4;l++) {
				ny = temp.i + dy[l];
				nx = temp.j + dx[l];
				if(!isBound(ny,nx)) continue;
				if(v[ny][nx]) continue;
				v[ny][nx] = true;
				q.offer(new Point(ny,nx));
				// 녹을 것인지 확인
				tempY = ny;
				tempX = nx;
				cnt = 0;
				for(int k=0;k<4;k++) {
					ny = tempY + dy[k];
					nx = tempX + dx[k];
					if(!isBound(ny,nx)) continue;
					if(out[ny][nx] == 2) {
						cnt++;
					}
					if(cnt >=2) { // 2개이상 노출되었다면
						list.offer(new Point(tempY,tempX));
						break;
					}
				}
			}
		}
	}
	static void dfsInnerSide(int i,int j) { // 외부 노출 확인
		int ny,nx;
		for(int l=0;l<4;l++) {
			ny = i + dy[l];
			nx = j + dx[l];
			if(!isBound(ny,nx)) continue;
			if(out[ny][nx] == 0 && mat[ny][nx] == 0) {
				out[ny][nx] = 2;
				dfsInnerSide(ny, nx);
			}
		}
	}
	static boolean isBound(int ny,int nx) {
		return ny>=0 && nx>=0 && nx<M && ny<N;
	}
}