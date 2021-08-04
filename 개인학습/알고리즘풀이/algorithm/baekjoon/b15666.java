

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class b15666 {
	static LinkedHashSet<String> set = new LinkedHashSet<>(); // linkedHash set?
	static int n,r;
	static int[] arr;
	static int[] arrSet;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		r = sc.nextInt();
		arr = new int[n];
		arrSet = new int[r];
		for(int i=0;i<arr.length;i++) {
			arr[i] = sc.nextInt();
		}
		Arrays.sort(arr);
		String st = new String("");
		comb(0,0,st);
		for(String s : set) {
			System.out.println(s);
		}
	}
	static void comb(int cnt,int idx,String st) {
		if(cnt == r) {
			set.add(st.trim());
			return;
		}
		for(int i=idx;i<n;i++) {
			comb(cnt + 1,i,st + " " +arr[i]);
		}
		
	}
}
