import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class MelonField {
	static class Point{
		int turn;
		int st;
		boolean flag;
		public Point(int turn, int st) {
			this.turn = turn;
			this.st = st;
		}
		
	}
	public static void main(String[] args) {
		Queue<Point> q = new LinkedList<>();
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		Point[] pArr = new Point[6];
		for(int i=0;i<pArr.length;i++) {
			pArr[i] = new Point(sc.nextInt(),sc.nextInt());
		} // 입력끝
		int maxWidth = 0;
		int maxWIdx = -1;
		int maxHeigt = 0;
		int maxHIdx = -1;
		for(int i=0;i<pArr.length;i++) {
			if(pArr[i].turn == 1 || pArr[i].turn == 2 ) { // 동서 가로
				if(maxWidth < pArr[i].st) {
					maxWidth = pArr[i].st;
					maxWIdx = i;
				}
			}
			else {
				if(maxHeigt < pArr[i].st) {
					maxHeigt = pArr[i].st;
					maxHIdx = i;
				}
			}
		}
		pArr[maxWIdx].flag = true;
		pArr[maxHIdx].flag = true;
		
		for(int i=0;i<pArr.length;i++) {
			q.offer(pArr[i]);
		} // 일단 넣고 true ture 나올때까지
		Point[] rst = new Point[6];
 		while(true) {
			Point temp = q.poll();
			if(temp.flag && q.peek().flag) {
				rst[0] = temp;
				for(int i=1;i<rst.length;i++) {
					rst[i] = q.poll(); 
				}
				break;
			} // 끝
			q.offer(temp);
		}
 		int ans = rst[0].st*rst[1].st - rst[3].st*rst[4].st;
 		System.out.println(N*ans);
 		
	}
}	
