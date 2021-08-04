import java.util.Scanner;

public class Olympic {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		int [][] mat = new int[N+1][5]; // 번호 금 은 동 등수
		for(int i=1;i<mat.length;i++) {
			for(int j=0;j<mat[0].length-1;j++) {
				mat[i][j] = sc.nextInt();
			}
		}// 입력끝
		for(int i=1;i<mat.length;i++) {
			int betterMe = 0;
			int l = 1; // 0번은 나라 이름
			for(int j = 1;j<mat.length;j++) {
				if(i==j)
					continue;
				if(mat[i][l]<mat[j][l]) { // 나보다 잘난사람 수 check
					betterMe++;
				}else if(mat[i][l]>mat[j][l]) {
					continue;					
				}else {
					l++;
					while(l<4) {
						if(mat[i][l] < mat[j][l]) {
							betterMe++;
							break;
						}else if(mat[i][l] > mat[j][l]) {
							break;
						}
						else {
							l++;
						}
					}
					l = 1;
				}
			}
			mat[i][4] = 1+ betterMe;
		}
//		for(int i=1;i<mat.length;i++) {
//			System.out.println(mat[i][4]);
//		}
		for(int i=1;i<mat.length;i++) {
			if(mat[i][0] == K) {
				System.out.println(mat[i][4]);
				break;
			}
		}
	}
}
