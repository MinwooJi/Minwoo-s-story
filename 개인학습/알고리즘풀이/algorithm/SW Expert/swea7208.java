import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class swea7208 { // 지도칠하기 (시뮬레이션 + 중복순열 + dfs)
	static int N;
	static boolean v[];
	static int[][] adj;
	static int[] color;
	static int con;
	static int[] colorCandidate;
	static List<Integer>conNodeIdx;
	
	static int minCnt;
	static int rst;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int tc = Integer.parseInt(br.readLine());
		for (int t = 1; t <= tc; t++) {
			N = Integer.parseInt(br.readLine());
			adj = new int[N][N];
			color = new int[N];
			v = new boolean[N];
			rst = 0;
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < color.length; i++) {
				color[i] = Integer.parseInt(st.nextToken())-1;
			}// 색
			for (int i = 0; i < adj.length; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < adj[0].length; j++) {
					adj[i][j] = Integer.parseInt(st.nextToken());
				}
			}// 입력끝
			
			for (int i = 0; i < N; i++) {
				if(v[i]) continue;
				con = 0; // 연결된 수
				v[i] = true;
				conNodeIdx = new ArrayList<>();
				conNodeIdx.add(i);
				findConnect(i); // 연결관계찾기
				
				colorCandidate = new int[conNodeIdx.size()]; // 색깔 가능경우의수
				if(con != 0) {
					minCnt = Integer.MAX_VALUE;
					perm(0);
					if(minCnt != Integer.MAX_VALUE)
						rst += minCnt;
				}
				
			}
			sb.append("#").append(t).append(" ").append(rst).append("\n");
		}
		System.out.print(sb);
	}
	static int count() {
		int cnt = 0;
		for (int i = 0; i < colorCandidate.length; i++) {
			if(colorCandidate[i] != color[i]) cnt++;
		}
		return cnt;
	}
	static void perm(int cnt) {
		if(cnt == colorCandidate.length) {
			for (int j = 0; j < conNodeIdx.size(); j++) {
				if(!check(conNodeIdx.get(j))) {
					return;
				}
			}
			// 색몇개 바꿔야하는지?
			minCnt = Math.min(minCnt, count());
			return;
		}
		for (int i = 0; i < 4; i++) {
			colorCandidate[cnt] = i;
			perm(cnt+1);
		}
	}
	static boolean check(int start) {
		for (int i = 0; i < conNodeIdx.size(); i++) {
			if(adj[start][conNodeIdx.get(i)] == 0) continue;
			if(colorCandidate[start] == colorCandidate[conNodeIdx.get(i)]) return false;
		}
		return true;
	}
	static void findConnect(int idx) {
		for (int i = 0; i < adj.length; i++) {
			if(!v[i] && adj[idx][i] == 1) {
				v[i] = true;
				con++;
				conNodeIdx.add(i);
				findConnect(i);
			}
		}
	}
}
