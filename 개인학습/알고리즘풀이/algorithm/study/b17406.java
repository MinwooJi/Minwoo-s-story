import java.util.Scanner;
public class b17406 { // 배열돌리기 4
	static class Cas{
		int r;
		int c;
		int s;
		public Cas(int r, int c, int s) {
			this.r = r;
			this.c = c;
			this.s = s;
		}
		@Override
		public String toString() {
			return "Cas [r=" + r + ", c=" + c + ", s=" + s + "]";
		}
	}
	static int[][] mat;
	static int[][] matC; // 복사한 원본
	static Cas[] casArr;
	static int N,M;
	static int K;
	static int minRst = Integer.MAX_VALUE;
	static boolean v[];
	static int[] set;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		mat = new int[N][M];
		matC = new int[N][M];
		casArr = new Cas[K];
		v = new boolean[K];
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				mat[i][j] = sc.nextInt();
			}
		}
		for(int i=0;i<N;i++) {
			matC[i] = mat[i].clone();
		}
		for(int i=0;i<K;i++) {
			casArr[i] = new Cas(sc.nextInt()-1,sc.nextInt()-1,sc.nextInt());
		}
		set  = new int[K];
		perm(0);
		System.out.println(minRst);
	}
	static void perm(int cnt) {
		if(cnt == K) {
			for(int i=0;i<set.length;i++) {
				rotate(casArr[set[i]].r, casArr[set[i]].c, casArr[set[i]].s);
			}
			int matMin = Integer.MAX_VALUE;
			for(int i=0;i<N;i++) {
				int min = 0;
				for(int j=0;j<M;j++) {
					min += mat[i][j];
				}
				matMin = Math.min(matMin, min);
			}
			minRst = Math.min(minRst, matMin);
			for(int i=0;i<N;i++) {
				mat[i] = matC[i].clone();
			}
		}
		for(int i=0;i<K;i++) {
			if(v[i]) continue;
			v[i] = true;
			set[cnt] = i;
			perm(cnt+1);
			v[i] = false;
		}
	}
	static void rotate(int r,int c,int s) {
		while(s!=0) {
			cycle(r,c,s); 
			s--;
		 }
	}
	static void cycle(int r,int c,int s) {
		int temp1 = mat[r-s][c-s];
		int temp2;
		for(int i=c-s;i<c+s;i++) { // 윗변
			temp2 = mat[r-s][i+1];
			mat[r-s][i+1] = temp1;
			temp1 = temp2;
		} // 마지막 temp1 에 r-s c+s값저장
		for(int i=r-s;i<r+s;i++) { // 오른변
			temp2 = mat[i+1][c+s];
			mat[i+1][c+s] = temp1;
			temp1 = temp2;
		}
		for(int i=c+s;i>c-s;i--) { // 아랫변
			temp2 = mat[r+s][i-1];
			mat[r+s][i-1] = temp1;
			temp1 = temp2;
		}
		for(int i=r+s;i>r-s;i--) { // 왼변
			temp2 = mat[i-1][c-s];
			mat[i-1][c-s] = temp1;
			temp1 = temp2;
		}
	}
}
