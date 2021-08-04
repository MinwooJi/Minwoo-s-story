import java.util.ArrayList;
import java.util.Scanner;

public class b1707 { // 이분 그래프
	static int N;
//	static int mat[][];
	static int markArr[];
	static boolean v[];
	static boolean rst = true;
	static ArrayList<Integer>[] listArr;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		int M;
		for(int t=0;t<tc;t++) {
			N = sc.nextInt();
			M = sc.nextInt();
//			mat = new int[N][N];// 메모리 초과원인
			listArr = new ArrayList[N];
			for(int i=0;i<N;i++) {
				listArr[i] = new ArrayList<>();
			}
			markArr = new int[N];
			v = new boolean [N];
			for(int i=0;i<M;i++) {
				int tempY = sc.nextInt()-1;
				int tempX = sc.nextInt()-1;
				listArr[tempY].add(tempX);
				listArr[tempX].add(tempY);
			} // 입력끝
			for(int i=0;i<N;i++) {
				if(v[i]) continue;
				v[i] = true;
				markArr[i] = 1;
				dfs(i,1);
				if(!rst)
					break;
			}
			if(rst)
				System.out.println("YES");
			else
				System.out.println("NO");
			rst = true;
		}
	}
	static void dfs(int now,int id) {
		if(!rst) return;
		int temp;
		for(int i=0;i<listArr[now].size();i++) {
			temp = listArr[now].get(i);
			if(v[temp]) {
				if(markArr[temp]==id) {
					rst = false;
					return;
				}else
					continue;
			}
			v[temp] = true;
			markArr[temp] = (-1)*id;
			dfs(temp,id*(-1));
		}
	}
}
