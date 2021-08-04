import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b15684 { // 사다리 조작
	static int N,H,M;
	static boolean connect[][];
	static int check[][];
	static int minRst = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		connect = new boolean[H+1][N+1]; // 0버림
		check = new int[H+1][N+1]; // 0버림
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			connect[a][b] = true;
			check[a][b] = 1;
		} // 입력끝
		sol(0,1);
		if(minRst == Integer.MAX_VALUE) minRst = -1;
		System.out.println(minRst);
	}
	static void sol(int cnt,int idx) {
		if(cnt > 3) return;
		if(go()) {
			minRst = Math.min(minRst, cnt);
			return;
		}
		for (int i = idx; i < connect.length; i++) {
			for (int j = 1; j < connect[0].length-1; j++) {
				if(connect[i][j]) continue;
				if(connect[i][j-1]) continue;
				if(connect[i][j+1]) continue;
				connect[i][j] = true;
				check[i][j] = 2;
				sol(cnt+1,i);
				check[i][j] = 0;
				connect[i][j] = false;
			}
		}
	}
	static boolean go() {
		for(int i=1;i<connect[0].length;i++) {
			int idx = i;
			for (int j = 0; j <connect.length; j++) {
				if(connect[j][idx]) idx += 1;
				else if(connect[j][idx-1]) idx -=1;
			}
			if(i != idx) return false;
		}
		return true;
	}
}
