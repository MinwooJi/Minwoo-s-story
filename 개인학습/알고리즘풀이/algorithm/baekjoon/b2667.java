import java.util.Arrays;
import java.util.Scanner;

public class b2667 {
	static int[][] mat;
	static String[] temp;
	static int cnt = 0;
	static int N;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {-1,0,1,0};
	static boolean[][] visited;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		temp = new String[N];
		for(int i=0;i<N;i++) {
			temp[i] = new String();
		}
		for(int i=0;i<N;i++) {
			temp[i] = sc.next();
		}
		mat = new int[N][N];
		visited = new boolean[N][N];
		// 입력 끝
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				mat[i][j] = temp[i].charAt(j) - '0';
			}
		}
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(mat[i][j] != 0) {
					if(!visited[i][j])
						cnt ++;
					sol(i,j);
				}
			}
		}
		int max = 0;
		for(int i=0;i<N;i++) {
			for(int j=0; j<N;j++) {
				max = Math.max(max, mat[i][j]);
			}
		}
		int[] rst = new int[max+1];
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(mat[i][j]==0)
					continue;
				rst[mat[i][j]] +=1;
			}
		}
		Arrays.sort(rst);
		System.out.println(max);
		for(int i=1;i<rst.length;i++)
			System.out.println(rst[i]);
	}
	static void sol(int i, int j) {
		if(mat[i][j] == 0)
			return;
		if(visited[i][j])
			return;
		visited[i][j] = true;
		mat[i][j] = cnt;
		for(int l=0;l<4;l++) {
			int ny = i + dy[l];
			int nx = j + dx[l];
			if(!isBound(ny,nx) || visited[ny][nx])
				continue;
			sol(ny,nx);
			
		}
	}
	static boolean isBound(int ny,int nx) {
		return (nx>=0 && ny>=0 && nx<N && ny<N);
	}

}
