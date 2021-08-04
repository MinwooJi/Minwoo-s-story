import java.util.Scanner;

public class swe4796 { // 의석이의 우뚝 선 산
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		for(int t=0;t<tc;t++) {
			int N = sc.nextInt();
			int rst = 0;
			int cntL = 0;
			int cntR = 0;
			int prev,next;
			prev = sc.nextInt();
			next = sc.nextInt();
			boolean isInc = false;
			if(prev < next) {
				isInc = true;
				cntL ++;
			}
			prev = next;
			for(int i=2;i<N;i++) {
				next = sc.nextInt();
				if(isInc && next > prev) {
					cntL++;
				}else if(isInc && next < prev) {
					isInc = false;
					cntR++;
				}else if(!isInc && next < prev) {
					cntR++;
				}else if(!isInc && next > prev) {
					rst += (cntL * cntR);
					isInc = true;
					cntL = 1;
					cntR = 0;
				}
				prev = next;
			}
			if(cntL != 0 && cntR != 0) {
				rst += (cntL * cntR);
			}
			System.out.printf("#%d ",t+1);
			System.out.println(rst);
		}
	}
}
