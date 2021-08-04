import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b17070 { //파이프 옮기기 1
	static int N;
	static int[][] mat;
	static int rst;
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		mat = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				mat[i][j] = Integer.parseInt(st.nextToken());
			}
		}// 입력끝
		dfs(0,1,0); 
		System.out.println(rst);
	}
	static void dfs(int curPosY,int curPosX, int angle) { // 0가로 1세로 2 대각
		// visit 필요없을듯
		if(curPosX == N-1 && curPosY == N-1) {
			rst ++;
			return;
		}
		if(angle == 0) {
			if(isBound(curPosY,curPosX+1) && mat[curPosY][curPosX+1] == 0) {
				dfs(curPosY,curPosX+1,0); // 가로
			}
			if(isBound(curPosY+1,curPosX+1) && isBound(curPosY,curPosX+1) && isBound(curPosY + 1,curPosX)) {
				if(mat[curPosY+1][curPosX+1] == 0&& mat[curPosY][curPosX+1] == 0 && mat[curPosY+1][curPosX] == 0)
					dfs(curPosY+1,curPosX+1,2); // 대각
			}
		}else if(angle == 1) {
			if(isBound(curPosY+1,curPosX) && mat[curPosY+1][curPosX] == 0) {
				dfs(curPosY+1,curPosX,1); // 세로
			}
			if(isBound(curPosY+1,curPosX+1) && isBound(curPosY,curPosX+1) && isBound(curPosY + 1,curPosX)) {
				if(mat[curPosY+1][curPosX+1] == 0&& mat[curPosY][curPosX+1] == 0 && mat[curPosY+1][curPosX] == 0)
					dfs(curPosY+1,curPosX+1,2); // 대각
			}
		}else if(angle == 2){ // 대각
			if(isBound(curPosY,curPosX+1) && mat[curPosY][curPosX+1] == 0) {
				dfs(curPosY,curPosX+1,0); // 가로
			}
			if(isBound(curPosY+1,curPosX) && mat[curPosY+1][curPosX] == 0) {
				dfs(curPosY+1,curPosX,1); // 세로
			}
			if(isBound(curPosY+1,curPosX+1) && isBound(curPosY,curPosX+1) && isBound(curPosY + 1,curPosX)) {
				if(mat[curPosY+1][curPosX+1] == 0&& mat[curPosY][curPosX+1] == 0 && mat[curPosY+1][curPosX] == 0)
					dfs(curPosY+1,curPosX+1,2); // 대각
			}
		}
	}
	static boolean isBound(int ny,int nx) {
		return ny >= 0 && nx >= 0 && ny <N && nx < N;
	}
}
