import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class b2252 { // 줄세우기 (pq이용한 위상정렬)
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[] arr = new int[n+1];
		List<Integer>listArr[] = new ArrayList[n+1];
		for (int i = 0; i < listArr.length; i++) {
			listArr[i] = new ArrayList<>();
		}// 초기화
		int front,back;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			front = Integer.parseInt(st.nextToken());
			back = Integer.parseInt(st.nextToken());
			listArr[front].add(back);
			arr[back]++;
		}
		PriorityQueue<Integer>pq = new PriorityQueue<>(new Comparator<Integer>() {
			public int compare(Integer arg0, Integer arg1) {
				return Integer.compare(arr[arg0], arr[arg1]);
			}
		});
		for (int i = 1; i <= n; i++) {
			pq.offer(i);
		}
		while(!pq.isEmpty()) {
			front = pq.poll();
			sb.append(front).append(" ");
			for (int i = 0; i < listArr[front].size(); i++) {
				back = listArr[front].get(i);
				arr[back]--;
				pq.remove(back);
				pq.add(back);
			}
		}
		System.out.print(sb);
	}
}
