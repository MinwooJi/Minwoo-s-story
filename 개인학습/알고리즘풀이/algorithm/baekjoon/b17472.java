import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class b17472 { // 다리만들기2 dfs + graph Mst(Prim)
	static int N,M;
	static int[][] mat;
	static boolean[][] v;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {-1,0,1,0};
	static int num = 1;
	static int[][] adj;
	static class Edge{
		int v;
		int weight;
		public Edge(int v, int weight) {
			this.v = v;
			this.weight = weight;
		}
		public String toString() {
			return "Edge [v=" + v + ", weight=" + weight + "]";
		}

	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		mat = new int[N][M];
		v = new boolean[N][M];
		for (int i = 0; i < mat.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < mat[0].length; j++) {
				mat[i][j] = Integer.parseInt(st.nextToken());
			}
		}// 입력끝

		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				if(mat[i][j] != 0 && !v[i][j]) {
					v[i][j] = true;
					mat[i][j] = num;
					numbering(i,j);
					num++;
				}
			}
		}
		num--;
		adj = new int[num][num]; // v+1
		// edge계산
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				if(mat[i][j] != 0) {
					calEdge(i,j);
				}
			}
		}
		boolean[] check = new boolean[num]; // 내편 니편
		int[] key = new int[num]; // 현재 선택된 정점으로 부터 도달할수있는 최소거리
		int[] p = new int[num]; // 부모
		
		Arrays.fill(key, Integer.MAX_VALUE);
		
		// prim
		p[0] = -1;
		key[0] = 0;
		for (int i = 0; i < num-1; i++) {
			int min = Integer.MAX_VALUE;
			int index = -1; 
			for (int j = 0; j < num; j++) { 
				if(!check[j] && key[j]<min) {
					index = j;
					min = key[j];
				}
			}
			if(index == -1) {
				System.out.println(-1);
				return;
			}
			check[index] = true; 
			for (int j = 0; j < num; j++) { 
				if(!check[j] && adj[index][j] != 0 && key[j] > adj[index][j]) {
					p[j] = index;
					key[j] = adj[index][j];
				}
			}
		}
		for (int i = 0; i < key.length; i++) {
			if(key[i] == Integer.MAX_VALUE) {
				System.out.println(-1);
				return;
			}
		}
		int result = 0;
		for (int i = 0; i < num; i++) {
			result += key[i];
		}
		System.out.println(result);
	}
	static void calEdge(int y,int x) {
		int ny,nx;
		int my = mat[y][x];
		for (int l = 0; l < dx.length; l++) {
			ny = y + dy[l];
			nx = x + dx[l];
			int length = 0;
			while(true) {
				if(!isBound(ny,nx)) break;
				if(mat[ny][nx] == my) break;
				if(length>1 && mat[ny][nx] != 0) {
					if(adj[my-1][mat[ny][nx]-1]==0 || adj[my-1][mat[ny][nx]-1] > length) {
						adj[my-1][mat[ny][nx]-1] = length;
						adj[mat[ny][nx]-1][my-1] = length;
						break;
					}
				}
				if(mat[ny][nx] == 0) {
					ny += dy[l];
					nx += dx[l];
					length++;
				}else break;
			}
		}
	}
	static void numbering(int y,int x) {
		int ny,nx;
		for (int l = 0; l < dx.length; l++) {
			ny = y + dy[l];
			nx = x + dx[l];
			if(!isBound(ny,nx)) continue;
			if(v[ny][nx]) continue;
			if(mat[ny][nx] != 0) {
				v[ny][nx] = true;
				mat[ny][nx] = num;
				numbering(ny,nx);
			}
		}
	}
	static boolean isBound(int ny,int nx) {
		return ny>=0 && nx>=0 && ny<N && nx<M;
	}
}
