import java.util.Scanner;
public class b1389 {
	static int[][] mat;
	static int min = Integer.MAX_VALUE;
	static int rstI = 0;
	static int M;
	static int N;
	static boolean[] v;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		mat = new int[N+1][N+1]; // 0버림
		v = new boolean[N+1];
		int tempY,tempX;
		int kevin[] = new int[N+1];
		for(int i=0;i<M;i++) {
			tempY = sc.nextInt();
			tempX = sc.nextInt();
			mat[tempX][tempY] = 1;
			mat[tempY][tempX] = 1; // 무방향 그래프
		}
		for(int i=1;i<mat.length;i++) {
			rstI = 0;
			for(int j=1;j<mat.length;j++) {
				if(i==j) continue;
				v = new boolean[N+1];
				min = Integer.MAX_VALUE;
				v[i] = true;
				dfs(i,j,0);
				rstI += min;
			}
			kevin[i] = rstI;
		}
		min = Integer.MAX_VALUE;
		int rst = 0;
		for(int i=1;i<kevin.length;i++) {
			if(min>kevin[i]) {
				min = kevin[i];
				rst = i;
			}
		}
		System.out.println(rst);
	}
	static void dfs(int idx,int target,int num) { //타고들어갈꺼/ 목표 // 타고들어간 횟수
		if(target == idx) {
			min = Math.min(min, num);
			return;
		}
		for(int i =1;i<mat.length;i++) {
			if(idx == i) continue;
			if(mat[idx][i]==1 && !v[i]) {
				v[i] = true;
				dfs(i,target,num+1);
				v[i] = false;
			}
		}
	}
}
