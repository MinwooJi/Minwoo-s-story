import java.util.Arrays;
import java.util.Scanner;
public class swe3378 { // 스타일리쉬 들여쓰기 //시뮬레이션
	static boolean[][][] rcsCase;
	static int [][] P; // 전문가
	static int [][] Q; // 나
	static int [] dot; // 전문가 들여쓰기
	static int[] qDot;
	static int pN;
	static int qN;
	static String[] pString;
	static String[] qString;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		for(int t=1;t<=tc;t++) {
			rcsCase = new boolean[21][21][21]; // 1 1 1~20 20 20
			pN = sc.nextInt();
			qN = sc.nextInt();
			P = new int[pN][3]; // 괄호  마지막항은 안해도 될듯
			Q = new int[qN][3]; // () {} []
			dot = new int[pN];  // 첫째항은 안해도 될듯
			pString = new String[pN];
			qString = new String[qN];
			for(int i=0;i<pN;i++) {
				pString[i] = sc.next();
			}
			for(int i=0;i<qN;i++) {
				qString[i] = sc.next();
			}// 입력끝
			for(int i=1;i<21;i++) {
				for(int j=1;j<21;j++) {
					for(int k=1;k<21;k++) {
						rcsCase[i][j][k] = true;
					}
				}
			}
			cntGwalho(0);
			cntGwalho(1);
			cntDot();
			findTargetP(); // rcs 경우 계산
			sol();
			System.out.printf("#%d ",t);
			for(int i=0;i<qDot.length;i++) {
				System.out.print(qDot[i]+" ");
			}
			System.out.println();
			
		}
	}
	static void sol() {
		qDot = new int[qN];
		boolean first = true;
		boolean flag = false;
		for (int i = 1; i <= 20; i++) {
			for (int j = 1; j <= 20; j++) {
				for (int k = 1; k <= 20; k++) {
					if(rcsCase[i][j][k]) { // 가능한 경우
						flag = true;
						int smallCnt = 0,middleCnt = 0,bigCnt = 0;
						for (int q = 1; q < qN; q++) {
							smallCnt += Q[q-1][0];
							middleCnt += Q[q-1][1];
							bigCnt += Q[q-1][2];
							if(first) {
								qDot[q] = i*smallCnt + j*middleCnt + k*bigCnt;
								if(q==qN-1) first = false;
							}else { // 이전 경우에서 만든값과 다르다 (유일한 값x)
								if(i*smallCnt + j*middleCnt + k*bigCnt!= qDot[q]) {
									qDot[q] = -1;
								}
							}
						}
					}
				}
			}
		}
		if(!flag) { // 가능한 rcs 하나도 없는경우
			Arrays.fill(qDot, -1);
			qDot[0] = 0;
		}
	}
	static void findTargetP() { // 가능한 경우의 수 계산
		int smallCnt = 0,middleCnt = 0,bigCnt = 0,nextDotCnt = 0;
		for(int i=0;i<pN-1;i++) {
			smallCnt += P[i][0];
			middleCnt += P[i][1];
			bigCnt += P[i][2];
			nextDotCnt = dot[i+1];
			findCase(smallCnt, middleCnt, bigCnt, nextDotCnt);
		}
	}
	static void findCase(int smallCnt,int middleCnt, int bigCnt,int nextDotCnt) {
		for (int i = 1; i <= 20; i++) {
			for (int j = 1; j <= 20; j++) {
				for (int k = 1; k <= 20; k++) {
					if(!rcsCase[i][j][k]) continue;
					if(smallCnt * i + middleCnt * j + bigCnt *k !=nextDotCnt) {
						rcsCase[i][j][k] = false;
					}
				}
			}
		}
	}
	static void cntDot() {// gwalho에서 하기에는 break를 추가하지 못함
		for(int i=0;i<pN;i++) {
			for(int j=0;j<pString[i].length();j++) {
				if(pString[i].charAt(j)=='.') {
					dot[i]++;
				}else {
					break;
				}
			}
		}
	}
	static void cntGwalho(int func) {
		if(func == 0) {// 전문가
			for(int i=0;i<pN;i++) {
				for(int j=0;j<pString[i].length();j++) {
					if(pString[i].charAt(j)=='(') {
						P[i][0] ++;
					}else if(pString[i].charAt(j)==')') {
						P[i][0] --;
					}else if(pString[i].charAt(j)=='{') {
						P[i][1] ++;
					}else if(pString[i].charAt(j)=='}') {
						P[i][1] --;
					}else if(pString[i].charAt(j)=='[') {
						P[i][2] ++;
					}else if(pString[i].charAt(j)==']') {
						P[i][2] --;
					}
				}
			}
		}else if(func == 1) { // 내꺼
			for(int i=0;i<qN;i++) {
				for(int j=0;j<qString[i].length();j++) {
					if(qString[i].charAt(j)=='(') {
						Q[i][0] ++;
					}else if(qString[i].charAt(j)==')') {
						Q[i][0] --;
					}else if(qString[i].charAt(j)=='{') {
						Q[i][1] ++;
					}else if(qString[i].charAt(j)=='}') {
						Q[i][1] --;
					}else if(qString[i].charAt(j)=='[') {
						Q[i][2] ++;
					}else if(qString[i].charAt(j)==']') {
						Q[i][2] --;
					}
				}
			}
		}
	}
}
