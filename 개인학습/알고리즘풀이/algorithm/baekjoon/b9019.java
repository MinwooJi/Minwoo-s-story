import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class b9019 {
	static char[] function  = {' ','D','S','L','R'};
	static boolean[]v;
	static int[] trace;
	static char[] traceC;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		int rst;
		for(int t=0;t<tc;t++) {
			int initVal = sc.nextInt();
			int finalVal = sc.nextInt();
			v = new boolean[10000];
			trace = new int[10000];
			traceC = new char[10000];
			bfs(initVal,finalVal);
			Stack<Character> stk = new Stack<>();
			int idx = finalVal;
			while(trace[idx] != initVal) {
				stk.push(traceC[idx]);
				idx = trace[idx];
			}
			stk.push(traceC[idx]);
			while(!stk.isEmpty()) {
				System.out.print(stk.pop());
			}
			System.out.println();
		}
	}
	static void bfs(int initVal,int targetVal) {
		Queue<Integer> q = new LinkedList<>();
		v[initVal] = true;
		int temp;
		q.offer(initVal);
		while(!q.isEmpty()) {
			temp = q.poll();
			for(int l=1;l<=4;l++) {
				int calVal = cal(temp,l); 
				if(v[calVal]) continue;
				v[calVal] = true;
				trace[calVal] = temp;
				traceC[calVal] = function[l];
				if(calVal == targetVal) {
					return;
				}
				q.offer(cal(temp,l));
			}
		}
	}
	static int cal(int num,int func) {
		int result = 0;
		if(func == 1) {
			result = (num*2) % 10000;
		}else if(func == 2) {
			if(num == 0) {
				result = 9999;
			}else {
				result = num -1;
			}
		}else if(func == 3) {
			result = (num % 1000) * 10 + num/1000;

		}else if(func == 4) {
			result = num/10 + 1000*(num%10);
		}

		return result;
	}
}
