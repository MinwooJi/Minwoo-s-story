import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class b1197 { // 최소스패닝 트리 (크루스칼)
	static int V,E;
	static int [] parent;
	static int [] rank;
	static class Pos{
		int start;
		int end;
		int val;
		public Pos(int start, int end, int val) {
			this.start = start;
			this.end = end;
			this.val = val;
		}
		
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		V = sc.nextInt();
		E = sc.nextInt();
		parent = new int[V];
		rank = new int[V];
		Pos p[] = new Pos[E] ;
		for (int i = 0; i < E; i++) {
			p[i] = new Pos(sc.nextInt()-1,sc.nextInt()-1,sc.nextInt());
		}
		Arrays.sort(p,new Comparator<Pos>() {
			@Override
			public int compare(Pos arg0, Pos arg1) {
				// TODO Auto-generated method stub
				return Integer.compare(arg0.val, arg1.val);
			}
		});
		for (int i = 0; i < V; i++) {
			makeSet(i);
		}
		int cnt = 0;
		int result = 0;
		for (int i = 0; i < E; i++) {
			int a = findSet(p[i].start);
			int b = findSet(p[i].end);
			if(a == b) continue;
			union(a,b);
			cnt++;
			result += p[i].val;
			if(cnt == V-1) break;
			
		}
		System.out.println(result);
	}
	static void makeSet(int x) {
		parent[x] = x;
	}
	static int findSet(int x) {
		if(parent[x] == x) {
			return x;
		}
		return parent[x] = findSet(parent[x]);
	}
	static void union(int x,int y) {
		x = findSet(x);
		y = findSet(y);
		if(x==y) return;
		if(rank[x] < rank[y]) {
			parent[x] = y;
		}else {
			parent[y] = x;
			if(rank[y] == rank[x]) rank[x]++;
		}
	}
}
