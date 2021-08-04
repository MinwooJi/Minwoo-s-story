import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class swe7793 { // 오! 나의 여신님
	static int N,M;
	static StringBuffer []sb;
	static boolean [][]v;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {-1,0,1,0};
	static int minRst;
	static class Pos{
		int i;
		int j;
		int level;
		int id;
		public Pos(int i, int j, int level, int id) {
			super();
			this.i = i;
			this.j = j;
			this.level = level;
			this.id = id;
		}
		@Override
		public String toString() {
			return "Pos [i=" + i + ", j=" + j + ", level=" + level + ", id=" + id + "]";
		}
		
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		for(int t=1;t<=tc;t++) {
			N = sc.nextInt();
			M = sc.nextInt();
			sb = new StringBuffer[N];
			v = new boolean[N][M];
			minRst = Integer.MAX_VALUE;
			for(int i=0;i<N;i++) {
				sb[i] = new StringBuffer(sc.next());
			}
			Pos s=null;
			ArrayList<Pos> dList = new ArrayList<>();
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					if(sb[i].charAt(j)=='S') {
						s = new Pos(i,j,0,0);
					}else if(sb[i].charAt(j)=='*') {
						dList.add(new Pos(i,j,0,1));
					}
				}
			}
			bfs(s,dList);
			System.out.printf("#%d ",t);
			if(minRst == Integer.MAX_VALUE)
				System.out.println("GAME OVER");
			else 
				System.out.println(minRst);
		}
	}
	static void bfs(Pos s, ArrayList<Pos> dList) {
		Queue<Pos>q = new LinkedList<>();
		int ny,nx;
		Pos temp;
		for(Pos p : dList) {
			v[p.i][p.j] = true;
			q.offer(p);
		}
		v[s.i][s.j] = true;
		q.offer(s);
		while(!q.isEmpty()) {
			temp = q.poll();
			for(int l=0;l<4;l++) {
				ny = temp.i + dy[l];
				nx = temp.j + dx[l];
				if(!isBound(ny,nx)) continue;
				if(sb[ny].charAt(nx)=='X') continue;
				if(sb[ny].charAt(nx)=='D') {
					if(temp.id == 0) { // 수연
						minRst = temp.level+1;
						return;
					}else if(temp.id==1) { // 악마
						continue;
					}
				}
				if(v[ny][nx]) continue;
				v[ny][nx] = true;
				if(temp.id==1) {
					sb[ny].setCharAt(nx, '*');
				}
				else if(temp.id==0) {
					sb[ny].setCharAt(nx, 'S');
				}
				q.offer(new Pos(ny,nx,temp.level+1,temp.id));
			}
		}
		
	}
	static boolean isBound(int ny,int nx) {
		return ny>=0 && nx>=0 && ny <N && nx <M;
	}
}
