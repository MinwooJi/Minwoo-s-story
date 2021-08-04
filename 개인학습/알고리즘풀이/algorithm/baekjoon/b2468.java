import java.util.Scanner;

public class b2468 {
	static int[][] mat;
	static int[][] temp;
	static boolean[][] visited;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {-1,0,1,0};
	static int N;
	static int maxHeigt = 0;
	static int num; // mat에 영역별로 num번호 입력
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		mat = new int[N][N];
		int rst = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				mat[i][j] = sc.nextInt();
				maxHeigt = Math.max(maxHeigt, mat[i][j]);
			}
		}
		//입력끝
		//		for(int l=1;l<=maxHeigt;l++) { // l= 범람크기
		for(int l=0;l<=maxHeigt;l++) { // l= 범람크기
			temp = new int[N][N];
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(mat[i][j] <= l) { // 침수
						temp[i][j] = -1;
					}
				}
			}

			//침수 체크 되어있음
			//여기에 sol로 인접지역 찾아가면 될듯
			visited = new boolean[N][N];
			num = 1;
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(temp[i][j] != -1 && !visited[i][j]) {
						sol(i,j);
						num++;
					}
					
				}
			}
			rst = Math.max(rst, num-1);
		}
		System.out.println(rst);
	}
	static boolean isBound(int i,int j) {
		return (i<N && j<N && i>=0 && j>=0);
	}
	static void sol(int i,int j) {
		if(visited[i][j] == true) 
			return;
		if(temp[i][j] == -1)
			return;
		visited[i][j] = true;
		temp[i][j] = num;
		for(int l=0;l<4;l++) {
			int ny = i + dy[l];
			int nx = j + dx[l];
			if(!isBound(ny,nx))
				continue;
			sol(ny,nx);
		}

	}
}
