import java.util.LinkedList;
import java.util.Scanner;

public class b14502 {
	static class Position{
		int x;
		int y;
		public Position(int x,int y)
		{
			this.x = x;
			this.y = y;
		}
		public Position() {
			
		}
	}
	static int[] dx = {-1,+1,0,0};
	static int[] dy = {0,0,-1,+1};
	static int heigt;
	static int width;
	static int[][] mat;
	static int[][] copyMat;
	static int wallCount = 0;
	static int rst = 0;
	static LinkedList<Position> Point = new LinkedList<Position>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		heigt = sc.nextInt();
		width = sc.nextInt();
		mat = new int[heigt][width];
		copyMat = new int[heigt][width];
		for(int i=0;i<heigt;i++) {
			for(int j=0;j<width;j++) {
				mat[i][j] = sc.nextInt();
				if(mat[i][j]==2)
					Point.offer(new Position(j,i));
			}
		}
		sol();
		System.out.println(rst);

	}
	public static void sol() {
		if(wallCount > 3)
			return;
		if(wallCount == 3) {
			int cnt=0;
			Copy();
			for(int i=0;i<Point.size();i++) {
				Poison(Point.get(i).x,Point.get(i).y);
			}
			for(int i=0;i<heigt;i++) {
				for(int j=0;j<width;j++) {
					if(copyMat[i][j]==0)
						cnt++;
				}
			}
			if(rst<cnt)
				rst = cnt;
		}
		for(int i=0;i<heigt;i++) {
			for(int j=0;j<width;j++) {
				if(mat[i][j]==0) {
					wallCount += 1;
					mat[i][j] = 1;
					sol();
					wallCount -= 1;
					mat[i][j] = 0;
				}
			}
		}
	}
	public static void Poison(int x, int y) {
		for(int i=0;i<4;i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx>=0&&nx<width&&ny>=0&&ny<heigt) {
				if(copyMat[ny][nx]==0) {
					copyMat[ny][nx] = 2;
					Poison(nx,ny);
				}
			}
				
			
		}
		
	}
	public static void Copy() {
		for(int i=0;i<heigt;i++) {
			for(int j=0;j<width;j++){
				copyMat[i][j] = mat[i][j];
			}
		}
	}

}
