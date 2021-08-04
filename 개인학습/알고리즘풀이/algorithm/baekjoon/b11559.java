import java.util.LinkedList;
import java.util.Scanner;

public class b11559 {
	static StringBuffer[] sb;
	static boolean[][] v;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {-1,0,1,0};
	static int cnt = 1;
	static LinkedList<Point> list = new LinkedList<>();
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
		sb = new StringBuffer[12];
		v = new boolean[12][6];
		for(int i=0;i<sb.length;i++) {
			sb[i] = new StringBuffer(sc.next());
		}//입력끝
		int flag = 1; // 첫시작
		int rst = 0;
		while(flag>0) {
			flag = 0;
			v = new boolean[12][6];
			for(int i=0;i<12;i++) {
				for(int j=0;j<6;j++) { // 터뜨리고 한개만 터지는건 콤보 x 같이 여러개 = +1 콤보 / 최초 폭박을 다르게 관리?
					if(!v[i][j] && sb[i].charAt(j) != '.') {
						cnt = 1;
						list = new LinkedList<>();
						list.add(new Point(i,j));
						v[i][j] = true;
						findFourDfs(new Point(i,j), sb[i].charAt(j));
						if(cnt >= 4) {
							while(!list.isEmpty()) {
								Point temp = list.poll();
								sb[temp.i].setCharAt(temp.j, '.');
							}
							flag ++; // 터뜨린 뿌요 뭉치
						}

					}
				}
			}
//			// 뿌요 떨어뜨리고
			drop();
			if(flag>0) 
				rst++;
		}
		System.out.println(rst);


	}
	static void drop() {
		int tempI;
		for(int i=0;i<12;i++) {
			for(int j=0;j<6;j++) {
				if(sb[i].charAt(j)!='.') {
					tempI = i;
					int count = 0;
					while(isBound(tempI+1,j) && sb[tempI+1].charAt(j) == '.') { // 몇칸 떨어뜨릴지 계산
						tempI ++;
						count ++;
					}
					tempI = i;
					while(isBound(tempI,j) && sb[tempI].charAt(j) != '.') { // 떨구기
						char temp = sb[tempI].charAt(j);
						sb[tempI].setCharAt(j, sb[tempI+count].charAt(j));
						sb[tempI+count].setCharAt(j, temp);
						tempI--;
					}
				}
			}
		}
	}
	static void findFourDfs(Point p,char ch) {
		for(int l=0;l<4;l++) {
			int ny = p.i + dy[l];
			int nx = p.j + dx[l];
			if(!isBound(ny,nx)) continue;
			if(v[ny][nx]) continue;
			if(sb[ny].charAt(nx)==ch) {
				list.offer(new Point(ny,nx));
				cnt++;
				v[ny][nx] = true;
				findFourDfs(new Point(ny,nx),ch);
			}
		}
	}
	static boolean isBound(int ny,int nx) {
		return ny>=0 && nx>=0 && nx<6 && ny<12;
	}
}
