import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class b9328 {
	static boolean v[][];
	static int N;
	static int M;
	static StringBuffer[] sb;
	static String key;
	static int dx[] = {0,1,0,-1};
	static int dy[] = {-1,0,1,0};
	static int maxRst = 0;
	static LinkedList<Point> checkArr[] = new LinkedList[26];
	static LinkedList<Point> doorPoint[] = new LinkedList[26];
	static class Point{
		int i;
		int j;
		public Point(int i, int j) { 
			this.i = i;
			this.j = j;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		for(int t=0;t<tc;t++) {
			N = sc.nextInt();
			M = sc.nextInt();
			for(int i=0;i<checkArr.length;i++) {
				checkArr[i] = new LinkedList<Point>();
				doorPoint[i] = new LinkedList<Point>();
			}
			sb = new StringBuffer[N+2]; // [N+2][M+2];
			v = new boolean[N+2][M+2]; 
			for(int i=0;i<N+2;i++) {
				sb[i] = new StringBuffer(".");
			}
			for(int i=1;i<M+2;i++) {
				sb[0].append(".");
				sb[N+1].append(".");
			}
			for(int i=1;i<N+1;i++) {
				sb[i].append(sc.next());
			}
			for(int i=1;i<N+1;i++) {
				sb[i].append(".");
			}
			key = sc.next(); // 입력끝
			char[] keySet = key.toCharArray();
			for(int k=0;k<keySet.length;k++) {
				for(int i=1;i<sb.length-1;i++) {
					for(int j=1;j<sb[0].length()-1;j++) {
						if((int)sb[i].charAt(j)+32 == (int)keySet[k]) {
							sb[i].setCharAt(j, '.'); // 처음가지고있던거 말고 좌표들 아래에 넣음
						}else if(sb[i].charAt(j)>='A' && sb[i].charAt(j)<='Z') {
							doorPoint[(int)sb[i].charAt(j) - (int)'A'].offer(new Point(i,j));
						}
					}
				}
			} // 지금 열쇠로 열수있는거 다 오픈
			System.out.println(Bfs());
		}
	}
	static int Bfs() {
		Queue<Point> q = new LinkedList<>();
		Point temp;
		int ny,nx;
		int idx;
		int num = 0;
		q.offer(new Point(0,0)); // i,j,n;
		while(!q.isEmpty()) {
			temp = q.poll();
			for(int l=0;l<4;l++) {
				ny = temp.i + dy[l];
				nx = temp.j + dx[l];
				if(!isBound(ny,nx)) continue;
				if(v[ny][nx]) continue;
				if(sb[ny].charAt(nx)=='*') continue;
				if(sb[ny].charAt(nx)>='A' && sb[ny].charAt(nx)<='Z') { // 아직 못품
					idx = (int)sb[ny].charAt(nx) - (int)'A';
					checkArr[idx].offer(new Point(ny,nx));
					v[ny][nx] = true;
					continue;
				}
				if(sb[ny].charAt(nx)=='$') {
					num++; // 아래로 떨어뜨려서 방문처리랑 큐에 넣기
				}else if(sb[ny].charAt(nx)>='a' && sb[ny].charAt(nx)<='z') { // 열쇠 찾음
					idx = (int)sb[ny].charAt(nx) - (int)'a';
					Point listTemp;
					while(!checkArr[idx].isEmpty()) { // 탐색 못햇던거 다시 넣어줌
						listTemp = checkArr[idx].poll();
						q.offer(listTemp);
					}
					while(!doorPoint[idx].isEmpty()) {
						listTemp = doorPoint[idx].poll();
						sb[listTemp.i].setCharAt(listTemp.j, '.');
					}
				}//빈공간들과 위에 분기떨어진것 처리
				v[ny][nx] = true;
				q.offer(new Point(ny,nx));
			}
		}
		return num;
	}
	static boolean isBound(int ny,int nx) {
		return ny>=0 && nx >=0 && ny < N+2 && nx< M+2;
	}
}
