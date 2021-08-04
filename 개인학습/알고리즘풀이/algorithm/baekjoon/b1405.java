import java.util.Scanner;

public class b1405 {
	static int num;
	static double[] probability = new double[4];
	static boolean[][] mat = new boolean[28+1][28+1];
	static double rst = 0;
	static int[] dx = {1,-1,0,0}; // 동서남북
	static int[] dy = {0,0,1,-1};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		num = sc.nextInt();
		for(int i=0;i<4;i++) {
			probability[i] = sc.nextInt()/(double)100;
		}
		mat[14][14] = true;
		dfs(14,14,0,1.0);
//		System.out.println(String.format("%.9f", rst));
		System.out.println(rst);
	}
	static void dfs(int y, int x ,int idx,double prob) {
		if(num == idx) {
			rst += prob;
			return;
		}
		for(int l=0;l<4;l++) {
			int ny = y + dy[l];
			int nx = x + dx[l];
			if(!mat[ny][nx]) {
				mat[ny][nx] = true;
				dfs(ny,nx,idx+1,prob*probability[l]);
				mat[ny][nx] = false;
			}
		}
	}
}
