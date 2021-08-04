import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
public class b16236 {
	static class Shark{ // 아기상어 (bfs) - 다자원 visit/priorityQueue
		int i;
		int j;
		int mySize;
		int exp;
		public Shark(int i, int j, int mySize, int exp) {
			this.i = i;
			this.j = j;
			this.mySize = mySize;
			this.exp = exp;
		}
		public String toString() {
			return "Shark [i=" + i + ", j=" + j + ", mySize=" + mySize + ", exp=" + exp + "]";
		}
	}
	static int rst;
	static int[] dx = {0,-1,1,0}; // 상 좌 우 하
	static int[] dy = {-1,0,0,1};
	static int[][] mat;
	static int N;
	static boolean[][][] v;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		mat = new int[N][N];
		Shark sk = null;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				mat[i][j] = Integer.parseInt(st.nextToken());
				if(mat[i][j] == 9) {
					mat[i][j] = 0;
					sk = new Shark(i,j,2,0);
				}
			}
		}// 입력끝
		bfs(sk);
		System.out.println(rst);
	}
	static void bfs(Shark start) {
		Queue<Shark> q = new LinkedList<>();
		PriorityQueue<Shark> pq = new PriorityQueue<>(new Comparator<Shark>() {
			@Override
			public int compare(Shark arg0, Shark arg1) {
				if(Integer.compare(arg0.i, arg1.i) < 0) {
					return -1;
				}else if(Integer.compare(arg0.i, arg1.i) > 0) {
					return 1;
				}else {
					if(Integer.compare(arg0.j, arg1.j) < 0) {
						return -1;
					}else if(Integer.compare(arg0.j, arg1.j) > 0) {
						return 1;
					}else return 0;
				}
			}
		});
		int ny,nx;
		int mySize,exp;
		Shark temp;
		v = new boolean[N*N][N][N];
		int cnt = 0;
		int length = 0;
		v[cnt][start.i][start.j] = true;
		q.offer(start);
		int size;
		while(!q.isEmpty()) {
			size = q.size();
			for (int i = 0; i < size; i++) {
				temp = q.poll();
				mySize = temp.mySize;
				for(int l=0;l<4;l++) {
					ny = temp.i + dy[l];
					nx = temp.j + dx[l];
					if(!isBound(ny, nx)) continue;
					if(v[cnt][ny][nx]) continue;
					if(mat[ny][nx] > mySize) continue;
					if(mat[ny][nx]!=0 && mat[ny][nx] < mySize) { // 먹을수있음
						exp = temp.exp +1;
						if(exp == mySize) {
							exp = 0;
							mySize++;
						}
						pq.offer(new Shark(ny,nx,mySize,exp));
					}else if(mat[ny][nx] == 0 || mat[ny][nx] == mySize) {
						v[cnt][ny][nx] = true;
						q.offer(new Shark(ny,nx,temp.mySize,temp.exp));
					}
				}
			}
			length ++;
			if(!pq.isEmpty()) {
				Shark newS;
				newS = pq.poll();
				q.clear();
				pq.clear();
				q.offer(newS);
				rst += length;
				length = 0;
				cnt ++;
				mat[newS.i][newS.j] = 0;
				v[cnt][newS.i][newS.j] = true;
			}
		}
	}
	static boolean isBound(int ny,int nx) {
		return ny>=0 && nx>= 0 && ny<N && nx<N;
	}
}