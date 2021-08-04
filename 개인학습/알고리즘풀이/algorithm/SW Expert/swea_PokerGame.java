import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class swea_PokerGame {  // 
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int tc = Integer.parseInt(br.readLine());
		String[] temp = new String[5];
		char[] suit = new char[5];
		char[] rank = new char[5];
		for (int t = 1; t <= tc; t++) {
			st = new StringTokenizer(br.readLine());
			int[] suitCnt = new int[4];
			int[] rankCnt = new int[15]; // A처리? 0버림
			for (int i = 0; i < temp.length; i++) {
				temp[i] = st.nextToken();
			}// 입력끝
			for (int i = 0; i < temp.length; i++) {
				suit[i] = temp[i].charAt(0);
				if(suit[i] == 'S') suitCnt[0]++;
				else if(suit[i] == 'D') suitCnt[1]++;
				else if(suit[i] == 'H') suitCnt[2]++;
				else if(suit[i] == 'C') suitCnt[3]++;
				rank[i] = temp[i].charAt(1);
				if(rank[i] == 'A' || rank[i] == 'T' ||rank[i] == 'J' ||rank[i] == 'Q' ||rank[i] == 'K') {
					if(rank[i] == 'A') {
						rankCnt[1] ++;
						rankCnt[14] ++;
					}
					else if(rank[i] == 'T') rankCnt[10]++;
					else if(rank[i] == 'J') rankCnt[11]++;
					else if(rank[i] == 'Q') rankCnt[12]++;
					else if(rank[i] == 'K') rankCnt[13]++;
				}else {
					rankCnt[rank[i]-'0']++;
				}
			}
			// 카드판별
//			System.out.println(Arrays.toString(suitCnt));
//			System.out.println(Arrays.toString(rankCnt));
			int cons = 0;
			int sameRst = 0;
			int maxCons = 0;
			boolean isFullHouse1 = false;
			int isFullHouse2 = 0;
			for (int i = 1; i < rankCnt.length; i++) { // 숫자
				if(rankCnt[i] != 0) {
					cons++;
					sameRst = Math.max(sameRst, rankCnt[i]);
					maxCons = Math.max(cons, maxCons);
					if(rankCnt[i]>=3) {
						isFullHouse1 = true;
					}else if(rankCnt[i]>=2 && i!=14) {
						isFullHouse2++;
					}
				}else {
					cons = 0;
				}
			}
//			System.out.println(sameRst);
//			System.out.println(maxCons);
			int maxSame = 0;
			for (int i = 0; i < suitCnt.length; i++) {
				maxSame = Math.max(maxSame, suitCnt[i]);
			}
//			System.out.println(maxSame);
			
			// 판별
			String rst = "High card";
			if(maxSame ==5 && maxCons == 5) {
				rst = "Straight Flush";
			}else if(sameRst >= 4) {
				rst = "Four of a Kind";
			}else if(isFullHouse1 && isFullHouse2 == 1) {
				rst = "Full House";
			}else if(maxSame == 5) {
				rst = "Flush";
			}else if(maxCons == 5) {
				rst = "Straight";
			}else if(isFullHouse1) {
				rst = "Three of a kind";
			}else if(isFullHouse2>=2) {
				rst = "Two pair";
			}else if(isFullHouse2>=1) {
				rst = "One pair";
			}
			System.out.print(String.format("#%d ", t));
			System.out.println(rst);
			
		}
		
	}
}