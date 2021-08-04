import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class b11404 {// 플로이드
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int n = Integer.parseInt(br.readLine());
		int bus = Integer.parseInt(br.readLine());
		int[][] mat = new int[n][n];
		for (int i = 0; i < mat.length; i++) {
			Arrays.fill(mat[i], Integer.MAX_VALUE);
		}
		int s,e,v;
		for (int i = 0; i < bus; i++) {
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken())-1;
			e = Integer.parseInt(st.nextToken())-1;
			v = Integer.parseInt(st.nextToken());
			if(mat[s][e] > v)
				mat[s][e] = v;
		}// 입력끝
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				if(i == k) continue;
				for (int j = 0; j < n; j++) {
					if( j == k || j == i) continue;
					if( mat[i][k] != Integer.MAX_VALUE && mat[k][j] != Integer.MAX_VALUE && 
							mat[i][j] > mat[i][k] + mat[k][j]) {
						mat[i][j] = mat[i][k] + mat[k][j];
					}
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(mat[i][j] == Integer.MAX_VALUE) {
					mat[i][j] = 0;
				}
				sb.append(mat[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
