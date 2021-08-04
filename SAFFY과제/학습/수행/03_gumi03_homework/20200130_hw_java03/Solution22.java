package com.ssafy.algo;
import java.io.FileInputStream;
import java.util.Scanner;
public class Solution22 {
	static int N;
	static int[][] lake;
	static int S;
	static int[] dx = {0, 0,0,-1,1}; // ���� �����¿�
	static int[] dy = {0, -1,1,0,0};
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("src/com/ssafy/algo/input.txt"));
		Scanner sc = new Scanner(System.in); 
		// TODO Auto-generated method stub
		int T = sc.nextInt();// �׽�Ʈ ���̽� ��
		for (int t = 1; t <= T; t++) {
			N = sc.nextInt(); // �迭�� ũ�� NxN
			lake = new int[N][N];
			int result = 0;
			boolean breakfor = false;
			S = sc.nextInt();// �ұ�����(Strider)��
			int[][] strider = new int[S][4];
			for (int k = 0; k < S; k++) {
				strider[k][0] = sc.nextInt();// ����ġ
				strider[k][1] = sc.nextInt();// ����ġ
				strider[k][2] = sc.nextInt();// ����(1:��,2:��)
			}
			for(int i=1;i<=S;i++) { // �ʱ� ��ġ��
				if(lake[strider[i-1][0]][strider[i-1][1]] !=0) {
					strider[i-1][3] = 1; // ����
					continue;
				}
				lake[strider[i-1][0]][strider[i-1][1]] = i;
			}
			for(int i =3;i>0;i--) { // �ٴ� �Ÿ�
				if(breakfor) 
					break;
				for(int j=1;j<=S;j++) { // �ұ����� �ѹ�
					if(strider[j-1][3] == 1)
						continue;
					int ny = strider[j-1][0];
					int nx = strider[j-1][1];
					for(int k=0;k<i;k++) {
						ny +=  dy[strider[j-1][2]];
						nx +=  dx[strider[j-1][2]];
						if(!isBound(nx,ny,N)) {
							strider[j-1][3] = 1; // ���� ����
							continue;
						}
						if(k == i-1) { // �����ؼ� �ɴ� �ڸ�
							if(lake[ny][nx] < 0) { // �ӹ����� �ֶ� �΋H���� ����
								strider[j-1][3] = 1;
								continue;
							}
							if(i == 1) {
								lake[ny][nx] = -j; // 3��° �ٰ� �ӹ��� ��ġ
							}
							else
								lake[ny][nx] = j;
							strider[j-1][0] = ny;
							strider[j-1][1] = nx;
						}
					}
				}
			}
			for(int i=0;i<S;i++) {
				if(strider[i][3] ==0)
					result++;
			}
			System.out.println("#" + t + " " + result);
		}
	}
	static boolean isBound(int x,int y,int N) {
		return (x>=0 && x<N && y>=0 && y<N);
	}
}
