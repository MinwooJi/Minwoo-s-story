import java.util.Scanner;
public class swe2105 {
	static int N;
	static int[][] mat;
	static int[] dx = {1,1,-1,-1}; // 시계방향 1시출
	static int[] dy = {-1,1,1,-1};
	static boolean[] v;
	static int maxRst = 0;
	static class Pos{
		int i;
		int j;
		public Pos(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		for(int t=0;t<tc;t++) {
			N = sc.nextInt();
			mat = new int[N][N];
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					mat[i][j] = sc.nextInt();
				}
			}// 입력끝
			maxRst = 0;
			Pos temp;
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					v = new boolean[101];
					v[mat[i][j]] = true;
					temp = new Pos(i,j);
					dfs(temp,temp,-1,1,0);
				}
			}
			System.out.printf("#%d ",t+1);
			if(maxRst == 0)
				maxRst = -1;
			System.out.println(maxRst);
		}
	}
	static void dfs(Pos start,Pos now,int curDir,int curRst,int bitMask) {
		Pos temp = now;;
		int ny,nx;
		int bBit = bitMask;
		for(int l=0;l<4;l++) {
			if(curDir!=-1 && l == ((curDir+2)%4)) // 역행
				continue;
			if(curDir != -1 && (bBit&(1<<l)) != 0) continue; // 이미쓴 반향
			ny = temp.i + dy[l];
			nx = temp.j + dx[l];
			if(!isBound(ny,nx)) continue;
			if(ny == start.i && nx == start.j) {
				maxRst = Math.max(maxRst, curRst);
				return;
			}
			if(v[mat[ny][nx]]) continue;
			v[mat[ny][nx]] = true;
			if(curDir != -1 && l != curDir) {
				bBit = bBit | 1<<curDir;
			}
			dfs(start,new Pos(ny,nx),l,curRst+1,bBit);
			v[mat[ny][nx]] = false;
		}
	}
	static boolean isBound(int ny,int nx) {
		return ny>=0 && nx>=0 && nx <N && ny < N;
	}
}
