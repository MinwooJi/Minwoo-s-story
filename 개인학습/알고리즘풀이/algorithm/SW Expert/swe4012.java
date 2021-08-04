import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class swe4012 { // 요리사
	static int N;
	static int rstA,rstB;
	static int[][] mat;
	static int[] A,B,r;
	static int minRst = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= tc; t++) {
			N = Integer.parseInt(br.readLine());
			mat = new int[N][N];
			A = new int[N/2];
			B = new int[N/2];
			r = new int[2];
			minRst = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					mat[i][j] = Integer.parseInt(st.nextToken());
				}
			}// 입력끝
			comb(0,0,0);
			sb.append(String.format("#%d %d\n", t,minRst));
		}
		System.out.print(sb);
	}
	static void comb2(int idx,int cnt,int[] arr,char ch) {
		if(cnt == 2) {
			if(ch == 'A') {
				rstA += (mat[r[0]][r[1]] + mat[r[1]][r[0]]);
			}else {
				rstB += (mat[r[0]][r[1]] + mat[r[1]][r[0]]);
			}
			return ;
		} // 메모리제이션 쓸까말까?
		for(int i=idx;i<arr.length;i++) {
			r[cnt] = arr[i];
			comb2(i+1, cnt+1, arr, ch);
		}
	}
	static void comb(int idx,int cnt,int bitMask) {
		if(cnt == (N/2)){
			int aCnt = 0, bCnt = 0;
			for (int i = 0; i < N; i++) {
				if((bitMask & 1<<i) != 0) {
					A[aCnt++] = i;
				}else {
					B[bCnt++] = i;
				}
			}
			rstA = 0; rstB = 0;
			comb2(0,0,A,'A');
			comb2(0,0,B,'B');
			minRst = Math.min(minRst,Math.abs(rstA - rstB));
			return;
		}
		for (int i = idx; i < N; i++) {
			comb(i+1,cnt+1,bitMask | 1<< i);
		}
	}
}
