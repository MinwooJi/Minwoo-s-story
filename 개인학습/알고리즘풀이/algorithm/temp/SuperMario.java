import java.util.Scanner;
public class SuperMario {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int score = 0;
		int prevScore = 0;
		for(int i=0;i<10;i++) {
			prevScore = score;
			score += sc.nextInt();
			if(score>100) {
				if(score-100 > 100 - prevScore) {
					score = prevScore;
					break;
				}
				else
					break;
			}
		}
		System.out.println(score);
	}
}
