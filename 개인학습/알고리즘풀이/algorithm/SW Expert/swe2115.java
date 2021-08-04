import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;


public class swe2115 {
	static int N,M,C;
	static int[][] mat;
	static Integer[] rst;
	static boolean[] v;
	static int maxSum = 0;
	static int[] sub;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		for(int t=0;t<tc;t++) {
			N = sc.nextInt();
			M = sc.nextInt();
			C = sc.nextInt();
			mat = new int[N][N];
			rst = new Integer[N];
			v = new boolean[N];
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					mat[i][j] = sc.nextInt();
				}
			}// 입력끝
			for(int i=0;i<N;i++) {
				maxSum = 0;
				for(int j=0;j<N;j++) {
					if(!isBound(j)) break;
					for(int k=M;k>=1;k--) {
						sub = new int[k];
						comb(i,j,j,0,k);
						rst[i] = maxSum;
					}
				}
			}
			int ans = 0;
			Arrays.sort(rst,Comparator.reverseOrder());
			for(int i=0;i<2;i++) {
				ans += rst[i]; 
			}
			System.out.printf("#%d ",t+1);
			System.out.println(ans);
			
			
		}
	}
	static void comb(int y,int x,int idx,int num,int target) {
		if(num == target) {
			int cnt = 0;
			int sum = 0;
			ArrayList<Integer> list = new ArrayList<>();
			for(int i=0;i<v.length;i++) {
				if(v[i]) {
//					sub[cnt++] = mat[y][i];
					sum += mat[y][i];
					list.add(mat[y][i]);
				}
			}
			if(sum<=C) {
				int temp = 0;
				for(int i=0;i<list.size();i++) {
					temp += (int) Math.pow(list.get(i), 2);
				}
				maxSum = Math.max(maxSum, temp);
			}
//			System.out.println(Arrays.toString(sub));
			return;
		}
		for(int i=idx;i<x+M;i++) {
			if(v[i]) continue;
			v[i] = true;
			comb(y,x,i,num+1,target);
			v[i] = false;
		}
		
	}
	static boolean isBound(int nx) {
		return nx>= 0 && nx+M<=N;
	}
}
