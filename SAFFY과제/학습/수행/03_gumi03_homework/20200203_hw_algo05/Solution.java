import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		Queue<Integer> q = new LinkedList<>();
		int tc = 10;
		int[] arr = new int[8];
		for(int t=0;t<tc;t++) {
			int subCnt = 0; // 1부터 뺴기 ->5되면 뺴고 다시 1로 초기화
			int[]rst = new int[8];
			int num = sc.nextInt();
			for(int i=0;i<8;i++) {
				arr[i] = sc.nextInt();
			}// 입력끝
			for(int i=0;i<8;i++)
				q.offer(arr[i]); // 처음부터 8개 다넣고 시작
			int temp = -1;
			while(true) {
				temp = q.poll()-(++subCnt); 
				if(subCnt == 5)
					subCnt = 0;
				if(temp<=0) { //종료조건
					temp = 0;
					q.offer(temp);
					int idx =0;
					while(!q.isEmpty()) { // rst
						rst[idx++] = q.poll();
					}
					break;
				}
				q.offer(temp); 

			}
			System.out.printf("#%d ",num);
			for(int i=0;i<8;i++) {
				System.out.printf("%d ",rst[i]);
			}
			System.out.println();
		}

	}

}
