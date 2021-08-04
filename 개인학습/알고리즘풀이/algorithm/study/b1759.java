import java.util.Arrays;
import java.util.Scanner;
public class b1759 { // 암호만들기
	static int L;
	static int C;
	static char[] charArr;
	static char[] set;
	static boolean v[];
	static char[] Vowels = {'a','e','i','o','u'};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		L = sc.nextInt();
		C = sc.nextInt();
		charArr = new char[C];
		v = new boolean[C];
		set = new char[L];
		for(int i=0;i<charArr.length;i++) {
			charArr[i] = sc.next().charAt(0);
		}// 입렦끝
		Arrays.sort(charArr);
		comb(0,0,0);
	}
	static void comb(int idx,int cnt , int curCount) {
		if(cnt == L) {
			if(curCount>=1 && L-curCount > 1) {
				for(int i=0;i<set.length;i++) {
					System.out.print(set[i]);
				}
				System.out.println();
			}
			return;
		}
		int vowelCnt;
		for(int i = idx;i<charArr.length;i++) {
			vowelCnt = 0;
			if(v[i]) continue;
			v[i] = true;
			if(isVowels(charArr[i]))
				vowelCnt ++;
			set[cnt] = charArr[i];
			comb(i,cnt+1,curCount + vowelCnt);
			set[cnt] = ' ';
			v[i] = false;
		}
	}
	static boolean isVowels(char ch) {
		for(int i=0;i<Vowels.length;i++) {
			if(Vowels[i] == ch) 
				return true;
		}
		return false;
	}
}

