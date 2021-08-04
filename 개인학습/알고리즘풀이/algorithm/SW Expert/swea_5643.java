import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class swea_5643 { // 키순서 (dfs이용한 시뮬레이션)
	static int N,M;
	static List<Integer>biggerThan[];
	static List<Integer>smallerThan[];
	static boolean v[][];
	static Pair[] pArr;
	static class Pair{
		int a;
		int b;
		public Pair(int a, int b) {
			this.a = a;
			this.b = b;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int tc = Integer.parseInt(br.readLine().trim());
		for (int t = 1; t <= tc; t++) {
			N = Integer.parseInt(br.readLine().trim());
			M = Integer.parseInt(br.readLine().trim());
			biggerThan = new ArrayList[N];
			smallerThan = new ArrayList[N];
			pArr = new Pair[M];
			v = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				biggerThan[i] = new ArrayList<>();
				smallerThan[i] = new ArrayList<>();
			}//init
			int a,b;
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine().trim());
				a = Integer.parseInt(st.nextToken())-1;
				b = Integer.parseInt(st.nextToken())-1;
				pArr[i] = new Pair(a,b);
			}// 입력끝
			for (int i = 0; i < pArr.length; i++) {
				if(!v[pArr[i].a][pArr[i].b] && !v[pArr[i].b][pArr[i].a]) {
					v[pArr[i].a][pArr[i].b] = true;
					v[pArr[i].b][pArr[i].a] = true;
					biggerThan[pArr[i].a].add(pArr[i].b);
					smallerThan[pArr[i].b].add(pArr[i].a);
					dfs(pArr[i].a,pArr[i].b);
				}
			}
			int rst = 0;
			for (int i = 0; i < N; i++) {
				if(biggerThan[i].size() + smallerThan[i].size() == N-1) {
					rst++;
				}
			}
			sb.append("#").append(t).append(" ").append(rst).append("\n");
		}
		System.out.println(sb);
	}
	static void dfs(int a,int b) { // 작은수 큰수
		// b보다 큰수
		int mb;
		for (int i = 0; i < biggerThan[b].size(); i++) {
			mb = biggerThan[b].get(i);
			if(v[mb][a]) continue;
			if(v[a][mb]) continue;
			v[mb][a] = v[a][mb] = true;
			biggerThan[a].add(mb);
			smallerThan[mb].add(a);
			dfs(a,mb);
		}
		// a보다 작은수
		int la;
		for (int i = 0; i < smallerThan[a].size(); i++) {
			la = smallerThan[a].get(i);
			if(v[la][b]) continue;
			if(v[b][la]) continue;
			v[la][b] = v[b][la] = true;
			smallerThan[b].add(la);
			biggerThan[la].add(b);
			dfs(la,b);
		}
	}
}
