import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class b3055 {
	static int N;
	static int M;
	static StringBuffer[] sb;
	static boolean v[][];
	static boolean vWater[][];
	static int dx[] = {0,1,0,-1};
	static int dy[] = {-1,0,1,0};
	static int rst = Integer.MAX_VALUE;
	static ArrayList<Point> list = new ArrayList<>();
	static class Point{
		int i;
		int j;
		int id;
		@Override
		public String toString() {
			return "Point [i=" + i + ", j=" + j + ", id=" + id + "]";
		}
		public Point(int i, int j, int id) {
			super();
			this.i = i;
			this.j = j;
			this.id = id;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		vWater = new boolean[N][M];
		v = new boolean[N][M];
		sb = new StringBuffer[N];
		for(int i=0;i<N;i++) {
			sb[i] = new StringBuffer(sc.next());
		}// 입력끝
		Point start = null;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(sb[i].charAt(j)=='S') {
					start = new Point(i,j,0);
				}else if(sb[i].charAt(j)=='*') {
					list.add(new Point(i,j,1));
				}

			}
		}
		bfs(start);
		if(rst == Integer.MAX_VALUE)
			System.out.println("KAKTUS");
		else
			System.out.println(rst);
	}
	static void bfs(Point start) {
		int ny,nx;
		Point temp;
		int min = 0;
		Queue<Point> q = new LinkedList<>();
		for(int i=0;i<list.size();i++) {
			q.offer(list.get(i));
			vWater[list.get(i).i][list.get(i).j] = true;
		}
		q.offer(start);
		v[start.i][start.j] = true;
		while(!q.isEmpty()) {
			int size = q.size();
			for(int i=0;i<size;i++) {
				temp = q.poll();
				if(temp.id == 1) { // 물
					for(int l=0;l<4;l++) {
						ny = temp.i + dy[l];
						nx = temp.j + dx[l];
						if(!isBound(ny,nx)) continue;
						if(vWater[ny][nx]) continue;
						if(sb[ny].charAt(nx) == 'X') continue;
						if(sb[ny].charAt(nx) == 'D') continue;
						vWater[ny][nx] = true;
						sb[ny].setCharAt(nx, '*');
						q.offer(new Point(ny,nx,temp.id));
					}
				}else if(temp.id == 0) {
					for(int l=0;l<4;l++) {
						ny = temp.i + dy[l];
						nx = temp.j + dx[l];
						if(!isBound(ny,nx)) continue;
						if(v[ny][nx]) continue;
						if(sb[ny].charAt(nx) == 'X') continue;
						if(sb[ny].charAt(nx) == '*') continue;
						if(sb[ny].charAt(nx) == 'D') {
							rst = min+1;
							return;
						}
						v[ny][nx] = true;
						sb[ny].setCharAt(nx, 'S');
						q.offer(new Point(ny,nx,temp.id));
					}
				}
			}
			min ++;
		}
	}
	static boolean isBound(int ny,int nx) {
		return nx >= 0 && ny >= 0 && ny <N && nx <M;
	}
}
