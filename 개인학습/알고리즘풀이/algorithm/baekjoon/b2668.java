import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class b2668 {
	static int[] arr;
	static int[] v;
	static int N;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		arr = new int[N];
		v = new int[N];
		for(int i=0;i<arr.length;i++)
			arr[i] = sc.nextInt()-1;
		for(int i=0;i<arr.length;i++) {
			if(v[i]!=2) 
				dfs(i);
		}
		ArrayList<Integer> list = new ArrayList<>();
		int cnt = 0;
		for(int i =0;i<v.length;i++) {
			if(v[i] == 2) {
				cnt++;
				list.add(i+1);
			}
		}
		Collections.sort(list);
		System.out.println(cnt);
		for(int i=0;i<list.size();i++) {
			System.out.println(list.get(i));
		}
	}
	static void dfs(int idx) {
		if(v[idx]==2) {
			for(int i=0;i<v.length;i++) {
				if(v[i] !=2) 
					v[i] = 0;
			}
			return;
		}
		v[idx]++;
		dfs(arr[idx]);
	}
}
