import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
public class b4179 {
	static int R;
	static int C;
	static char[][] mat; 
	static int[] dx = {0,1,0,-1};
	static int[] dy = {-1,0,1,0};
	static int min = Integer.MAX_VALUE;
	static class Point{
		int i;
		int j;
		int level;
		public Point(int i, int j, int level) {
			this.i = i;
			this.j = j;
			this.level = level;
		}
		public Point() {
			this(-1,-1,-1);
			// TODO Auto-generated constructor stub
		}
		@Override
		public String toString() {
			return "Point [i=" + i + ", j=" + j + ", level=" + level + "]";
		}

	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		mat = new char[R][C];
		String temp = "";
		for(int i=0;i<R;i++) {
			temp = sc.next();
			mat[i] = temp.toCharArray();
		}// 입력끝
		Point J = new Point();
		Point[] F = new Point[R*C];
		for(int i=0;i<F.length;i++) {
			F[i] = new Point();
		}
		int idx = 0;
		for(int i=0;i<mat.length;i++) {
			for(int j=0;j<mat[0].length;j++) {
				if(mat[i][j] == 'J') {
					J = new Point(i,j,0);
				}else if(mat[i][j] == 'F') {
					F[idx++] = new Point(i,j,0);
				}
			}
		}
		sol(J,F);
		if(min == Integer.MAX_VALUE)
			System.out.println("IMPOSSIBLE");
		else
			System.out.println(min);

	}
	static void sol(Point J, Point[] F) {
		boolean[][] v1 = new boolean[R][C]; // J
		boolean[][] v2 = new boolean[R][C]; // F
		Queue<Point> q1 = new LinkedList<>(); //J
		Queue<Point> q2 = new LinkedList<>(); //F
		q1.offer(J);
		for(int i=0;i<F.length;i++) {
			if(F[i].i != -1) {
				q2.offer(F[i]);
			}else 
				break;
		}
		int prevLevel = 0;
		int prevLevel2 = 0;
		Point tempJ = new Point();
		Point tempF = new Point();
		while(!q1.isEmpty()) {
			while(!q2.isEmpty()) { // 이부분 다시 보자
				if(prevLevel2 != q2.peek().level) { // 다른 레벨
					prevLevel2 = q2.peek().level;
					break;
				}
				tempF = q2.poll();
				for(int l=0;l<4;l++) {
					int ny = tempF.i + dy[l];
					int nx = tempF.j + dx[l];
					if(!isBound(nx,ny)) 
						continue;
					if(v2[ny][nx]) 
						continue;
					if(mat[ny][nx] == '.' || mat[ny][nx] == 'J') { // J경우는 아마 뺴도 될듯
						v2[ny][nx] = true;
						mat[ny][nx] = 'F';
						q2.offer(new Point(ny,nx,tempF.level+1));
					}
				}

			}

			while(!q1.isEmpty()) { 
				if(prevLevel != q1.peek().level) { // 다른 레벨
					prevLevel = q1.peek().level;
					break;
				}
				tempJ = q1.poll();
				for(int l=0;l<4;l++) {
					int ny = tempJ.i + dy[l];
					int nx = tempJ.j + dx[l];
					if(!isBound(nx,ny)) {
						min = Math.min(tempJ.level+1, min); // 밖으로 나가야하니까 +1
						continue;
					}
					if(v1[ny][nx]) 
						continue;
					if(mat[ny][nx]=='F')
						continue;
					if(mat[ny][nx] == '.' || mat[ny][nx] == 'J') { // J경우는 아마 뺴도 될듯
						v1[ny][nx] = true;
						q1.offer(new Point(ny,nx,tempJ.level+1));
					}
				}

			}
		}
	}
	static boolean isBound(int x,int y) {
		return x>=0 && y >= 0 && x<C && y<R;
	}
}