import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b14890 { // 경사로 (시뮬레이션)
	static int N,L;
	static int[][] mat;
	static int row,col;
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		mat = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				mat[i][j] = Integer.parseInt(st.nextToken());
			}// 입력끝
		}
		calcRow();
		calcCol();
		System.out.println(row+col);
	}
	static void calcRow() { // 가로
		for (int i = 0; i < N; i++) { //세로
			int cnt = 1;
			for (int j = 0; j < N-1; j++) {
				if(j==N-2 && 
						( mat[i][j] == mat[i][j+1] || 
						(mat[i][j]+1 == mat[i][j+1] && cnt >= L) || 
						(mat[i][j]-1 == mat[i][j+1] && isValid(i, j+1)) ) ) {
					row++;
					break;
				}
				if(mat[i][j] == mat[i][j+1]) {
					cnt ++;
				}
				else if(mat[i][j]+1 == mat[i][j+1] && cnt >= L) {
					cnt = 1;
				}
				else if(mat[i][j]-1 == mat[i][j+1])  {
					if(isValid(i, j+1)) {
						cnt = -(L-1);
					}else break;
				}else break;
			}
		}
	}
	static void calcCol() {
		for (int i = 0; i < N; i++) { //세로
			int cnt = 1;
			for (int j = 0; j < N-1; j++) {
				if(j==N-2 && 
						( mat[j][i] == mat[j+1][i] || 
						(mat[j][i]+1 == mat[j+1][i] && cnt >= L) || 
						(mat[j][i]-1 == mat[j+1][i] && isValid2(j+1, i)) ) ) {
					col++;
					break;
				}
				if(mat[j][i] == mat[j+1][i]) {
					cnt ++;
				}
				else if(mat[j][i]+1 == mat[j+1][i] && cnt >= L) {
					cnt = 1;
				}
				else if(mat[j][i]-1 == mat[j+1][i])  {
					if(isValid2(j+1, i)) {
						cnt = -(L-1);
					}else break;
				}else break;
			}
		}
	}
	static boolean isValid2(int i,int j) {
		int idx = i;
		for (int k = 0; k < L-1; k++) {
			if(idx+1 == N) {
				return false;
			}
			if(mat[idx][j] != mat[idx+1][j]) {
				return false;
			}
			idx++;
		}
		return true;
	}
	static boolean isValid(int i,int j) {
		int idx = j;
		for (int k = 0; k < L-1; k++) {
			if(idx+1 == N) {
				return false;
			}
			if(mat[i][idx] != mat[i][idx+1]) {
				return false;
			}
			idx++;
		}
		return true;
	}
}

