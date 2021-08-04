import java.util.Scanner;

public class b2579 { // 계단오르기
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] stScore = new int[301];
		int[][] score = new int[301][2];
		for (int i = 1; i <= n; i++) {
			stScore[i] = sc.nextInt();
		}
		score[1][0] = stScore[1];
		score[2][0] = stScore[2];
		score[2][1] = stScore[2] + score[1][0];
		score[3][0] = stScore[3] + score[1][0];
		score[3][1] = stScore[3] + score[2][0];
		for (int i = 4; i <= n; i++) {
			score[i][0] = Math.max(stScore[i] + score[i-2][1], stScore[i] + score[i-2][0]);
			score[i][1] = stScore[i] + score[i-1][0];
		}
		System.out.println(Math.max(score[n][0], score[n][1]));
		
	}
	
}
