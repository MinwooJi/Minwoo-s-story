import java.util.Scanner;
public class Religion {
	static int n;
	static int m;
	static int[] arr;
	static int[][] mat;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		arr = new int [n+1]; // 0버림
		mat = new int[m][2];
		for(int i=0;i<m;i++) {
			mat[i][0] = sc.nextInt();
			mat[i][1] = sc.nextInt();
		}
		makeSet();
		for(int i=0;i<m;i++) {
			union(mat[i][0],mat[i][1]);
		}
		int cnt = 0;
		for(int i=1;i<arr.length;i++) {
			if(arr[i] == i)
				cnt++;
		}
		System.out.println(cnt);
	}
	static void makeSet() {
		for(int i=1;i<arr.length;i++) {
			arr[i] = i;
		}
	}
	static int findSet(int idx) {
		if(arr[idx] == idx)
			return idx;
		return arr[idx] = findSet(arr[idx]);
	}
	static void union(int x,int y) {
		x = findSet(x);
        y = findSet(y);
        if(x==y)
            return;
        arr[y] = x;
	}
}
