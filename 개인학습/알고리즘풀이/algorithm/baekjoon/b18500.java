import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class b18500 { // 미네랄2
	static boolean[][] v;
	static StringBuffer[] sb;
	static int N,M;
	static int K;
	static int[] thr;
	static ArrayList<Pos> ClusterToFall;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {-1,0,1,0};
	static class Pos{
		int i;
		int j;
		public Pos(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
		@Override
		public String toString() {
			return "Pos [i=" + i + ", j=" + j + "]";
		}
		
	}
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	N = sc.nextInt();
    	M = sc.nextInt();
    	sb = new StringBuffer[N];
    	for(int i=0;i<N;i++) {
    		sb[i] = new StringBuffer(sc.next());
    	}
    	K = sc.nextInt();
    	thr = new int[K];
    	for(int i=0;i<K;i++) {
    		thr[i] = sc.nextInt();
    	} // 입력끝
    	for(int i=0;i<K;i++) {
    		breakM(i);
    		v = new boolean[N][M];
    		bottomCluster();
    		ClusterToFall = new ArrayList<>();
    		falling();
    	}
    	for(int k=0;k<N;k++) {
			System.out.println(sb[k]);
		}
		System.out.println();
    }
    static void falling() {
    	int maxI = 0;
    	ArrayList<Pos> benchmark = new ArrayList<>();
    	for(int i=0;i<N;i++) {
    		for(int j=0;j<M;j++) {
    			if(!v[i][j] && sb[i].charAt(j)=='x') { // 떨어질놈
    				ClusterToFall.add(new Pos(i,j)); // 한번에 한개만 떨어지니까 탐색 필요 x
    				sb[i].setCharAt(j, '.');
    			}
    		}
    	}
    	if(ClusterToFall.size()==0) return;
    	int minFall = 0;
    	Pos temp;
    	int tempY,tempX;
    	boolean flag = true;
    	while(flag) {
    		minFall ++;
    		for(int i=0;i<ClusterToFall.size();i++) {
    			temp = ClusterToFall.get(i);
    			if(!isBound(temp.i+minFall,temp.j)|| sb[temp.i+minFall].charAt(temp.j) == 'x') {
    				flag = false;
    				break;
    			}
    		}
    	}
    	minFall--;
    	char swapTemp;
    	for(int i=ClusterToFall.size()-1;i>=0;i--) {
    		temp = ClusterToFall.get(i);
    		sb[temp.i+minFall].setCharAt(temp.j, 'x');
    	}
    }
    static void bottomCluster() {
    	Queue<Pos> q = new LinkedList<>();
    	int ny,nx;
    	Pos temp;
    	for(int i=0;i<M;i++) {
    		if(sb[N-1].charAt(i)=='.') continue;
    		if(v[N-1][i]) continue;
    		v[N-1][i] = true;
    		q.offer(new Pos(N-1,i));
    		while(!q.isEmpty()) {
    			temp = q.poll();
    			for(int l=0;l<4;l++) {
    				ny = temp.i + dy[l];
    				nx = temp.j + dx[l];
    				if(!isBound(ny,nx)) continue;
    				if(v[ny][nx]) continue;
    				if(sb[ny].charAt(nx)=='.') continue;
    				v[ny][nx] = true;
    				q.offer(new Pos(ny,nx));
    			}
    		}
    	}
    }
    static void breakM(int idx) {
    	int x;
    	if(idx%2 == 0) {
    		x = 0;
    		while(isBound(N-thr[idx],x)) {
    			if(sb[N-thr[idx]].charAt(x) == 'x') {
    				sb[N-thr[idx]].setCharAt(x, '.');
    				break;
    			}
    			x++;
    		}
    	}else {
    		x = M-1;
    		while(isBound(N-thr[idx],x)) {
    			if(sb[N-thr[idx]].charAt(x) == 'x') {
    				sb[N-thr[idx]].setCharAt(x, '.');
    				break;
    			}
    			x--;
    		}
    	}
    }
    static boolean isBound(int ny,int nx) {
    	return nx >= 0 && ny >= 0 && nx <M && ny<N;
    }
}