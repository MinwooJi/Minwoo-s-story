import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class b5014 {
	static int F; // max
	static int S; // now
	static int G; // target
	static int[] command;
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		F = sc.nextInt();
		S = sc.nextInt();
		G = sc.nextInt();
		command = new int[2];
		command[0] = sc.nextInt();
		command[1] = 0 - sc.nextInt();
		if(S==G) { // 요놈!
			System.out.println(0); 
			return;
		}
		bfs();
		if(min!=Integer.MAX_VALUE)
			System.out.println(min);
		else {
			System.out.println("use the stairs");
		}
	}
	static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		int temp;
		int nInt;
		boolean[] v = new boolean[F+1];
		int[] check = new int[F+1];
		v[S] = true;
		q.offer(S);
		while(!q.isEmpty()) {
			temp = q.poll();
			for(int l=0;l<2;l++) {
				nInt = temp + command[l];
				if(!isBound(nInt)) continue;
				if(v[nInt]) continue;
				if(nInt == G) {
					min = check[temp] +1;
					return;
				}
				v[nInt] = true;
				check[nInt] = check[temp]+1;
				q.offer(nInt);
			}
		}
	}
	static boolean isBound(int nInt) {
		return nInt>=1 && nInt < F+1;
	}
}
