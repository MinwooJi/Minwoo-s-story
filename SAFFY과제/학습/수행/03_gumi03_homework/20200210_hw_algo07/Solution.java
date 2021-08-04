import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	static int N;
	static char[] arr;
	static boolean flag;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = 10;
		for(int t =0;t<tc; t++) {
			N = Integer.parseInt(sc.nextLine());
			arr = new char[N+1];
			flag = true;
			int cnt = 0 ;
			for(int i = 1;i<=N;i++) {
				if(i == 10)
					cnt = 1;
				if(i == 100)
					cnt = 2;
				String temp = "";
				temp = sc.nextLine();
				arr[i] = temp.charAt(2+cnt);
			}
			System.out.printf("#%d ",t+1);
			inOrder(1);
			if(flag)
				System.out.println(1);
			else
				System.out.println(0);
		}
	}
	static void inOrder(int idx) {
		int leftChildIndex = findLChild(idx);
		int rightChildIndex = findRChild(idx);
		if(leftChildIndex != 0 && rightChildIndex !=0) { // 자식이 둘다 있다
			inOrder(leftChildIndex);
			if((arr[idx] >= '0' && arr[idx] <='9')) {
				flag = false;
				return;
			}
			inOrder(rightChildIndex);
			return;
		}
		if(leftChildIndex != 0) {
			flag = false;
			return;
		}
		if(arr[idx] < '0' || arr[idx] > '9') {
			flag = false;
			return; 
		}

	}
	static int findLChild(int i) {
		if(i*2 >= arr.length) {
			return 0;
		}
		if(arr[i*2] != '\u0000')
			return i*2;
		return 0;
	}
	static int findRChild(int i) {
		if(i*2+1 >= arr.length) {
			return 0;
		}
		if(arr[i*2+1] != '\u0000')
			return i*2+1;
		return 0;
	}

}
