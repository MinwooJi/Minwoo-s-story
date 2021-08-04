import java.util.Scanner;

public class djS2 {
	static int N;
	static int M;
	static int[] arr;
	static disjointSet[] dSet;
	static class disjointSet{
		int func;
		int a;
		int b;
		public disjointSet(int func, int a, int b) {
			this.func = func;
			this.a = a;
			this.b = b;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		arr = new int[N+1]; // 0버림
		dSet = new disjointSet[M];
		for(int i=0;i<M;i++) {
			dSet[i] = new disjointSet(sc.nextInt(), sc.nextInt(), sc.nextInt());
		}
		MakeSet();
		for(int i=0;i<M;i++) {
			if(dSet[i].func==0) {
				UnionSet(dSet[i].a, dSet[i].b);
			}
			else {
				if(findSet(dSet[i].a) == findSet(dSet[i].b)) {
					System.out.println("YES");
				}
				else
					System.out.println("NO");
			}
		}
	}
	static void MakeSet() {
		for(int i=0;i<arr.length;i++) {
			arr[i] = i;
		}
	}
	static int findSet(int idx) {
		if(idx == arr[idx])
			return idx;
		return arr[idx] = findSet(arr[idx]);
	}
	static void UnionSet(int x, int y) {
		x = findSet(x);
		y = findSet(y);
		if(x==y)
			return;
		arr[y] = x;
	}
}

