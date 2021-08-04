import java.util.Scanner;
public class b1476 { // 날짜 계산
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);	
		int E = sc.nextInt();
		int S = sc.nextInt();
		int M = sc.nextInt();
		int cnt = 1;
		int cntE = 0,cntS = 0,cntM = 0;
		while(true) {
			cntE = cnt%15;
			cntS = cnt%28;
			cntM = cnt%19;
			if(cntE == 0)
				cntE = 15;
			if(cntS == 0)
				cntS = 28;
			if(cntM == 0)
				cntM = 19;
			if(cntE == E && cntS == S && cntM == M) {
				break;
			}
			cnt ++;
		}
		System.out.println(cnt);
	}
}
