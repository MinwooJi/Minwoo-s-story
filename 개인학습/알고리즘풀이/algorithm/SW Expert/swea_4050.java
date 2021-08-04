import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class swea_4050 { // 재관이의 대량할인 (그리디-아이디어)
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int tc = Integer.parseInt(br.readLine());
		int[] arr;
		int N,sum,sub;
		for (int t = 1; t <= tc; t++) {
			sum = 0;
			sub = 0;
			N = Integer.parseInt(br.readLine());
			
			arr = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < arr.length; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				sum += arr[i];
			}// 입력끝
			Arrays.sort(arr); // 오름차순 정렬
			
			for (int i = arr.length-1,cnt=1; i >= 0; i--, cnt++) {
				if(cnt % 3 == 0) sub += arr[i];
			}
			sb.append("#").append(t).append(" ").append(sum-sub).append("\n");
		}
		System.out.print(sb);
	}
}
