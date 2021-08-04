import java.util.Scanner;

public class b11724 {
	static int mat[][];
	static boolean v[];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		mat = new int[N][N];
		v = new boolean[N];
		for(int i=0;i<M;i++) {
			int tempY = sc.nextInt();
			int tempX = sc.nextInt();
			mat[tempY-1][tempX-1] = 1;
			mat[tempX-1][tempY-1] = 1; // 무방향 그래프
		}// 입력끝
		int cnt = 0;
		for(int i=0;i<mat.length;i++) {
			if(!v[i]) {
				cnt++;
				dfs(i);
			}
		}
		System.out.println(cnt);
		
	}
	static void dfs(int idx) {
		v[idx] = true;
		for(int i=0;i<mat.length;i++) {
			if(mat[idx][i]==1 && !v[i]) {
				dfs(i);
			}
		}
	}
}