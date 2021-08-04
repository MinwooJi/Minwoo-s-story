import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b2468 { // 안전영역 (dfs)
	static int[][] mat;
	static int[][] temp;
	static boolean[][] visited;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {-1,0,1,0};
	static int N;
	static int num; 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		mat = new int[N][N];
		int rst = 0;
		int maxHeigt = 0;
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				mat[i][j] = Integer.parseInt(st.nextToken());
				maxHeigt = Math.max(maxHeigt, mat[i][j]);
			}
		}
		//입력끝
		for(int l=0;l<=maxHeigt;l++) { 
			temp = new int[N][N];
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(mat[i][j] <= l) { // 침수
						temp[i][j] = -1;
					}
				}
			}
			visited = new boolean[N][N];
			num = 1;
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(temp[i][j] != -1 && !visited[i][j]) {
						visited[i][j] = true;
						temp[i][j] = num;
						sol(i,j);
						num++;
					}
				}
			}
			rst = Math.max(rst, num-1);
		}
		System.out.println(rst);
	}
	static void sol(int i,int j) {
		int ny,nx;
		for(int l=0;l<4;l++) {
			ny = i + dy[l];
			nx = j + dx[l];
			
			if(!isBound(ny,nx)) continue;
			if(visited[ny][nx]) continue;
			if(temp[ny][nx] == -1) continue;
			visited[ny][nx] = true;
			temp[ny][nx] = num;
			sol(ny,nx);
		}
	}
	static boolean isBound(int ny,int nx) {
		return ny>=0 && nx>=0 && ny<N && nx<N;
	}
}
