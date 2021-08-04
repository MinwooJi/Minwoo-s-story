import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class b13460 { // 구슬탈출 2
	static int N;
	static int M;
	static boolean v[][][][];
	static StringBuffer[] sb;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {-1,0,1,0};
	static class Pos{
		int rI;
		int rJ;
		int bI;
		int bJ;
		@Override
		public String toString() {
			return "Pos [rI=" + rI + ", rJ=" + rJ + ", bI=" + bI + ", bJ=" + bJ + "]";
		}
		public Pos(int rI, int rJ, int bI, int bJ) {
			super();
			this.rI = rI;
			this.rJ = rJ;
			this.bI = bI;
			this.bJ = bJ;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		sb = new StringBuffer[N];
		v = new boolean[N][M][N][M]; // 빨
		for(int i=0;i<N;i++) {
			sb[i] = new StringBuffer(sc.next());
		}// 입력끝
		Pos p = null;
		int rI=0,rJ=0,bI=0,bJ=0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(sb[i].charAt(j)=='R') {
					rI = i;
					rJ = j;
				}else if(sb[i].charAt(j)=='B') {
					bI = i;
					bJ = j;
				}
			}
		}
		p = new Pos(rI,rJ,bI,bJ);
		System.out.println(bfs(p));
	}
	static int bfs(Pos p) {
		int nRY,nRX,nBY,nBX;
		Pos temp;
		int level = 0;
		Queue<Pos> q = new LinkedList<>();
		v[p.bI][p.bJ][p.rI][p.rJ] = true;
		q.offer(p);
		boolean flag = false;
		boolean find = false;
		outer:
			while(!q.isEmpty()) {
				int size = q.size();
				for(int k=0;k<size;k++) {
					temp = q.poll();
					inner:
						for(int l=0;l<4;l++) {
							nRY = temp.rI;
							nRX = temp.rJ;
							nBY = temp.bI;
							nBX = temp.bJ;
							while(true) { // 파랑
								nBY = nBY + dy[l];
								nBX = nBX + dx[l];
								if(!isBound(nBY,nBX)) break;
								if(sb[nBY].charAt(nBX)=='#') break;
								if(nBY==temp.rI && nBX==temp.rJ) {
									flag = true;
								}
								if(sb[nBY].charAt(nBX)=='O') {
									flag = false;
									continue inner;
								}
							}
							nBY -= dy[l];
							nBX -= dx[l];
							if(flag) {
								nBY -= dy[l];
								nBX -= dx[l];
								flag = false;
							}
							while(true) { // 빨강
								nRY = nRY + dy[l];
								nRX = nRX + dx[l];
								if(!isBound(nRY,nRX)) break;
								if(sb[nRY].charAt(nRX)=='#') break;
								if(nRY==temp.bI && nRX==temp.bJ) {
									flag = true;
								}
								if(sb[nRY].charAt(nRX)=='O') {
									level ++;
									find = true;
									break outer;
								}
							}
							nRY -= dy[l];
							nRX -= dx[l];
							if(flag) {
								nRY -= dy[l];
								nRX -= dx[l];
								flag = false;
							}
							if(!v[nBY][nBX][nRY][nRX]) {
								v[nBY][nBX][nRY][nRX] = true;
								q.offer(new Pos(nRY,nRX,nBY,nBX));
							}
						}

				}
				if(level == 0) {
					sb[p.bI].setCharAt(p.bJ, '.'); 
					sb[p.rI].setCharAt(p.rJ, '.'); 
				}
				level ++;
			}
		if(level>10 || !find)
			return -1;
		else
			return level;
	}
	static boolean isBound(int ny,int nx) {
		return nx >= 0 && ny >=0 && nx < M && ny < N;
	}
}