import java.util.Arrays;
import java.util.Scanner;

public class b11403 {
	static int mat[][];
	static int check[][];
	static int v[];
	static int cnt = 0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		mat = new int[num][num];
		check = new int[num][num];
		v = new int[num];

		for(int i=0;i<num;i++) {
			for(int j=0;j<num;j++) {
				mat[i][j] = sc.nextInt();
			}
		}// 입력끝
		int[] route;
		for(int i=0;i<mat.length;i++) {
			route = new int[num*num];
			cnt = 0;
			Arrays.fill(route, -1);
			Arrays.fill(v,0);
			dfs(route,i);
			for(int j=0;j<route.length;j++) {
				if(route[j] == -1) break;
				check[i][route[j]] = 1;
			}
		}
		for(int i=0;i<check.length;i++) {
			for(int j=0;j<check.length;j++) {
				System.out.print(check[i][j]+" ");
			}
			System.out.println();
		}

	}
	static void dfs(int[] route, int idx) {
		for(int i=0;i<mat.length;i++) {
			if(mat[idx][i] == 1 && v[i] ==0) {
				v[i] ++;
				route[cnt++] = i;
				dfs(route,i);
			}
		}
	}

}
/*
 * 
 * 10
0 0 0 1 0 0 0 0 1 1
0 0 0 0 0 0 1 0 1 1
0 0 0 0 0 0 0 0 1 1
0 0 0 0 1 1 0 0 1 1
1 0 0 0 0 0 0 0 1 1
0 0 0 0 0 0 1 0 1 1
0 0 1 0 0 0 0 0 1 1
0 0 0 0 0 0 0 0 1 1
0 0 0 0 0 0 0 0 1 1
0 0 0 0 0 0 0 0 1 1
*/
