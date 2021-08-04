import java.util.Scanner;
public class b1012 {
	static int[][] mat;
	static int[][]where;
	static int dx[] = {0,1,0,-1}; // 12 3 6 9시부터 시계방향
	static int dy[] = {-1,0,1,0};
	static int rst = 0;
	static int idx = 1;
	static boolean[][] visited;
	static int width;
	static int heigt;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		for(int t=0;t<tc;t++) {
			width = sc.nextInt();
			heigt = sc.nextInt();
			int num = sc.nextInt();
			mat = new int[heigt][width];
			visited = new boolean[heigt][width];
			where = new int[num][2];
			for(int i=0;i<num;i++) {
				where[i][1] = sc.nextInt(); // j
				where[i][0] = sc.nextInt(); // i
			}// 입력 끝
			for(int i=0;i<num;i++) {
				mat[ where[i][0] ][ where[i][1] ] = -1; // 배추
			}
//			 초기화 끝
			for(int i=0;i<heigt;i++) {
				for(int j=0;j<width;j++) {
					if(mat[i][j] == -1 && !visited[i][j]) {
						sol(i,j);
						idx ++;
					}
				}
			}
			System.out.println(idx-1);
			idx = 1;
		}
	}
	static void sol(int i,int j) {
		if(visited[i][j] == true)
			return;
		visited[i][j] = true;
		mat[i][j] = idx;
		for(int l=0;l<4;l++) {
			int ny = i + dy[l];
			int nx = j + dx[l];
			if(!isBound(ny,nx) || mat[ny][nx] != -1 ||visited[ny][nx])
				continue;
			mat[ny][nx] = idx;
			sol(ny,nx);
		}
	}
	static boolean isBound(int ny,int nx) {
		return ny>=0 && nx >=0 && ny< heigt && nx <width;
	}
}
