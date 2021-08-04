import java.util.ArrayList;
import java.util.Scanner;

public class b2580 { //스도쿠
	static int[][] mat = new int[9][9];
	static boolean vI[][] = new boolean[9][10];	
	static boolean vJ[][] = new boolean[9][10];	
	static boolean v9[][] = new boolean[9][10]; // 0버림	
	static ArrayList<Pos> list = new ArrayList<>();
	static boolean end = false;
	static class Pos{
		int i;
		int j;
		public Pos(int i, int j) {
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
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				mat[i][j] = sc.nextInt();
				if(mat[i][j] == 0){
					list.add(new Pos(i,j));
				}else {
					vJ[i][ mat[i][j] ] = true;
				}
			}
		}// 입력끝
		for(int j=0;j<9;j++) {
			for(int i=0;i<9;i++) {
				if(mat[i][j] != 0)
					vI[j][ mat[i][j] ] = true;
			}
		}
		int j=0,k=0;
		int temp = 0;
		for(int i=0;i<9;i++) {
			if(i%3 == 0) {
				temp = i;
			}
			for(int q=0;q<3;q++) {
				k = 3*i %9;
				j = temp + q;
				for(int w = 0;w<3;w++) {
					if(mat[j][k] != 0)
						v9[i][mat[j][k]] = true;
					k++;
				}
			}
		}
		dfs(0);
		
	}
	static void dfs(int idx) {
		if(idx == list.size()) {
			end = true;
			for(int i=0;i<9;i++) {
				for(int j=0;j<9;j++) {
					System.out.print(mat[i][j] + " ");
				}
				System.out.println();
			}
			return;
		}
		if(end) return;
		Pos temp;
		temp = list.get(idx);
		int id = -1;
		if(temp.i>=0 && temp.i<3) {
			if(temp.j>=0 && temp.j<3) {
				id = 0; 
			}else if(temp.j>=3 && temp.j<6) {
				id = 1;
			}else if(temp.j>=6 && temp.j<9) {
				id = 2;
			}
		}else if(temp.i>=3 && temp.i<6) {
			if(temp.j>=0 && temp.j<3) {
				id = 3; 
			}else if(temp.j>=3 && temp.j<6) {
				id = 4;
			}else if(temp.j>=6 && temp.j<9) {
				id = 5;
			}
		}else if(temp.i>=6 && temp.i<9) {
			if(temp.j>=0 && temp.j<3) {
				id = 6; 
			}else if(temp.j>=3 && temp.j<6) {
				id = 7;
			}else if(temp.j>=6 && temp.j<9) {
				id = 8;
			}
		}
		for(int i=1;i<10;i++) {
			if(vI[temp.j][i]) continue;
			if(vJ[temp.i][i]) continue;
			if(v9[id][i]) continue;
			vI[temp.j][i] = true;
			vJ[temp.i][i] = true;
			v9[id][i] = true;
			mat[temp.i][temp.j] = i;
			dfs(idx+1);
			mat[temp.i][temp.j] = 0; // 뺴도될 것
			vI[temp.j][i] = false;
			vJ[temp.i][i] = false;
			v9[id][i] = false;
		}
	}
}
