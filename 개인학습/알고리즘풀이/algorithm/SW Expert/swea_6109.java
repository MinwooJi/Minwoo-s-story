import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea_6109 { // 추억의 2048 (단순 시뮬레이션)
	static String act;
	static int N;
	static int[][] mat;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int tc = Integer.parseInt(br.readLine());
		for (int t = 1; t <= tc; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			act = st.nextToken();
			mat = new int[N][N];
			for (int i = 0; i < mat.length; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < mat[0].length; j++) {
					mat[i][j] = Integer.parseInt(st.nextToken());
				}
			}// 입력끝
			switch(act) {
				case "up":
					up();
					break;
				case "down":
					down();
					break;
				case "left":
					left();
					break;
				case "right":
					right();
					break;
				
			}
			System.out.println("#"+t);
			for (int i = 0; i < mat.length; i++) {
				for (int j = 0; j < mat[0].length; j++) {
					System.out.print(mat[i][j] + " ");
				}
				System.out.println();
			}
		}
	}
	static void up() {
		for (int i = 0; i < mat.length; i++) {
			int regIdx = 0;
			for (int j = 0; j < mat[0].length; j++) {
				if(j==regIdx) continue;
				if(mat[j][i] == 0) continue;
				if(mat[regIdx][i] == 0) {
					mat[regIdx][i] = mat[j][i];
					mat[j][i] = 0;
					continue;
				}
				if(mat[j][i] == mat[regIdx][i]) {
					mat[regIdx][i] *= 2;
					mat[j][i] = 0;
				}else {
					mat[regIdx+1][i] = mat[j][i];
					if(regIdx+1 != j) mat[j][i] = 0;
				}
				regIdx ++;
			}
		}
	}
	static void down() {
		for (int i = 0; i < mat.length; i++) {
			int regIdx = mat.length-1;
			for (int j = mat[0].length-1; j >=0 ; j--) {
				
				if(j==regIdx) continue;
				if(mat[j][i] == 0) continue;
				if(mat[regIdx][i] == 0) {
					mat[regIdx][i] = mat[j][i];
					mat[j][i] = 0;
					continue;
				}
				if(mat[j][i] == mat[regIdx][i]) {
					mat[regIdx][i] *= 2;
					mat[j][i] = 0;
				}else {
					mat[regIdx-1][i] = mat[j][i];
					if(regIdx-1 != j) mat[j][i] = 0;
				}
				regIdx --;
			}
		}
	}
	static void left() {
		for (int i = 0; i < mat.length; i++) {
			int regIdx = 0;
			for (int j = 0; j < mat[0].length; j++) {
				if(j==regIdx) continue;
				if(mat[i][j] == 0) continue;
				if(mat[i][regIdx] == 0) {
					mat[i][regIdx] = mat[i][j];
					mat[i][j] = 0;
					continue;
				}
				if(mat[i][j] == mat[i][regIdx]) {
					mat[i][regIdx] *= 2;
					mat[i][j] = 0;
				}else {
					mat[i][regIdx+1] = mat[i][j];
					if(regIdx+1 != j) mat[i][j] = 0;
				}
				regIdx ++;
			}
		}
	}
	static void right() {
		for (int i = 0; i < mat.length; i++) {
			int regIdx = mat.length-1;
			for (int j = mat[0].length-1; j >=0 ; j--) {
				
				if(j==regIdx) continue;
				if(mat[i][j] == 0) continue;
				if(mat[i][regIdx] == 0) {
					mat[i][regIdx] = mat[i][j];
					mat[i][j] = 0;
					continue;
				}
				if(mat[i][j] == mat[i][regIdx]) {
					mat[i][regIdx] *= 2;
					mat[i][j] = 0;
				}else {
					mat[i][regIdx-1] = mat[i][j];
					if(regIdx-1 != j) mat[i][j] = 0;
				}
				regIdx --;
			}
		}
	}
}
