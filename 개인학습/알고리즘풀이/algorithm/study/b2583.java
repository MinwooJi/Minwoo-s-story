import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class b2583 {
	static int M;
	static int N;
	static int[][] mat;
	static boolean[][] v;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {-1,0,1,0};
	static int cnt;
	static class Point{
		int j;
		int i;
		public Point(int j, int i) {
			this.j = j;
			this.i = i;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		M = sc.nextInt();
		N = sc.nextInt();
		mat = new int[M][N];
		v = new boolean[M][N];
		int K = sc.nextInt();
		Point[][] arr = new Point[K][2];
		for(int i=0;i<K;i++) {
			arr[i][0] = new Point(sc.nextInt(),sc.nextInt()); // 왼쪽아래
			arr[i][1] = new Point(sc.nextInt(),sc.nextInt()); // 오른쪽위
		}
		for(int l=0;l<arr.length;l++) {
			for(int i=arr[l][0].i;i<arr[l][1].i;i++) {
				for(int j=arr[l][0].j;j<arr[l][1].j;j++) {
					mat[i][j] = 1;
				}
			}
		}// 사전작업 끝
		int areaCnt = 0;
		ArrayList<Integer> list = new ArrayList<>();
		for(int i=0;i<mat.length;i++) {
			for(int j=0;j<mat[0].length;j++) {
				if(!v[i][j] && mat[i][j]==0) {
					cnt = 1;
					areaCnt++;
					v[i][j] = true;
					dfs(i,j);
					list.add(cnt);
				}
			}
		}
		Collections.sort(list);
		System.out.println(areaCnt);
		for(int i=0;i<list.size();i++) {
			System.out.print(list.get(i)+ " ");
		}
		System.out.println();
	}
	static void dfs(int i,int j) {
		for(int l=0;l<4;l++) {
			int ny = i + dy[l];
			int nx = j + dx[l];
			if(!isBound(ny,nx)) continue;
			if(v[ny][nx]) continue;
			if(mat[ny][nx] == 0) {
				cnt++;
				v[ny][nx] = true;
				dfs(ny,nx);
			}

		}
	}
	static boolean isBound(int ny,int nx) {
		return ny>=0 && nx >=0 && ny<M && nx<N;
	}
}
