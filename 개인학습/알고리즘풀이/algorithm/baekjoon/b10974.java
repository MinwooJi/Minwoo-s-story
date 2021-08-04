import java.util.Scanner;
public class b10974 {
	static int N;
	static int[] map;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N];
		int[] data = new int[N];
		for(int i=0;i<map.length;i++) {
			map[i] = i+1;
		}
		boolean[] v = new boolean[N];
		perm(v,data,0);
	}
	static void perm(boolean[]v,int[] data, int level) {
		if(level == N) {
			for(int i=0;i<data.length;i++) {
				System.out.print(data[i] + " ");
			}
			System.out.println();
			return;
		}
		for(int i=0;i<map.length;i++) {
			if(v[i])
				continue;
			v[i] = true;
			data[level] = map[i];
			perm(v,data,level+1);
			data[level] = 0;
			v[i] = false;
		}
	}
}