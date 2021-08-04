import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class b11724 { // 연산자 끼워넣기
	static int N;
	static int[]aI;
	static int[]operaters;
	static boolean v[];
	static HashSet<Long> perm = new HashSet<Long>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		aI = new int[N];
		operaters = new int[N-1];
		v = new boolean[N-1];
		for(int i=0;i<aI.length;i++) {
			aI[i] = sc.nextInt();
		}
		int idx = 0;
		for(int i=1;i<=4;i++) {
			int cnt = sc.nextInt();
			for(int j=0;j<cnt;j++) {
				operaters[idx++] = i; 
			}
		}//입력끝
		perm(0,0);
		int maxRst = Integer.MIN_VALUE;
		int minRst = Integer.MAX_VALUE;
		Iterator<Long> it = perm.iterator();
		Long temp;
		int a,b;
		long oper;
		while(it.hasNext()) {
			temp = it.next();
//			System.out.println(temp);
			a = aI[0];
			for(int i=1;i<aI.length;i++) {
				b = aI[i];
				oper = temp%10;
				temp /= 10;
				a = calc(a,b,oper);
			}
			maxRst = Math.max(a, maxRst);
			minRst = Math.min(minRst, a);
		}
		System.out.println(maxRst);
		System.out.println(minRst);
	}
	static void perm(int cnt,long oper) {
		if(cnt == N-1) {
			perm.add(oper);
			return;
		}
		for(int i=0;i<N-1;i++) {
			if(v[i]) continue;
			v[i] = true;
			perm(cnt+1,oper*10 + operaters[i]);
			v[i] = false;
		}
	}
	static int calc(int a,int b,long oper) {
		int rst = 0;
		switch((int)oper){
		case 1 :
			rst = a+b;
			break;
		case 2 :
			rst = a-b;
			break;
		case 3 :
			rst = a*b;
			break;
		case 4 :
			if(a<0) {
				rst = -((-a)/b);
			}else {
				rst = a/b;
			}
			break;
		}
		return rst;
	}
}
