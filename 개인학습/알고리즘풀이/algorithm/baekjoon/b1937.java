import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class b1937 { // 욕심쟁이 판다 dfs-dp 로 먼저 해보자
	static int[] dx = {0,1,0,-1};
	static int[] dy = {-1,0,1,0};
	static int[][] memo;
	static int[][] mat;
	static int N,rst=0;
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		mat = new int[N][N];
		memo = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				mat[i][j] = Integer.parseInt(st.nextToken());
			}
		} // 입력끝
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(memo[i][j] == 0) {
					rst = Math.max(rst, dfs(i,j)+1);
				}
			}
		}
		System.out.println(rst);
	}
	static int dfs(int curY,int curX) {
		if(memo[curY][curX]!=0) {
			return memo[curY][curX];
		}
		for(int l=0;l<4;l++) {
			if(!isBound(curY+dy[l],curX+dx[l])) continue;
			if(mat[curY+dy[l]][curX+dx[l]] > mat[curY][curX]) {
				memo[curY][curX] = Math.max(memo[curY][curX], dfs(curY+dy[l],curX+dx[l]) + 1);
			}
		}
		return memo[curY][curX];
	}
	static boolean isBound(int ny,int nx) {
		return ny>=0 && nx>=0 && ny<N && nx< N;
	}
}
