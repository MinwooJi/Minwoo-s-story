import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
public class b1753 { // 최단경로
	static int V,E,K;
	static List<Node> adj[];
	static boolean[] check;
	static class Node implements Comparable<Node>{
		int cur;
		int dist;
		public Node(int next, int dist) {
			this.cur = next;
			this.dist = dist;
		}
		@Override
		public String toString() {
			return "Node [cur=" + cur + ", dist=" + dist + "]";
		}
		@Override
		public int compareTo(Node arg0) {
			// TODO Auto-generated method stub
			return Integer.compare(this.dist, arg0.dist);
		}
	}
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine())-1;
		adj = new ArrayList[V];
		for (int i = 0; i < V; i++) {
			adj[i] = new ArrayList<>();
		}
		int u,v,w;
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken())-1;
			v = Integer.parseInt(st.nextToken())-1;
			w = Integer.parseInt(st.nextToken());
			adj[u].add(new Node(v,w));
		}// 입력끝
		dijkstr();
	}
	static void dijkstr() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		check = new boolean[V];
		Node[] nArr = new Node[V];
		Node temp;
		for (int i = 0; i < V; i++) {
			if(i==K) {
				nArr[i] = new Node(i,0);
			}else {
				nArr[i] = new Node(i,Integer.MAX_VALUE);
			}
			pq.offer(nArr[i]);
		}
		int cur;
		while(!pq.isEmpty()) {
			temp = pq.poll();
			cur = temp.cur;
			if(temp.dist == Integer.MAX_VALUE) break; // 연결되있지 않은 노드일때 ( 최소값뽑은게 MAX)
			for (Node next : adj[cur]) {
				if(!check[next.cur] && nArr[next.cur].dist > next.dist + nArr[cur].dist) {
					nArr[next.cur].dist = next.dist + nArr[cur].dist;
					pq.remove(nArr[next.cur]);
					pq.add(nArr[next.cur]);
				}
			}
			check[temp.cur] = true;
		}
		int rst = 0;
		for (Node node : nArr) {
			System.out.println(node.dist == Integer.MAX_VALUE ? "INF" :node.dist);
		}
		
	}
}
