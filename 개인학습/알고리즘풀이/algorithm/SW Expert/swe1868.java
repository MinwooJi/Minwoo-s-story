import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
public class swe1868 { // 파핑파핑 지뢰 찾기
	static char[][] matC;
	static String[] sArr;
	static int N;
	static int[] dx = {0,1,1,1,0,-1,-1,-1};
	static int[] dy = {-1,-1,0,1,1,1,0,-1};
	static boolean[][] v;
	static class Pos{
		int i;
		int j;
		public Pos(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		for (int t = 1; t <= tc; t++) {
			N = Integer.parseInt(br.readLine());
			matC = new char[N][N];
			sArr = new String[N];
			int minRst = 0;
			for (int i = 0; i < sArr.length; i++) {
				sArr[i] = br.readLine();
				matC[i] = sArr[i].toCharArray();
			}// 입력끝
			for (int i = 0; i < matC.length; i++) { // 폭발
				for (int j = 0; j < matC[0].length; j++) {
					if(matC[i][j] == '.') {
						boom(i,j);
					}
				}
			}
			v = new boolean[N][N];
			for (int i = 0; i < matC.length; i++) { // 왕거니 계산
				for (int j = 0; j < matC[0].length; j++) {
					if(!v[i][j] && matC[i][j] == '0') {
						check(i,j);
						minRst ++;
					}
				}
			}
			for (int i = 0; i < matC.length; i++) { // 나머지 계산
				for (int j = 0; j < matC[0].length; j++) {
					if(!v[i][j] && matC[i][j] != '*') { 
						minRst ++;
					}
				}
			}
			StringBuilder sb = new StringBuilder();
			sb.append(String.format("#%d ", t)).append(minRst).append("\n");
			System.out.print(sb);
		}
	}
	static void check(int y,int x) {
		Queue<Pos> q = new LinkedList<>();
		int ny,nx;
		Pos temp;
		v[y][x] = true;
		q.offer(new Pos(y,x));
		while(!q.isEmpty()) {
			temp = q.poll();
			for (int l = 0; l < 8; l++) {
				ny = temp.i + dy[l];
				nx = temp.j + dx[l];
				if(!isBound(ny,nx))	continue;
				if(v[ny][nx]) continue;
				v[ny][nx] = true;
				if(matC[ny][nx] == '0') {
					q.offer(new Pos(ny,nx));
				}
			}
		}
	}
	static void boom(int y,int x) { 
		int ny,nx;
		int cnt = 0;
		for (int l = 0; l < 8; l++) {
			ny = y + dy[l];
			nx = x + dx[l];
			if(!isBound(ny,nx))	continue;
			if(matC[ny][nx] == '*') cnt++;
		}
		matC[y][x] = (char) (cnt + '0');
	}
	static boolean isBound(int ny,int nx) {
		return ny>=0 && nx >= 0 && nx < N && ny < N;
	}
}
