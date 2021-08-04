import java.util.PriorityQueue;
import java.util.Scanner;

public class swe1251_prim { //MST_Prim // 하나로
	static long[][] dist;
	static long[][] arr;
	static int N;
	static class Node implements Comparable<Node>{
		int idx;
		long dist;
		@Override
		public String toString() {
			return "Node [idx=" + idx + ", dist=" + dist + "]";
		}
		public Node(int idx, long dist) {
			this.idx = idx;
			this.dist = dist;
		}
		@Override
		public int compareTo(Node arg0) {
			// TODO Auto-generated method stub
			return Long.compare(this.dist, arg0.dist);
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		for(int t =1;t<=tc;t++) {
			N = sc.nextInt();
			arr = new long[N][2];
			dist = new long[N][N];
			for(int i=0;i<N;i++) {
				arr[i][0] = sc.nextInt();
			}
			for(int i=0;i<N;i++) {
				arr[i][1] = sc.nextInt();
			}
			for(int i=0;i<N-1;i++) {
				for(int j=i+1;j<N;j++) {
					dist[i][j] = dist[j][i] = calc(arr[i][0],arr[i][1],arr[j][0],arr[j][1]); 
				}
			}
			double e = sc.nextDouble();
			// 0시작
			System.out.printf("#%d ",t);
			System.out.println(Math.round(prim(0)*e));
			
		}
	}
	static long prim(int start) {
		long rst = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>();
		Node[] nArr = new Node[N];
		nArr[0] = new Node(start,0);
		pq.offer(nArr[0]);
		for(int i=1;i<N;i++) {
			nArr[i] = new Node(i,Long.MAX_VALUE);
			pq.offer(nArr[i]);
		} // 전부 넣음
		Node temp,child;
		long distTemp;
		while (!pq.isEmpty()) { // pq에서 뽑을때마다 MST집합에 포함시킴
			temp = pq.poll();
			rst+=temp.dist;
			for(int i=0;i<nArr.length;i++) {
				child = nArr[i];
				if(pq.contains(child)) {
					distTemp = dist[temp.idx][child.idx];
					if(child.dist>distTemp) {
						child.dist = distTemp;
						pq.remove(child);
						pq.offer(child);
					}
				}
			}
		}
		return rst;
	}
	static long calc(long aI,long aJ,long bI,long bJ) {
		return (long)(Math.pow((aI-bI), 2) + Math.pow((aJ-bJ), 2)); 
	}
}
