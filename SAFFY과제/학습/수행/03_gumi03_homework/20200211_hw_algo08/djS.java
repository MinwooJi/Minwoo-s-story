import java.util.Scanner;

public class djS {
	static int N;
	static int M;
	static int[] arr;
	static disjointSet[] dSet;
	static class disjointSet{
		int func; // 0은 합연산 1은 같은 집합인지 확인
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
		int tc = sc.nextInt();
		for(int t=0;t<tc;t++) {
			N = sc.nextInt();
			M = sc.nextInt();
			arr = new int[N+1]; // 0번 버림
			dSet = new disjointSet[M]; // 연산
			for(int i=0;i<M;i++) {
				dSet[i] = new disjointSet(sc.nextInt(), sc.nextInt(), sc.nextInt());
			}// 입력끝
			makeSet();
			int tempA;
			int tempB;
			System.out.printf("#%d ",t+1);
			for(int i=0;i<M;i++) {
				if(dSet[i].func == 0) { // 합치기
					unionSet(dSet[i].a,dSet[i].b);
				}else if(dSet[i].func == 1) { // 확인
					tempA = findSet(dSet[i].a);
					tempB = findSet(dSet[i].b);
					if(tempA == tempB)
						System.out.print(1);
					else
						System.out.print(0);
				}
			}
			System.out.println();
		}
	}
	static void unionSet(int x, int y) {
		x = findSet(x);
		y = findSet(y);
		if(x==y)
			return;
		arr[y] = x;
	}
	static int findSet(int idx) {
		if(idx == arr[idx])
			return idx;
		return arr[idx] = findSet(arr[idx]);
	}
	static void makeSet() {
		for(int i=1;i<arr.length;i++) {
			arr[i] = i;
		}
	}
}
