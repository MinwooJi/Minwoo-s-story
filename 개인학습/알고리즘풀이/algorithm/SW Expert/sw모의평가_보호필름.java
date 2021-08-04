import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class sw모의평가_보호필름 { // 모의swtest 보호필름 (순열 조합)
	static int D,W,K;
	static int[][] mat;
	static int[][] matC;
	static int[] set;
	static boolean[] v;
	static int target;
	static int rst;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int tc = Integer.parseInt(br.readLine());
		for (int t = 1; t <= tc; t++) {
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			rst = 0;
			mat = new int[D][W];
			matC = new int[D][W];
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					mat[i][j] = Integer.parseInt(st.nextToken());
				}
			}// 입력끝
			
			if(K==1) {
				sb.append("#").append(t).append(" ").append(0).append("\n");
				continue;
			}
			for (int k = 0; k < mat.length; k++) {
				matC[k] = mat[k].clone();
			}
			if(check()) {
				sb.append("#").append(t).append(" ").append(0).append("\n");
				continue;
			}
			
			for (int i = 1; i <= D; i++) {
				if(rst != 0) break;
				set = new int[i];
				v = new boolean[13];
				target = i;
				comb(0,0);
			}
			sb.append("#").append(t).append(" ").append(rst).append("\n");
		}
		System.out.print(sb);
	}
	static boolean check() {
		int cnt;
		boolean flag;
		for (int i = 0; i < W; i++) {
			cnt = 1;
			flag = false;
			for (int j = 0; j < D-1; j++) {
				if(cnt == K) {
					flag = true;
					break;
				}
				if(matC[j][i] == matC[j+1][i]) {
					cnt ++;
				}else {
					cnt = 1;
				}
			}
			if(cnt == K) continue;
			if(!flag) return false;
		}
		return true;
	}
	static void perm(int cnt) {
		int val;
		for (int i = 0; i < 1<<cnt; i++) {
			for (int k = 0; k < mat.length; k++) {
				matC[k] = mat[k].clone();
			}
			for (int j = 0; j < cnt; j++) {
				if((i & (1<<j) ) == 0) val = 0;
				else val = 1;
				for (int j2 = 0; j2 < W; j2++) {
					matC[ set[j] ][j2] = val;
				}
//				System.out.println();
			}
//			for (int[] js : matC) {
//				System.out.println(Arrays.toString(js));
//			}
//			System.out.println();
			if(check()) {
				rst = cnt;
				return;
			}
		}
	}
	static void comb(int idx, int cnt) {
		if(rst != 0) return;
		if(cnt == target) {
//			System.out.println(Arrays.toString(set));
//			if(cnt == 2 && set[0] == 2 && set[1] == 5)
//				System.out.println("check");
			perm(cnt);
			return;
		}
		for (int i = idx; i < D; i++) {
			set[cnt] = i;
			comb(i+1,cnt+1);
		}
	}
}
