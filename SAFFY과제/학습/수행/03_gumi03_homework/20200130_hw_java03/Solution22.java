package com.ssafy.algo;
import java.io.FileInputStream;
import java.util.Scanner;
public class Solution22 {
	static int N;
	static int[][] lake;
	static int S;
	static int[] dx = {0, 0,0,-1,1}; // 공백 상하좌우
	static int[] dy = {0, -1,1,0,0};
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("src/com/ssafy/algo/input.txt"));
		Scanner sc = new Scanner(System.in); 
		// TODO Auto-generated method stub
		int T = sc.nextInt();// 테스트 케이스 수
		for (int t = 1; t <= T; t++) {
			N = sc.nextInt(); // 배열의 크기 NxN
			lake = new int[N][N];
			int result = 0;
			boolean breakfor = false;
			S = sc.nextInt();// 소금쟁이(Strider)수
			int[][] strider = new int[S][4];
			for (int k = 0; k < S; k++) {
				strider[k][0] = sc.nextInt();// 행위치
				strider[k][1] = sc.nextInt();// 열위치
				strider[k][2] = sc.nextInt();// 방향(1:하,2:우)
			}
			for(int i=1;i<=S;i++) { // 초기 위치값
				if(lake[strider[i-1][0]][strider[i-1][1]] !=0) {
					strider[i-1][3] = 1; // 죽음
					continue;
				}
				lake[strider[i-1][0]][strider[i-1][1]] = i;
			}
			for(int i =3;i>0;i--) { // 뛰는 거리
				if(breakfor) 
					break;
				for(int j=1;j<=S;j++) { // 소금쟁이 넘버
					if(strider[j-1][3] == 1)
						continue;
					int ny = strider[j-1][0];
					int nx = strider[j-1][1];
					for(int k=0;k<i;k++) {
						ny +=  dy[strider[j-1][2]];
						nx +=  dx[strider[j-1][2]];
						if(!isBound(nx,ny,N)) {
							strider[j-1][3] = 1; // 나가 죽음
							continue;
						}
						if(k == i-1) { // 점프해서 앉는 자리
							if(lake[ny][nx] < 0) { // 머무르던 애랑 부딫혀서 죽음
								strider[j-1][3] = 1;
								continue;
							}
							if(i == 1) {
								lake[ny][nx] = -j; // 3번째 뛰고 머무른 위치
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
