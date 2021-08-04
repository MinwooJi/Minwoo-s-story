import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class b1786 { // 찾기
	static int[] getPi(String pattern) {
		int[] pi = new int[pattern.length()];
		int j = 0;
		for (int i = 1; i < pattern.length(); i++) {
			while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
				j = pi[j - 1];
			}
			if (pattern.charAt(i) == pattern.charAt(j)) {
				pi[i] = ++j;
			}
		}
		return pi;
	}
	static void KMP(String parent, String pattern) {
		int[] table = getPi(pattern);
		
		int j = 0; 
		List<Integer> list = new ArrayList<>();
		for(int i = 0 ; i< parent.length(); i++) {
			while(j >0 && parent.charAt(i) != pattern.charAt(j)) {
				j = table[j - 1];
			}
			if(parent.charAt(i) == pattern.charAt(j)) {
				if(j == pattern.length()-1) {
					list.add((i-pattern.length()+2));
					j = table[j];
				}else {
					j++;
				}
			}
		}
		System.out.println(list.size());
		for (Integer integer : list) {
			System.out.print(integer + " ");
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String origin = br.readLine();
		String pattern = br.readLine();
		KMP(origin,pattern);

	}

}
