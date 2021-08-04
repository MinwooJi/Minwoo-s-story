import java.util.Scanner;
public class BreadHouse {
	static int R;
	static int C;
	static int[] dy = {-1,0,1};
	static int[] dx = {1,1,1};
	static boolean[][] v ;
	static StringBuffer[] mat;
	static int cnt = 0;
	static boolean flag;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		v = new boolean[R][C];
		mat = new StringBuffer[R];
		for(int i=0;i<R;i++) {
			mat[i] = new StringBuffer(sc.next());
		}
		for(int i=0;i<R;i++) {
			flag = false;
			dfs(0,i);
		}
		System.out.println(cnt);
	}
	static void dfs(int x,int y) {
		if(x==C-2) {
			cnt++;
			flag = true;
			return ;
		}
		for(int l=0;l<3;l++) {
			int ny = y +dy[l];
			int nx = x +dx[l];
			if(!isBound(nx,ny))
				continue;
			if(v[ny][nx])
				continue;
			if(mat[ny].charAt(nx)=='.') {
				v[ny][nx] = true;
				dfs(nx,ny);
				if(flag)
					return;
			}
		}
	}
	static boolean isBound(int x,int y) {
		return x>=0 && y>=0 && x<C && y<R;
	}
}
