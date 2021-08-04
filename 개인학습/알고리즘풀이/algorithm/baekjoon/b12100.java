import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class b12100 { // 2048 (easy) // 중복순열 + 단순 시뮬레이션
	static int N;
	static int[][] mat;
	static int[][] matC;
	static int[] set;
	static int rst = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		set = new int[5];
		N = Integer.parseInt(br.readLine());
		mat = new int[N][N];
		matC = new int[N][N];
		for (int i = 0; i < mat.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < mat[0].length; j++) {
				mat[i][j] = Integer.parseInt(st.nextToken());
			}
		}// 입력끝
		for (int i = 0; i < mat.length; i++) {
			matC[i] = mat[i].clone();
		}
		perm(0);
		sol();
		System.out.println(rst);
	}
	static void sol() {
		for (int i = 0; i < set.length; i++) {
			switch(set[i]) {
			case 0:
				up();
				break;
			case 1:
				down();
				break;
			case 2:
				left();
				break;
			case 3:
				right();
				break;
			}
		}
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				if(mat[i][j] > rst)
					rst = mat[i][j];
			}
		}
	}
	static void perm(int cnt) { // 중복 순열
		if(cnt == 5) {
			for (int i = 0; i < mat.length; i++) {
				mat[i] = matC[i].clone();
			}

			sol();
			return;
		}
		for (int i = 0; i < 4; i++) {
			set[cnt] = i;
			perm(cnt+1);
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
