import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class b17143 { //b17143 낚시왕 시뮬레이션
	//일단 그리디 귀찮으니 완탐으로.. 
	static int N,M,S;
	static int sharkMat[][];
	static Shark[] sArr;
	static int[] dx = {0,0,1,-1};
	static int[] dy = {-1,1,0,0};
	static int rst;
	static class Shark{
		int i;
		int j;
		int speed;
		int dir;
		int size;
		int id;
		boolean isAlive;
		public Shark(int i, int j, int speed, int dir, int size, int id, boolean isAlive) {
			this.i = i;
			this.j = j;
			this.speed = speed;
			this.dir = dir;
			this.size = size;
			this.id = id;
			this.isAlive = isAlive;
		}
		@Override
		public String toString() {
			return "Shark [i=" + i + ", j=" + j + ", speed=" + speed + ", dir=" + dir + ", size=" + size + ", id=" + id
					+ ", isAlive=" + isAlive + "]";
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		sharkMat = new int[N][M]; // idx
		for (int i = 0; i < N; i++) {
			Arrays.fill(sharkMat[i], -1);
		}
		
		sArr = new Shark[S];
		int y,x,speed,dir,size,id;
		for (int i = 0; i < S; i++) {
			st = new StringTokenizer(br.readLine());
			y = Integer.parseInt(st.nextToken())-1;
			x = Integer.parseInt(st.nextToken())-1;
			speed = Integer.parseInt(st.nextToken());
			dir = Integer.parseInt(st.nextToken())-1;
			size = Integer.parseInt(st.nextToken());
			id = i;
			if(dir == 0 || dir == 1) {
				speed = speed%(2*N-2);
			}else {
				speed = speed%(2*M-2);
			}
			sArr[i] = new Shark(y,x,speed,dir,size,id,true);
		}// 입력끝
		int idx = -1;
		for (int i=0;i<S;i++) {
			idx = sharkMat[sArr[i].i][sArr[i].j];
			if(idx == -1) {
				sharkMat[sArr[i].i][sArr[i].j] = i;
			}else {
				if(sArr[idx].size <= sArr[i].size) { // 새로 넣은것
					sArr[idx].isAlive = false;
					sharkMat[sArr[i].i][sArr[i].j] = i;
				}else {
					sArr[i].isAlive = false;
				}
			}
		}
		for (int q = 0; q < M; q++) {
			catchS(q);
			bfs();
		}
		System.out.println(rst);
	}
	static void catchS(int idx) {
		for (int i = 0; i < N; i++) {
			if(sharkMat[i][idx] != -1) {
				sArr[sharkMat[i][idx]].isAlive = false;
				rst += sArr[sharkMat[i][idx]].size;
				sharkMat[i][idx] = -1;
				return;
			}
		}
	}
	static void bfs() { // 상어 이동
		Queue<Shark> q = new LinkedList<>();
		for (Shark s : sArr) {
			if(s.isAlive && s.speed != 0) {
				q.offer(s);
				sharkMat[s.i][s.j] = -1;
			}
		}
		Shark temp;
		int ny,nx,speed,dir;
		while(!q.isEmpty()) {
			temp = q.poll();
			ny = temp.i + dy[temp.dir];
			nx = temp.j + dx[temp.dir];
			dir = temp.dir;
			speed = temp.speed -1;
			if(!isBound(ny,nx)) {
				ny = temp.i - dy[temp.dir];
				nx = temp.j - dx[temp.dir];
				switch(dir) {
				case 0: 
					dir = 1;
					break;
				case 1: 
					dir = 0;
					break;
				case 2: 
					dir = 3;
					break;
				case 3: 
					dir = 2;
					break;
				}
			}
			if(speed == 0) {
				if(sharkMat[ny][nx] != -1) {
					if(sArr[sharkMat[ny][nx]].size <= temp.size) {
						sArr[sharkMat[ny][nx]].isAlive = false;
						sharkMat[ny][nx] = temp.id;
						sArr[temp.id] = new Shark(ny,nx,sArr[temp.id].speed,dir,temp.size,temp.id,true);
					}else {
						sArr[temp.id].isAlive = false;
					}
				}else {
					sharkMat[ny][nx] = temp.id;
					sArr[temp.id] = new Shark(ny,nx,sArr[temp.id].speed,dir,temp.size,temp.id,true);
				}
			}else {
				q.offer(new Shark(ny,nx,speed,dir,temp.size,temp.id,true));
			}
		}
	}
	static boolean isBound(int ny,int nx) {
		return ny>=0 && nx >=0 && ny<N && nx<M;
	}
}
