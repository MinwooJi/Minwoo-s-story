import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class swea3234 { // 준환이의 양팔저울 (dfs 백트레킹 + 가지치기)
	static int N,rst;
	static int[] arr;
	static boolean[] v;
	static int totalSum;
    static int factVal[] = new int[10];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		factVal[0] = 1;
		factVal[1] = 1;
		for (int i = 2; i < factVal.length; i++) {
			fact(i);
		}
		int tc = Integer.parseInt(br.readLine());
		for (int t = 1; t <= tc; t++) {
			N = Integer.parseInt(br.readLine());
			rst = 0;
			totalSum = 0;
			arr = new int[N];
			v = new boolean[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < arr.length; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				totalSum += arr[i];
			}// 입력끝
			dfs(0,0,0,0);
			sb.append("#").append(t).append(" ").append(rst).append("\n");
		}
		System.out.println(sb);
				
	}
	
	static void dfs(int leftWeight,int rightWeight,int choice,int curSum) {
		if(leftWeight < rightWeight) return;
		if(choice == N) {
			rst++;
			return;
		}
		if( (leftWeight - rightWeight) > (totalSum -curSum) ) {
			rst+=factVal[N-choice]*Math.pow(2, N-choice);
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			if(v[i]) continue;
			v[i] = true;
			dfs(leftWeight+arr[i],rightWeight,choice+1,curSum + arr[i]);
			dfs(leftWeight,rightWeight+arr[i],choice+1,curSum + arr[i]);
			v[i] = false;
		}
	}
	static int fact(int n) {
		if(n==1) return 1;
		if(factVal[n] != 0) return factVal[n];
		return factVal[n] = n*fact(n-1);
	}
}
