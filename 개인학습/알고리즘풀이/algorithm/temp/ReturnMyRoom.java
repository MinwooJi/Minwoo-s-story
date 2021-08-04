import java.util.Arrays;
import java.util.Scanner;

public class ReturnMyRoom {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		for(int t=0;t<tc;t++) {
			int[] hallway = new int[201];
			int numStu = sc.nextInt();
			int[][] mat = new int[numStu][2];
			for(int i=0;i<mat.length;i++) {
				mat[i][0] = sc.nextInt();
				if(mat[i][0]%2 == 1) 
					mat[i][0]++;
				mat[i][1] = sc.nextInt();
				if(mat[i][1]%2 == 1) 
					mat[i][1]++;
				if(mat[i][0]>mat[i][1]) {
					int temp = mat[i][0];
					mat[i][0] = mat[i][1];
					mat[i][1] = temp;
				}
				for(int j=mat[i][0]/2;j<=mat[i][1]/2;j++) {
					hallway[j] += 1;
				}
			}
			int max = 0;
			for(int i =1;i<hallway.length;i++) {
				max = Math.max(max, hallway[i]);
			}
			System.out.println(Arrays.toString(hallway));
			System.out.printf("#%d ",t+1);
			System.out.println(max);
		}
	}
}


