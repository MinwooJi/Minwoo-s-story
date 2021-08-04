import java.util.Arrays;
import java.util.Scanner;

public class swe1952 {
	static int month[] = new int[12];
	static int val[];
	static int dayCost,monthCost,tMonthCost,yearCost; // 0 1 2
	static int rst = Integer.MAX_VALUE;
	public static void main(String[] args) {
		Scanner sc =new Scanner(System.in);
		int tc = sc.nextInt();
		for(int t=0;t<tc;t++) {
			dayCost = sc.nextInt();
			monthCost = sc.nextInt();
			tMonthCost = sc.nextInt();
			val = new int[]{dayCost,monthCost,tMonthCost};
			yearCost = sc.nextInt();
			
			rst = Integer.MAX_VALUE;
			for(int i=0;i<month.length;i++) {
				month[i] = sc.nextInt();
			}// 입력끝
			perm(0,0);
			System.out.printf("#%d ",t+1);
			System.out.println(rst);
		}
	}
	static void perm(int num,int curSum) {
		if(num == 12) {
			if(curSum > yearCost) {
				rst = Math.min(rst, yearCost);
			}else {
				rst = Math.min(rst,curSum);
			}
			return;
		}
		for(int i=0;i<3;i++) {
			if(i==0) {
				perm(num+1,curSum +month[num] * val[i]);
			}else if(i==1) {
				perm(num+1,curSum +val[i]);
			}else {
				if(num+3 > 12) {
					perm(12,curSum +val[i]);
				}else
					perm(num+3,curSum +val[i]);
			}
			
		}
	}
	
}
