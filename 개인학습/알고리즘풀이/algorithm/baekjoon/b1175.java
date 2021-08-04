package check;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
public class b1175 { // 배달 bfs
	static int N,M;
	static boolean v[][][][];
	static char[][] mat;
	static String[] sArr;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {-1,0,1,0};
	static int minRst;
	static class Pos{
		int i;
		int j;
		int direction;
		boolean a;
		boolean b;
		int find;
		public Pos(int i, int j, int direction, boolean a, boolean b, int find) {
			this.i = i;
			this.j = j;
			this.direction = direction;
			this.a = a;
			this.b = b;
			this.find = find;
		}
	}
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		v = new boolean[N][M][4][3]; // 좌표 방향 (못찾은것 a찾은것 b찾은것)
		sArr = new String[N];
		mat = new char[N][M];
		for (int i = 0; i < N; i++) {
			sArr[i] = br.readLine();
		}// 입력끝
		for (int i = 0; i < mat.length; i++) {
			mat[i] = sArr[i].toCharArray();
		}
		int ch = 0;
		Pos s = null;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(mat[i][j] == 'S') {
					s = new Pos(i,j,-1,false,false,0);
				}
				if(mat[i][j]=='C') {
					mat[i][j] = (char) ('A'+ch++);
				}
			}
		}
		if(s==null) System.out.println(-1);
		else {
			System.out.println(bfs(s));
		}
	}
	static int bfs(Pos s) {
		int ny,nx,find;
		boolean a,b;
		Pos temp;
		Queue<Pos> q = new LinkedList<>();
		q.offer(s);
		// 다음좌표, 지금내방향,a,b
		int rst = 0;
		while(!q.isEmpty()) {
			int size = q.size();
			for (int k = 0; k < size; k++) {
				temp = q.poll();
				for (int l = 0; l < 4; l++) {
					if(temp.direction == l) continue;
					ny = temp.i + dy[l];
					nx = temp.j + dx[l];
					a = temp.a;
					b = temp.b;
					find = temp.find;
					if(!isBound(ny, nx)) continue;
					if(v[ny][nx][l][find]) continue;
					if(mat[ny][nx] == '#') continue;
					if(mat[ny][nx] == 'A' && !a) {
						if(b) {
							return rst +1;
						}
						find= (mat[ny][nx]-'A'+1);
						a = true;
					}
					if(mat[ny][nx] == 'B' && !temp.b) {
						if(a) {
							return rst+1;
						}
						find=(mat[ny][nx]-'A'+1);
						b = true;
					}
					v[ny][nx][l][find] = true;
					q.offer(new Pos(ny,nx,l,a,b,find));
				}
			}
			rst++;
		}
		return -1;
	}
	static boolean isBound(int ny,int nx) {
		return ny>=0 && nx>=0 && nx<M && ny<N;
	}
}