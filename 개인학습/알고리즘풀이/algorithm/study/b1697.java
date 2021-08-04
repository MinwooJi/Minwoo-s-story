import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class b1697 {
	static int[] v = new int[100001];
	static int[] trace = new int[100001];
	static int N;
	static int K;
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N= sc.nextInt();
		K = sc.nextInt();
		Arrays.fill(v, -1);
		bfs();
		System.out.println(min);
//		int i=K;
//		Stack<Integer> stk = new Stack<>();
//		while(trace[i]!=N) {
//			//System.out.println(trace[i]);
//			stk.push(trace[i]);
//			i = trace[i];
//		}
//		System.out.print(N + " ");
//		while(!stk.isEmpty()) {
//			System.out.print(stk.pop()+" ");
//		}
//		System.out.print(K);

	}
	static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		q.offer(N);
		v[N] = 0;
		while(!q.isEmpty()) {
			int temp = q.poll();
			if(v[K] != -1) {
				min = v[K];
				break;
			}
			if( !(temp > K) && temp+1 < v.length && v[temp+1]==-1) {
				v[temp+1] = v[temp]+1;
				q.offer(temp+1);
//				trace[temp+1] = temp;
			}
			if( !(temp > K) && temp*2 < v.length && v[temp*2]==-1) {
				v[temp*2] = v[temp]+1;
				q.offer(temp*2);
//				trace[temp*2] = temp;
			}
			if(temp-1 >= 0 && v[temp-1]==-1) {
				v[temp-1] = v[temp]+1;
				q.offer(temp-1);
//				trace[temp-1] = temp;
			}
		}
		
	}
}
