import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;


public class b5213 { // 과외맨 (특이한 map bfs)_시간메모리 효율안좋았음
	static Tile mat[][];
	static int dist[];
	static boolean v[];
	static int dx[] = {0,1,0,-1};
	static int dy[] = {-1,0,1,0};
	static int trace[];
	static int N;
	static int qsize;
	static class Tile{
		int val;
		int number;
		Pos pair;
		public Tile(int val, int number, Pos pair) {
			this.val = val;
			this.number = number;
			this.pair = pair;
		}
		@Override
		public String toString() {
			return "tile [val=" + val + ", number=" + number + ", pair=" + pair + "]";
		}
	}
	static class Pos{
		int i;
		int j;
		public Pos(int i, int j) {
			this.i = i;
			this.j = j;
		}
		@Override
		public String toString() {
			return "Pos [i=" + i + ", j=" + j + "]";
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		mat = new Tile[N+1][N*2 +1];
		dist = new int[N*N+1];
		v = new boolean[N*N+1]; // 아마 쓸일없을듯
		trace = new int[N*N+1];
		Arrays.fill(dist, -1);
		int num = 0;
		for (int i = 1; i < mat.length; i++) {
			for (int j = 1; j < mat[0].length; j++) {
				if(i%2==0) {
					if(j==1) {
						mat[i][j] = new Tile(-1,-1,null);
						continue;
					}else if(j==mat[0].length-1) {
						mat[i][j] = new Tile(-1,-1,null);
						continue;
					}
				}
				if(num%2 == 0) {
					mat[i][j] = new Tile(sc.nextInt(),num++/2+1,new Pos(i,j+1)); // 값 넘버 페어
				}
				else
					mat[i][j] = new Tile(sc.nextInt(),num++/2+1,new Pos(i,j-1));
			}
		}// 입력끝
		bfs();
		int rst = 0;
		Pos temp = null;
		for (int i = dist.length-1; i >= 1; i--) {
			if(dist[i]!=-1) {
				rst = i;
				break;
			}
		}
		int idx = rst;
		Stack<Integer> stk = new Stack<>();
		stk.push(idx);
		while(idx != 1) {
			stk.push(trace[idx]);
			idx = trace[idx];
		}
		StringBuilder sb = new StringBuilder();
		sb.append(dist[rst]).append("\n");
		while(!stk.isEmpty()) {
			sb.append(stk.pop()).append(" ");
		}
		System.out.print(sb);
	}
	static void bfs() {
		int ny,nx,npx,npy,level = 1;
		Pos temp,pair;
		Queue<Pos> q = new LinkedList<>();
		q.offer(new Pos(1,1));
		v[1] = true;
		dist[1] = 1;
		while(!q.isEmpty()) {
			int size = q.size();
			for (int k = 0; k < size; k++) {
				temp = q.poll();
				pair = mat[temp.i][temp.j].pair;
				for (int l = 0; l < 4; l++) {
					ny = temp.i + dy[l];
					nx = temp.j + dx[l];
					if(!isBound(ny,nx)) continue;
					if(ny==pair.i && nx==pair.j) continue;
					if(mat[ny][nx].number == -1) continue;
					if(v[ mat[ny][nx].number ]) continue;
					if(mat[ny][nx].val != mat[temp.i][temp.j].val) continue;
					dist[ mat[ny][nx].number ] = level+1;
					v[mat[ny][nx].number] = true;
					trace[mat[ny][nx].number] = mat[temp.i][temp.j].number;
					q.offer(new Pos(ny,nx));
				}
				for (int l = 0; l < 4; l++) {
					npy = pair.i + dy[l];
					npx = pair.j + dx[l];
					if(!isBound(npy,npx)) continue;
					if(npy==temp.i && npx==temp.j) continue;
					if(mat[npy][npx].number == -1) continue;
					if(v[ mat[npy][npx].number ]) continue;
					if(mat[npy][npx].val != mat[pair.i][pair.j].val) continue;
					dist[ mat[npy][npx].number ] = level+1;
					v[mat[npy][npx].number] = true;
					trace[mat[npy][npx].number] = mat[pair.i][pair.j].number;
					q.offer(new Pos(npy,npx));

				}
			}
			level++;
		}

	}
	static boolean isBound(int ny,int nx) {
		return ny >= 1 && nx>=1 && ny<N+1 && nx<N*2 +1;
	}
}
