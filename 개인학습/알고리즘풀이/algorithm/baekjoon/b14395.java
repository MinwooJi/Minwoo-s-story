import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class b14395 { // 4연산 // set사용하고 String에 경로를 남김으로 메모리초과 잡기
	static int s,t;
	static HashSet<Long> set = new HashSet<>();
	static class Num{
		long val;
		String sb;
		public Num(long val, String sb) {
			super();
			this.val = val;
			this.sb = sb;
		}
		@Override
		public String toString() {
			return "Num [val=" + val + ", sb=" + sb + "]";
		}
	}
	static String minRst;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		s = sc.nextInt();
		t = sc.nextInt();
		if(s==t) {
			System.out.println(0);
			return;
		}
		bfs();
		if(minRst == null)
			System.out.println(-1);
		else {
			System.out.println(minRst.toString());
		}
	}
	static void bfs() {
		Num temp;
		Queue<Num> q = new LinkedList<>();
		q.offer(new Num(s,""));
		long longTemp;
		while(!q.isEmpty()) {
			temp = q.poll();
			for(int l=0;l<4;l++) {
				if(l==0) { // *+-/
					longTemp = temp.val*temp.val;
					if(longTemp == t) {
						minRst = temp.sb+ "*";
						return;
					}
					if(set.contains(longTemp)) continue;
					set.add(longTemp);
					q.offer(new Num(longTemp,temp.sb + "*"));
				}else if(l==1) {
					longTemp = temp.val+temp.val;
					if(longTemp == t) {
						minRst = temp.sb+ "+";
						return;
					}
					if(set.contains(longTemp)) continue;
					set.add(longTemp);
					q.offer(new Num(longTemp,temp.sb+"+"));
				}else if(l==2) {
					longTemp = temp.val-temp.val;
					if(longTemp <= 1) continue;
					if(longTemp == t) {
						minRst = temp.sb+"-";
						return;
					}
					if(set.contains(longTemp)) continue;
					set.add(longTemp);
					q.offer(new Num(longTemp,temp.sb+"-"));
				}else if(l==3) {
					if(temp.val<=1) continue;
					longTemp = temp.val/temp.val;
					if(longTemp == t) {
						minRst = temp.sb+"/";
						return;
					}
					if(set.contains(longTemp)) continue;
					set.add(longTemp);
					q.offer(new Num(longTemp,temp.sb+"/"));
				}
			}
		}
	}
}
