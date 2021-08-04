import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
public class b13549 {
	static int[] v = new int[100001];
	static boolean[] visit = new boolean[100001];
	static int N;
	static int K;
	static int min = Integer.MAX_VALUE;
	static int cnt = 0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N= sc.nextInt();
		K = sc.nextInt();
		if(N==K) { // 요놈
			System.out.println(0);
			return;
		}
		Arrays.fill(v, -1);
		bfs();
		System.out.println(min);
	}
	static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		q.offer(N);
		v[N] = 0;
		while(!q.isEmpty()) {
			int temp = q.poll();
			visit[temp] = true;
			if(temp+1 == K || temp*2 == K || temp-1 == K) {
				if(temp+1 == K ) {
					if(v[temp]+1 <= min) {
						min = v[temp]+1;
						cnt++;
					}
				}
				if(temp*2 == K ) {
					if(v[temp]+1 <= min) {
						min = v[temp];
						cnt++;
					}
				}
				if(temp-1 == K ) {
					if(v[temp]+1 <= min) {
						min = v[temp]+1;
						cnt++;
					}
				}
			}
			else {
				if( !(temp > K) && temp+1 < v.length && temp+1 != K && !visit[temp+1]) {
					if(v[temp+1] == -1) {
						v[temp+1] = v[temp]+1;
						q.offer(temp+1);
						
					}else if(v[temp+1] >= v[temp]+1) {
						v[temp+1] = v[temp]+1;
						q.offer(temp+1);
					}
				}
				if( !(temp > K) && temp*2 < v.length && temp*2 != K && !visit[temp*2]) {
					if(v[temp*2] == -1) {
						v[temp*2] = v[temp];
						q.offer(temp*2);
					}else if(v[temp*2] >= v[temp]) {
						v[temp*2] = v[temp];
						q.offer(temp*2);
					}
				}
				if(temp-1 >= 0 && temp-1 != K && !visit[temp-1]) {
					if(v[temp-1] == -1) {
						v[temp-1] = v[temp]+1;
						q.offer(temp-1);
					}else if(v[temp-1] >= v[temp]+1) {
						v[temp-1] = v[temp]+1;
						q.offer(temp-1);
					}
				}
			}
		}
	}
}
