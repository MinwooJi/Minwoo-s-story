import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
public class b2606 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int [][]mat = new int[N+1][N+1];
		boolean[] v = new boolean[N+1]; // 0버림
		int link = sc.nextInt();
		for(int i=0;i<link;i++) {
			int y = sc.nextInt();
			int x = sc.nextInt();
			mat[y][x] = 1;
			mat[x][y] = 1;
		}
		Queue<Integer> q = new LinkedList<>();
		v[1] = true;
		for(int i =1;i<N+1;i++)
			if(mat[1][i] != 0) 
				q.offer(i);
		int cnt = 0;
		while(!q.isEmpty()) {
			int temp = q.poll();
			if(v[temp])
				continue;
			v[temp] = true;
			cnt ++;
			for(int i=1;i<N+1;i++) 
				if(mat[temp][i]!=0)
					q.offer(i);
		}
		System.out.println(cnt);
	}
}
