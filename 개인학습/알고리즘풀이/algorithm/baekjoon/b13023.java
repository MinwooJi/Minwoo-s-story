import java.util.ArrayList;
import java.util.Scanner;

public class b13023 { // ABCDE 
	static int N,M;
	static boolean v[];
	static boolean flag = false;
	static ArrayList<Integer> list[];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		v = new boolean[N];
		list = new ArrayList[N];
		for(int i=0;i<list.length;i++) {
			list[i] = new ArrayList<>();
		}
		int a,b;
		for(int i=0;i<M;i++) {
			a = sc.nextInt();
			b = sc.nextInt();
			list[a].add(b);
			list[b].add(a);
		} // 양방향 그래프 입렦끝
		for(int i=0;i<N;i++) {
			v[i] = true;
			dfs(i,0);
			v[i] = false;
			if(flag) break;
		}
		if(flag) {
			System.out.println(1);
		}else {
			System.out.println(0);
		}
	}
	static void dfs(int now ,int cnt) {
		if(cnt == 4) {
			flag = true;
			return;
		}
		if(flag) return;
		for(int i=0;i<list[now].size();i++) {
			if(v[list[now].get(i)]) continue;
			v[list[now].get(i)] = true;
			dfs(list[now].get(i),cnt+1);
			v[list[now].get(i)] = false;
		}
	}
}
