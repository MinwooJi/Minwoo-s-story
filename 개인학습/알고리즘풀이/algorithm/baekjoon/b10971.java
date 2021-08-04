import java.util.Scanner;

public class b10971 {//외판원 순회2
	static int N;
	static int[][] mat;
	static int minRst = Integer.MAX_VALUE;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		mat = new int[N][N];
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				mat[i][j] = sc.nextInt();
			}
		}// 입력끝
		for(int i=0;i<N;i++) {
			int visit = 0;
			dfs(1,i,i,0,visit|1<<i);
		}
		System.out.println(minRst);
	}
	static void dfs(int cnt,int start,int now,int curVal,int visit) {
		if(cnt == N) {
			if(mat[now][start]==0) return;
			minRst = Math.min(minRst, curVal+mat[now][start]);
			return;
		}
		if(curVal >= minRst) return;
		for(int i=0;i<N;i++) {
			if((visit & (1<<i)) != 0) continue;
			if(mat[now][i]==0) continue;
			dfs(cnt+1,start,i,curVal + mat[now][i],(visit | (1<<i)));
		}
	}
}
