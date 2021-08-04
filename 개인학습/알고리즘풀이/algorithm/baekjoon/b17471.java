import java.util.Scanner;
public class b17471 {
	static int N;
	static int[] population;
	static int[][] mat;
	static int sum = 0;
	static boolean[] v;
	static boolean[] v2;
	static int[] arr;
	static boolean flag1 = false;
	static boolean flag2 = false;
	static int targetCnt = 0;
	static int minRst = Integer.MAX_VALUE;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		population = new int[N+1];
		mat = new int[N+1][N+1];
		v = new boolean[N+1];
		v2 = new boolean[N+1];
		for(int i=1;i<=N;i++) {
			population[i] = sc.nextInt(); 
			sum += population[i];
		}
		for(int i=1;i<mat.length;i++) {
			int num = sc.nextInt();
			for(int j=0;j<num;j++) {
				mat[i][sc.nextInt()] = 1;
			}
		}// 입력끝
		for(int i=1;i<=N/2;i++) {
			arr = new int[i];
			comb(i,0,1); // 부분집합 생성하고
		}
		if(minRst == Integer.MAX_VALUE)
			minRst = -1;
		System.out.println(minRst);

	}
	static void dfs(int idx, boolean check,int target) {
		if(targetCnt == target) {
			if(check) {
				flag1 = true;
			}else {
				flag2 = true;
			}
		}
		for(int i=1;i<v.length;i++) {
			if(!v2[i] && v[i] == check && mat[idx][i] == 1) {
				v2[i] = true;
				targetCnt++;
				dfs(i,check,target);
			}
		}
	}
	static void comb(int target,int idx,int num) {
		if(idx == target) { // 여기서 진행
			for(int k=0;k<arr.length;k++) {
				v[arr[k]] = true;
			}
			int tIdx = 0,fIdx = 0;
			int tCnt = 0,fCnt = 0;
			boolean tFlag = true;
			boolean fFlag = true;
			for(int i=1;i<v.length;i++) {
				if(v[i]) {
					if(tFlag) {
						tFlag = false;
						tIdx = i;
					}
					tCnt++;
				}else {
					if(fFlag) {
						fFlag = false;
						fIdx = i;
					}
					fCnt++;
				}
			}
			v2[tIdx] = true;
			targetCnt = 1;
			flag1 = false;
		
			dfs(tIdx,true,tCnt); // 참인 것 확인
			v2[fIdx] = true;
			targetCnt = 1;
			flag2 = false;
			
			dfs(fIdx,false,fCnt);
			v = new boolean[N+1]; // 안선택받은거 관리
			v2 = new boolean[N+1]; 
			if(flag1 && flag2) {
				int localSum = 0;
				for(int i=0;i<arr.length;i++) {
					localSum += population[arr[i]];
				}
				minRst = Math.min(minRst, Math.abs(localSum-(sum-localSum) ) );
			}
			return;
		}
		for(int i=num;i<=N;i++) {
			arr[idx] = i;
			comb(target,idx+1,i+1);
		}
	}
}
