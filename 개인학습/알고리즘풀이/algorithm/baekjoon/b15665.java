import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class b15665 {
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
		perm(0,st);
		for(String s : set) {
			System.out.println(s);
		}
	}
	static void perm(int cnt,String st) {
		if(cnt == r) {
			set.add(st.trim());
			return;
		}
		for(int i=0;i<n;i++) {
			perm(cnt + 1,st + " " +arr[i]);
		}
		
	}
}
