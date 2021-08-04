import java.util.Scanner;

public class b10973 { // 다음순열, 이전순열
	static int N;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		int arr[] = new int[N];
		for(int i=0;i<arr.length;i++) {
			arr[i] = sc.nextInt();
		}
		prevPerm(arr);
	}
	static void prevPerm(int[] arr) {
		int idx=-1;
		for(int i=0;i<arr.length-1;i++) {
			if(arr[i] > arr[i+1]) {
				idx = i;
			}
		}
		if(idx == -1) {
			System.out.println(-1);
			return;
		}
		int idx2 = -1;
		for(int i=arr.length-1;i>=0;i--) {
			if(arr[i]<arr[idx]) {
				idx2 = i;
				break;
			}
		}
		int temp = arr[idx];
		arr[idx] = arr[idx2];
		arr[idx2] = temp;
		for(int i=idx+1,j = arr.length-1; i<j; i++,j--) {
			temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
		}
		for(int i=0;i<arr.length;i++) {
			System.out.print(arr[i] + " ");
		}
		
	}
//	static void nextPerm(int[] arr) {
//		int idx=-1;
//		for(int i=0;i<arr.length-1;i++) {
//			if(arr[i+1] > arr[i]) {
//				idx = i;
//			}
//		}
//		if(idx == -1) {
//			System.out.println(-1);
//			return;
//		}
//		int idx2 = idx+1;
//		for(int i=idx+1;i<arr.length-1;i++) {
//			if(arr[i+1]>arr[idx]) {
//				idx2 = i+1;
//			}
//		}
//		int temp = arr[idx];
//		arr[idx] = arr[idx2];
//		arr[idx2] = temp;
//		for(int i=idx+1,j = arr.length-1; i<j; i++,j--) {
//			temp = arr[i];
//			arr[i] = arr[j];
//			arr[j] = temp;
//		}
//		for(int i=0;i<arr.length;i++) {
//			System.out.print(arr[i] + " ");
//		}
//		
//	}
}
